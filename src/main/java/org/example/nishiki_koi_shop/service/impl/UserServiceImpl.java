package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.ChangePasswordForm;
import org.example.nishiki_koi_shop.model.payload.UserForm;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void softDeleteUser(Long userId, Long loggedInUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra nếu người dùng đang đăng nhập
        if (userId.equals(loggedInUserId)) {
            throw new RuntimeException("can not delete user is logged in");
        }

        // Kiểm tra nếu người dùng đã bị xóa mềm trước đó
        if (user.getDeletedAt() != null) {
            throw new RuntimeException("User is deleted at " + user.getDeletedAt());
        }

        // Thực hiện xóa mềm người dùng
        user.setDeletedAt(LocalDate.now());
        userRepository.save(user);
        log.info("User with ID {} has been soft delete", userId);
    }
    @Override
    public void restoreUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getDeletedAt() == null) {
            throw new RuntimeException("User is not deleted");
        }

        user.setDeletedAt(null);
        userRepository.save(user);
        log.info("User with ID {} restored", userId);
    }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getDeletedAt() != null) {
            throw new RuntimeException("User has been deleted");
        }

        return UserDto.from(user);
    }

    @Override
    public UserDto getMyInfo() {
        var context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        log.info("Authentication object: {}", authentication);

        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("User not authenticated");
            throw new RuntimeException("User not authenticated");
        }

        String name = authentication.getName();
        log.info("Authenticated user name: {}", name);

        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        return UserDto.from(user);
    }


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getDeletedAt() == null) // Lọc người dùng chưa bị xóa
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserForm updateUser(Long id, UserForm form) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getDeletedAt() != null) {
            throw new RuntimeException("User has been deleted");
        }

        user.setFullName(form.getFullName());
        user.setAddress(form.getAddress());
        user.setEmail(form.getEmail());
        user.setPhoneNumber(form.getPhone());
        user.setUsername(form.getUsername());

        userRepository.save(user);
        log.info("User with ID {} updated successfully", id);
        return UserForm.from(user);
    }

    @Override
    public String changePassword(ChangePasswordForm request, Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getDeletedAt() != null) {
            return "Cannot change password for a deleted user";
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return "Current password is incorrect";
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return "New password and confirm password do not match";
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        log.info("Password changed successfully for user with email {}", user.getEmail());
        return "Password changed successfully";
    }


}
