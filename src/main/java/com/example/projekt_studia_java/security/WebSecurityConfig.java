package com.example.projekt_studia_java.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final DatabaseFlushLogoutHandler databaseFlushLogoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/uzytkownik/zarejestruj").anonymous()
                .requestMatchers("/informacja").hasAnyAuthority("USER","USER_WEAK")
                .requestMatchers("/informacja/dodaj").hasAnyAuthority("USER")
                .requestMatchers("/kategorie").hasAuthority("USER")
                .requestMatchers("/kategorie/dodaj").hasAuthority("USER")
                .requestMatchers("/uzytkownicy").hasAuthority("ADMIN")
                .requestMatchers("/uzytkownik").hasAuthority("ADMIN")
                .requestMatchers("/uzytkownik/logut").authenticated()
                .requestMatchers("/uzytkownik/edytuj_uzytkownika").hasAuthority("ADMIN")
                .requestMatchers(toH2Console()).authenticated()
                .anyRequest().authenticated()
                .and().csrf().ignoringRequestMatchers(toH2Console())
                .and().headers().frameOptions().sameOrigin()
                .and()
                .formLogin((form)->form
                        .loginPage("/uzytkownik/zaloguj")
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/").permitAll()
                        .logoutSuccessHandler(databaseFlushLogoutHandler)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
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
