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
            if (userRepository.findByEmail("lam1782004@gmail.com").isEmpty()) {
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

            User admin = null;
            if (userRepository.findByEmail("manager@example.com").isEmpty()) {
                Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                        .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_MANAGER").build()));

                admin = User.builder()
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
            if (userRepository.findByEmail("salestaff@example.com").isEmpty()) {
                Role saleStaffRole = roleRepository.findByName("ROLE_SALE_STAFF")
                        .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_SALE_STAFF").build()));

                User saleStaff = User.builder()
                        .username("saleStaff")
                        .email("salestaff@example.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Sale Staff User")
                        .role(saleStaffRole)
                        .createdDate(LocalDate.now())
                        .address("42/1")
                        .phoneNumber("0362651806")
                        .build();

                userRepository.save(saleStaff);

                Cart cart = Cart.builder()
                        .user(saleStaff)
                        .createdDate(LocalDate.now())
                        .build();
                cartRepository.save(cart);
            }

            if (userRepository.findByEmail("saledelivery@example.com").isEmpty()) {
                Role saleDeliveryRole = roleRepository.findByName("ROLE_SALE_DELIVERY")
                        .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_SALE_DELIVERY").build()));

                User saleDelivery = User.builder()
                        .username("saleDelivery")
                        .email("saledelivery@example.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Sale Delivery User")
                        .role(saleDeliveryRole)
                        .createdDate(LocalDate.now())
                        .address("42/1")
                        .phoneNumber("0362651806")
                        .build();

                userRepository.save(saleDelivery);
                Cart cart = Cart.builder()
                        .user(saleDelivery)
                        .createdDate(LocalDate.now())
                        .build();
                cartRepository.save(cart);
            }
            if (userRepository.findByEmail("saleconsult@example.com").isEmpty()) {
                Role saleConsultRole = roleRepository.findByName("ROLE_SALE_CONSULT")
                        .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_SALE_CONSULT").build()));

                User saleConsult = User.builder()
                        .username("saleConsult")
                        .email("saleconsult@example.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Sale Consult User")
                        .role(saleConsultRole)
                        .createdDate(LocalDate.now())
                        .address("42/1")
                        .phoneNumber("0362651806")
                        .build();

                userRepository.save(saleConsult);
                Cart cart = Cart.builder()
                        .user(saleConsult)
                        .createdDate(LocalDate.now())
                        .build();
                cartRepository.save(cart);
            }






        };
    }
}
    

