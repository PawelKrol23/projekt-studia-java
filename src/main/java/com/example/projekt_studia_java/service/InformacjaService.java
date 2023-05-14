package com.example.projekt_studia_java.service;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.repository.InformacjaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Service
@Getter
public class InformacjaService {
    @Autowired
    InformacjaRepository informacjaRepository;

    public void sort(String typ, String direction) {
        List<Informacja> lista = informacjaRepository.getInformacje();

        switch (typ) {
            case "data":
                lista.sort(java.util.Comparator.comparing(Informacja::getDataDodania));
                break;
            case "kategoria":
                lista.sort(java.util.Comparator.comparing(Informacja::getKategoria));
                break;
            default:
                lista.sort(java.util.Comparator.comparing(Informacja::getTytul));
                break;
        }

        if(Objects.equals(direction, "malejaco"))
        {
            java.util.Collections.reverse(lista);
        }
    }
}
