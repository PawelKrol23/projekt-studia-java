package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.repositories.InformacjaRepositoryJPA;
import com.example.projekt_studia_java.repositories.KategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InformacjaService {
    private final KategoriaRepository kategoriaRepository;
    private final InformacjaRepositoryJPA informacjaRepositoryJPA;

    public List<InformacjaEntity> getInformacje() {
        return informacjaRepositoryJPA.findAll();
    }

    public void zapisz(Informacja informacja) {
        InformacjaEntity doZapisania = InformacjaEntity.builder()
                .tresc(informacja.getTresc())
                .link(informacja.getLink())
                .tytul(informacja.getTytul())
                .kategoria(kategoriaRepository.findByNazwa(informacja.getKategoria()))
                .dataPrzypomnienia(LocalDateTime.now().plusYears(1))
                .build();

        informacjaRepositoryJPA.save(doZapisania);
    }

    public List<InformacjaEntity> sort(String typ, String direction, List<InformacjaEntity> lista) {
        switch (typ) {
            case "data" -> lista.sort(Comparator.comparing(InformacjaEntity::getDataDodania));
            case "kategoria" -> lista.sort(Comparator.comparing(info -> info.getKategoria().getNazwa()));
            default -> lista.sort(Comparator.comparing(InformacjaEntity::getTytul));
        }

        if(Objects.equals(direction, "malejaco"))
        {
            java.util.Collections.reverse(lista);
        }
        return lista;
    }

    public List<InformacjaEntity> filter(String dataFiltrowania)
    {
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

        return informacjaRepositoryJPA.findByDataDodaniaAfter(czas);
    }
}
