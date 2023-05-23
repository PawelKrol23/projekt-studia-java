package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.repositories.UzytkownikRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UzytkownikService {
    private final UzytkownikRepositoryJPA uzytkownikRepositoryJPA;

    public List<Uzytkownik> getUzytkownicy() {
        return uzytkownikRepositoryJPA.findAll();
    }

    public void zapisz(Uzytkownik uzytkownik) {
        uzytkownikRepositoryJPA.save(uzytkownik);
    }
}
