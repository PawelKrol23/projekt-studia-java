package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UzytkownikService {
    private final UzytkownikRepository uzytkownikRepository;

    public List<UzytkownikEntity> getUzytkownicy() {
        return uzytkownikRepository.findAll();
    }

    public void zapisz(UzytkownikEntity uzytkownik) {
        uzytkownikRepository.save(uzytkownik);
    }
}
