package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.repositories.InformacjaRepositoryJPA;
import com.example.projekt_studia_java.repositories.KategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InformacjaService {
    private final KategoriaRepository kategoriaRepository;
    private final InformacjaRepositoryJPA informacjaRepositoryJPA;

    private final KategoriaService kategoriaService;

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
            case "kategoria" -> //lista.sort(Comparator.comparing(info -> info.getKategoria().getNazwa()));
            {
                List<Kategoria> listaK = sortKategoria();
                List<InformacjaEntity> listaI = new ArrayList<>();

                for(Kategoria kategoria : listaK)
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
    public List<Kategoria> sortKategoria()
    {
        List<Kategoria> listaKategorji = kategoriaService.getKategorie();
        List<InformacjaEntity> listaInformacji = getInformacje();
        Map<Kategoria,Integer> mapa = new HashMap<>();
        for(Kategoria kategoria : listaKategorji)
            mapa.put(kategoria,0);
        for(InformacjaEntity informacja : listaInformacji)
            mapa.replace(informacja.getKategoria(),mapa.get(informacja.getKategoria())+1);

        List<Map.Entry<Kategoria, Integer>> lista = new ArrayList<>(mapa.entrySet());
        lista.sort((entry1, entry2) -> CharSequence.compare(entry1.getKey().getNazwa(), entry2.getKey().getNazwa()));
        java.util.Collections.reverse(lista);
        lista.sort((entry1, entry2) -> Integer.compare(entry1.getValue(), entry2.getValue()));

        listaKategorji.clear();

        for(Map.Entry<Kategoria,Integer> entry : lista)
            listaKategorji.add(0, entry.getKey());

        return listaKategorji;
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

        List<InformacjaEntity> lista = informacjaRepositoryJPA.findByDataDodaniaAfter(czas);

        if(!kategoriaFiltrowania.equals("brak"))
            lista.removeIf(informacja -> !informacja.getKategoria().getNazwa().equals(kategoriaFiltrowania));

        return lista;
    }
}
