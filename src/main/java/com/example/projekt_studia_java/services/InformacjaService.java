package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import com.example.projekt_studia_java.repositories.InformacjaRepositoryJPA;
import com.example.projekt_studia_java.repositories.KategoriaRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InformacjaService {
    private final KategoriaRepositoryJPA kategoriaRepositoryJPA;
    private final InformacjaRepositoryJPA informacjaRepositoryJPA;
    private final InformacjaRepository informacjaRepository;

    public List<InformacjaEntity> getInformacje() {
        return informacjaRepositoryJPA.findAll();
    }

    public void zapisz(Informacja informacja) {
        InformacjaEntity doZapisania = InformacjaEntity.builder()
                .tresc(informacja.getTresc())
                .link(informacja.getLink())
                .tytul(informacja.getTytul())
                .kategoria(kategoriaRepositoryJPA.findByNazwa(informacja.getKategoria()))
                .dataPrzypomnienia(LocalDateTime.now().plusYears(1))
                .build();

        informacjaRepositoryJPA.save(doZapisania);
    }

    public List<InformacjaEntity> sort(String typ, String direction, List<InformacjaEntity> lista) {
        switch (typ) {
            case "data" -> lista.sort(java.util.Comparator.comparing(InformacjaEntity::getDataDodania));
            case "kategoria" -> lista.sort(Comparator.comparing(info -> info.getKategoria().getNazwa()));
            default -> lista.sort(java.util.Comparator.comparing(InformacjaEntity::getTytul));
        }

        if(Objects.equals(direction, "malejaco"))
        {
            java.util.Collections.reverse(lista);
        }
        return lista;
    }
    public List<InformacjaEntity> filter(String dataFiltrowania)
    {
        List<InformacjaEntity> lista = informacjaRepository.getInformacje();
        List<InformacjaEntity> listaPoFiltrowaniu = new ArrayList<>();

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

        for(InformacjaEntity element : lista)
        {
            if(element.getDataDodania().isAfter(czas) || element.getDataDodania().isEqual(czas))
            {
                listaPoFiltrowaniu.add(element);
            }
        }
        return listaPoFiltrowaniu;
    }
}
