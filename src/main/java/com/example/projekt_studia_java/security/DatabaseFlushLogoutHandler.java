package com.example.projekt_studia_java.security;

import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseFlushLogoutHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
    private final UzytkownikRepository uzytkownikRepository;

    public DatabaseFlushLogoutHandler(UzytkownikRepository uzytkownikRepository) {
        this.uzytkownikRepository = uzytkownikRepository;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        uzytkownikRepository.flush();
        super.onLogoutSuccess(request, response, authentication);
    }
}