//package com.labwithcommunity.infrastructure.configs;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/v1/*").permitAll()
//                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
//                        .requestMatchers("/api/v1/**").authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/v1/signin")
//                        .successHandler((request, response, authentication) -> {
//                            response.setStatus(200);
//                        })
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessHandler((request, response, authentication) -> {
//                            if (authentication != null) {
//                                response.setStatus(HttpServletResponse.SC_OK);
//                            } else {
//                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                            }
//                        })
//                        .permitAll()
//                )
//                .csrf(csrf -> csrf.disable())
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                );
//
//        return http.build();
//    }
//}
