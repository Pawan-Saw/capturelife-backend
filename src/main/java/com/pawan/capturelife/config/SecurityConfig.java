package com.pawan.capturelife.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // ✅ Public routes
                .requestMatchers("/api/signup", "/api/login", "/").permitAll()
                .requestMatchers("/h2-console/**").permitAll()

                // ✅ Public — Booking
                .requestMatchers("/api/bookings").permitAll()
                .requestMatchers("/api/bookings/my/**").permitAll()

                // ✅ Public — Contact form
                .requestMatchers("/api/contact").permitAll()

                // ✅ Public — Pricing & Testimonials (frontend pe dikhenge)
                .requestMatchers("/api/pricing").permitAll()
                .requestMatchers("/api/testimonials").permitAll()

                // ✅ Admin only
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")

                .requestMatchers("/api/gallery").permitAll()
                .requestMatchers("/api/gallery/**").permitAll()

                .requestMatchers("/api/public/**").permitAll()

                // ✅ Baki sab authenticated
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}