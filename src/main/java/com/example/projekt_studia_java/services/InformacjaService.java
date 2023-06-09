package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.domain.db.KategoriaEntity;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import com.example.projekt_studia_java.repositories.KategoriaRepository;
import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InformacjaService {
    private final KategoriaRepository kategoriaRepository;
    private final UzytkownikRepository uzytkownikRepository;
    private final InformacjaRepository informacjaRepository;

    public void zapisz(Informacja informacja) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        InformacjaEntity doZapisania = InformacjaEntity.builder()
                .tresc(informacja.getTresc())
                .link(informacja.getLink())
                .tytul(informacja.getTytul())
                .kategoria(kategoriaRepository.findByNazwa(informacja.getKategoria()))
                .uzytkownik(uzytkownikRepository.findByLogin(authentication.getName()))
                .dataPrzypomnienia(informacja.getDataPrzypomnienia())
                .build();

        informacjaRepository.save(doZapisania);
    }
    public void usun(InformacjaEntity informacja) {
        informacjaRepository.delete(informacja);
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

        // Przefiltrowanie informacji na podstawie uzytkownika
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        informacje = informacje.stream()
                .filter(informacjaEntity -> informacjaEntity.getUzytkownik().getLogin().equals(authentication.getName()))
                .collect(Collectors.toList());

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

    public List<KategoriaEntity> sortKategoria()
    {
        List<KategoriaEntity> kategorie = kategoriaRepository.findAll();

        kategorie.sort((kat1, kat2) -> CharSequence.compare(kat1.getNazwa(), kat2.getNazwa()));
        java.util.Collections.reverse(kategorie);

        kategorie.sort(Comparator.comparingInt(kat -> kat.getInformacje().size()));
        java.util.Collections.reverse(kategorie);

        return kategorie;
    }
    public InformacjaEntity findInformacja(int id)
    {
        return informacjaRepository.findById(id).get();
    }

}
