package com.example.projekt_studia_java.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/uzytkownik/zarejestruj").anonymous() //TODO: dodać przekierowanie gdy ktoś jest już zalogowany zeby nie było erroru 403
                        .requestMatchers("/informacja").hasAnyAuthority("USER","USER_WEAK")
                        .requestMatchers("/informacja/dodaj").hasAnyAuthority("USER")
                        .requestMatchers("/kategorie").hasAuthority("USER")
                        .requestMatchers("/kategorie/dodaj").hasAuthority("USER")
                        .requestMatchers("/uzytkownicy").hasAuthority("ADMIN")
                        .requestMatchers("/uzytkownik").hasAuthority("ADMIN")
                        .requestMatchers("/uzytkownik/logut").authenticated()
                        .requestMatchers("/uzytkownik/edytuj_uzytkownika").hasAuthority("ADMIN")
                        .requestMatchers("/h2-console").hasAuthority("ADMIN")
                        .requestMatchers("/h2-console/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form)->form
                        .loginPage("/uzytkownik/zaloguj")
                        .defaultSuccessUrl("/",true)
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

    @Bean
    public AuthenticationManager authenticationManager (UserDetailsService customUserDetailsService)
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider .setUserDetailsService(customUserDetailsService);
       // authProvider .setPasswordEncoder(passwordEncoder());
        List<AuthenticationProvider> providers = List.of(authProvider );
        return new ProviderManager( providers);
    }


}
