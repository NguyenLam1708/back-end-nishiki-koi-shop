package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.nishiki_koi_shop.exception.InvalidRefreshTokenException;
import org.example.nishiki_koi_shop.model.dto.AuthDto;
import org.example.nishiki_koi_shop.model.entity.Role;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.SignInForm;
import org.example.nishiki_koi_shop.model.payload.SignUpForm;
import org.example.nishiki_koi_shop.repository.RoleRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.security.JwtTokenProvider;
import org.example.nishiki_koi_shop.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CartServiceImpl cartServiceImpl;

    @Override
    public AuthDto login(SignInForm form) {

        User user = userRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + form.getEmail()));

        if (user.getDeletedAt() != null) {
            throw new IllegalArgumentException("User account is deactivated. Please contact support.");
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword())
            );
        } catch (AuthenticationException e) {
            log.error("Authentication failed for email: {} with exception: {}", form.getEmail(), e.getMessage());
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Nếu xác thực thành công
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        log.info("User {} logged in successfully", user.getEmail());

        String result = "Login successful";

        return AuthDto.from(user, accessToken, refreshToken, result);
    }

    @Override
    public String register(SignUpForm form) {
        if (userRepository.existsByUsername(form.getUsername())) {
            throw new IllegalArgumentException("Username is already in use");
        }
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }
        Role role = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_CUSTOMER").build()));

        User user = User.builder()
                .username(form.getUsername())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .role(role)
                .createdDate(LocalDate.now())
                .build();

        userRepository.save(user);
        log.info("User {} registered", user.getUsername());
        return "Success register new user";
    }

    @Override
    public AuthDto refreshJWT(String refreshToken) {
        if (refreshToken != null) {
            refreshToken = refreshToken.replaceFirst("Bearer ", "");
            if (jwtTokenProvider.validateRefreshToken(refreshToken)) {
                Authentication auth = jwtTokenProvider.createAuthentication(refreshToken);

                User user = userRepository.findByEmail(auth.getName())
                        .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + auth.getName()));

                log.info("User {} refreshed token", user.getUsername());

                // Xác định trạng thái và thông điệp kết quả
                String result = "Token refreshed successfully";

                return AuthDto.from(user, jwtTokenProvider.generateAccessToken(auth), refreshToken , result);
            }
        }
        throw new InvalidRefreshTokenException(refreshToken);
    }

}