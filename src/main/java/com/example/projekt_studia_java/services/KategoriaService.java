package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.domain.db.KategoriaEntity;
import com.example.projekt_studia_java.repositories.KategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriaService {
    private final KategoriaRepository kategoriaRepository;

    public List<KategoriaEntity> getKategorie() {
        return kategoriaRepository.findAll();
    }
    public void zapisz(Kategoria kategoria) {
        KategoriaEntity kategoriaEntity = new KategoriaEntity();
        kategoriaEntity.setNazwa(kategoria.getNazwa());
        kategoriaRepository.save(kategoriaEntity);
    }
}
