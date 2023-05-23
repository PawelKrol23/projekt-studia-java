package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Getter
public class InformacjaService {
    @Autowired
    InformacjaRepository informacjaRepository;

    public List<Informacja> sort(String typ, String direction, List<Informacja> lista) {
        switch (typ) {
            case "data" -> lista.sort(java.util.Comparator.comparing(Informacja::getDataDodania));
            case "kategoria" -> lista.sort((info1, info2) -> info1.getKategoria().getNazwa().compareTo(info2.getKategoria().getNazwa()));
            default -> lista.sort(java.util.Comparator.comparing(Informacja::getTytul));
        }

        if(Objects.equals(direction, "malejaco"))
        {
            java.util.Collections.reverse(lista);
        }
        return lista;
    }
    public List<Informacja> filter(String dataFiltrowania)
    {
        List<Informacja> lista = informacjaRepository.getInformacje();
        List<Informacja> listaPoFiltrowaniu = new ArrayList<>();

        LocalDateTime czas = LocalDateTime.now();

        switch (dataFiltrowania)
        {
            case "2tyg" -> czas = czas.minusWeeks(2);
            case "4tyg" -> czas = czas.minusMonths(1);
            case "8tyg" -> czas = czas.minusMonths(2);
            case "24tyg" -> czas = czas.minusMonths(6);
            case "48tyg" -> czas = czas.minusYears(1);
            case "nigdy" -> czas = czas.minusYears(100);
        }

        for(Informacja element : lista)
        {
            if(element.getDataDodania().isAfter(czas) || element.getDataDodania().isEqual(czas))
            {
                listaPoFiltrowaniu.add(element);
            }
        }
        return listaPoFiltrowaniu;
    }
}
