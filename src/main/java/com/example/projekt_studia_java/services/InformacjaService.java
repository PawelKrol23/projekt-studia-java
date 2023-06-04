package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.KategoriaEntity;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import com.example.projekt_studia_java.repositories.KategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InformacjaService {
    private final KategoriaRepository kategoriaRepository;
    private final InformacjaRepository informacjaRepository;

    public List<InformacjaEntity> getInformacje() {
        return informacjaRepository.findAll();
    }

    public void zapisz(Informacja informacja) {
        InformacjaEntity doZapisania = InformacjaEntity.builder()
                .tresc(informacja.getTresc())
                .link(informacja.getLink())
                .tytul(informacja.getTytul())
                .kategoria(kategoriaRepository.findByNazwa(informacja.getKategoria()))
                .dataPrzypomnienia(LocalDateTime.now().plusYears(1))
                .build();

        informacjaRepository.save(doZapisania);
    }

    public List<InformacjaEntity> sortFilterInformacje(String typ, String direction, String kategoria, String data) {
        // Ustalenie z jakiego okresu mają być informacje
        Integer dni = null;
        if(data != null) {
            switch (data)
            {
                case "2tyg" -> dni = 2 * 7;
                case "4tyg" -> dni = 4 * 7;
                case "8tyg" -> dni = 8 * 7;
                case "24tyg" -> dni = 24 * 7;
                case "48tyg" -> dni = 48 * 7;
            }
        }

        // Pobranie informacji
        List<InformacjaEntity> informacje;
        if(dni != null) {
            informacje = informacjaRepository.findByDataDodaniaAfter(LocalDateTime.now().minusDays(dni));
        } else {
            informacje = informacjaRepository.findAll();
        }

        // Przefiltrowanie informacji na podstawie kategorii
        if(kategoria != null && !kategoria.equals("wszystkie")) {
            informacje = informacje.stream()
                    .filter(informacjaEntity -> informacjaEntity.getKategoria().getNazwa().equals(kategoria))
                    .collect(Collectors.toList());
        }

        // Posortowanie informacji
        switch (typ) {
            case "data" -> informacje.sort(Comparator.comparing(InformacjaEntity::getDataDodania));
            case "kategoria" -> informacje.sort(Comparator.comparing(info -> info.getKategoria().getNazwa()));
            case "alfabetycznie" -> informacje.sort(Comparator.comparing(InformacjaEntity::getTytul));
        }

        // Odwrócenie listy, jeżeli dane mają być posortowane malejąco
        if(Objects.equals(direction, "malejaco"))
        {
            java.util.Collections.reverse(informacje);
        }

        return informacje;
    }

    public List<InformacjaEntity> sort(String typ, String direction, List<InformacjaEntity> lista) {
        switch (typ) {
            case "data" -> lista.sort(Comparator.comparing(InformacjaEntity::getDataDodania));
            case "kategoria" -> //lista.sort(Comparator.comparing(info -> info.getKategoria().getNazwa()));
            {
                List<KategoriaEntity> listaK = sortKategoria();
                List<InformacjaEntity> listaI = new ArrayList<>();

                for(KategoriaEntity kategoria : listaK)
                {
                    for(InformacjaEntity informacja : lista)
                    {
                        if(informacja.getKategoria() == kategoria)
                            listaI.add(informacja);
                    }
                }
                lista = listaI;
            }
            default -> lista.sort(Comparator.comparing(InformacjaEntity::getTytul));
        }

        if(Objects.equals(direction, "rosnaco"))
        {
            java.util.Collections.reverse(lista);
        }
        return lista;
    }
    public List<KategoriaEntity> sortKategoria()
    {
        List<KategoriaEntity> kategorie = kategoriaRepository.findAll();

        kategorie.sort((kat1, kat2) -> CharSequence.compare(kat1.getNazwa(), kat2.getNazwa()));
        java.util.Collections.reverse(kategorie);

        kategorie.sort(Comparator.comparingInt(kat -> kat.getInformacje().size()));
        java.util.Collections.reverse(kategorie);

        return kategorie;
    }

    public List<InformacjaEntity> filter(String dataFiltrowania, String kategoriaFiltrowania)
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

        List<InformacjaEntity> lista = informacjaRepository.findByDataDodaniaAfter(czas);

        if(!kategoriaFiltrowania.equals("brak"))
            lista.removeIf(informacja -> !informacja.getKategoria().getNazwa().equals(kategoriaFiltrowania));

        return lista;
    }
}
