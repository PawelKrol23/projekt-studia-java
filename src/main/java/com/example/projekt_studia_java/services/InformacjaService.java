package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Getter
public class InformacjaService {
    @Autowired
    InformacjaRepository informacjaRepository;

    public List<Informacja> sort(String typ, String direction) {
        List<Informacja> lista = informacjaRepository.getInformacje();

        switch (typ) {
            case "data" -> lista.sort(java.util.Comparator.comparing(Informacja::getDataDodania));
            case "kategoria" -> lista.sort(java.util.Comparator.comparing(Informacja::getKategoria));
            default -> lista.sort(java.util.Comparator.comparing(Informacja::getTytul));
        }


        if(Objects.equals(direction, "malejaco"))
        {
            java.util.Collections.reverse(lista);
        }
        return lista;
    }
}
