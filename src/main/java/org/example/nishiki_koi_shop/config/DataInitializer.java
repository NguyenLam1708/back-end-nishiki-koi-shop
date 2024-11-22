package org.example.nishiki_koi_shop.config;

import org.example.nishiki_koi_shop.model.entity.*;
import org.example.nishiki_koi_shop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CartRepository cartRepository) {
        return args -> {
            if (userRepository.findByEmail("lam1782004@gmail.com").isEmpty()){
                Role userRole = roleRepository.findByName("ROLE_CUSTOMER")
                        .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_CUSTOMER").build()));

                User user = User.builder()
                        .username("Nguyen Lam")
                        .email("lam1782004@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Nguyen Thanh Lam")
                        .role(userRole)
                        .createdDate(LocalDate.now())
                        .address("42/1")
                        .phoneNumber("0362651806")
                        .build();

                userRepository.save(user);

                Cart cart = Cart.builder()
                        .user(user)
                        .createdDate(LocalDate.now())
                        .build();
                cartRepository.save(cart);
            }

            if (userRepository.findByEmail("manager@example.com").isEmpty()) {
                Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                        .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_MANAGER").build()));

                User admin = User.builder()
                        .username("manager")
                        .email("manager@example.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Manager User")
                        .role(managerRole)
                        .createdDate(LocalDate.now())
                        .address("42/1")
                        .phoneNumber("0362651806")
                        .build();

                userRepository.save(admin);

                Cart cart = Cart.builder()
                        .user(admin)
                        .createdDate(LocalDate.now())
                        .build();
                cartRepository.save(cart);
            }


        };
    }
}
    

