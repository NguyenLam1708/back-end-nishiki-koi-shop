package org.example.nishiki_koi_shop.security;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Bộ lọc xác thực JWT để xử lý token trong request
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Service lấy thông tin user từ database
    private final UserDetailsServiceImpl userDetailsService;

    // Các endpoint không yêu cầu xác thực
    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/auth/**", // Các API liên quan đến xác thực
            "/api/v1/fish/**", // Các API liên quan đến thông tin cá
            "/api/v1/farms/**", // Các API liên quan đến thông tin trang trại
            "/api/v1/tours/**", // Các API liên quan đến thông tin tour
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF
                .authorizeHttpRequests(authorize -> authorize
                        // Quyền public
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/fish/**").permitAll()

                        // Các quyền phân cấp
                        .requestMatchers("/api/v1/manager/**").hasAuthority("ROLE_MANAGER")
                        .requestMatchers("/api/v1/sale-staff/**").hasAuthority("ROLE_SALE_STAFF")
                        .requestMatchers("/api/v1/staff-delivery/**").hasAuthority("ROLE_STAFF_DELIVERY")
                        .requestMatchers("/api/v1/staff-consult/**").hasAuthority("ROLE_STAFF_CONSULT")

                        // Các quyền khác
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Cấu hình CORS cho phép các yêu cầu từ frontend
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Chỉ định nguồn (origin) được phép truy cập
        configuration.addAllowedMethod("*"); // Cho phép tất cả các phương thức HTTP (GET, POST, PUT, DELETE,...)
        configuration.addAllowedHeader("*"); // Cho phép tất cả các header
        configuration.setAllowCredentials(true); // Hỗ trợ cookies hoặc headers chứa thông tin xác thực

        // Đăng ký cấu hình CORS cho tất cả các đường dẫn
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Mã hóa mật khẩu sử dụng thuật toán BCrypt
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // Cung cấp AuthenticationManager để xử lý xác thực
        return configuration.getAuthenticationManager();
    }

}
