package com.example.projekt_studia_java.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("user1")
                        .password("password")
                        .roles("USER")
                        .build();
        UserDetails weak_user =
                User.withUsername("user2")
                        .password("good")
                        .roles("USER_WEAK")
                        .build();
        UserDetails admin =
                User.withUsername("admin")
                        .password("best")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user,admin,weak_user);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/informacja").hasAnyRole("USER","WEAK_USER","ADMIN")
                        .requestMatchers("/informacja").hasAnyRole("USER","WEAK_USER","ADMIN")
                        .requestMatchers("/kategorie").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form)->form
                        .loginPage("/uzytkownik/zaloguj")
                        .permitAll()
                )
                .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        int rounds = 12;
        return NoOpPasswordEncoder.getInstance();
    }
}
