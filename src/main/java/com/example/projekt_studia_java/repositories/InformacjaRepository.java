package com.example.projekt_studia_java.repositories;

import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.domain.Informacja;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InformacjaRepository {
    private final List<Informacja> informacje;
    private final List<Kategoria> kategorie;

    public InformacjaRepository() {
        this.informacje = new ArrayList<>();
        this.kategorie = new ArrayList<>();

        this.kategorie.add(new Kategoria("motoryzacja"));
        this.kategorie.add(new Kategoria("sport"));
        this.kategorie.add(new Kategoria("jedzenie"));
        this.kategorie.add(new Kategoria("moda"));
        this.kategorie.add(new Kategoria("podróże"));
        this.kategorie.add(new Kategoria("sztuka"));
        this.kategorie.add(new Kategoria("biznes"));
        this.kategorie.add(new Kategoria("technologia"));
        this.kategorie.add(new Kategoria("gry i rozrywka"));
        this.kategorie.add(new Kategoria("dom i ogród"));

        this.informacje.add(new Informacja("piłka",kategorie.get(1),"okrągła piłka","www.link.com", LocalDateTime.of(2023, 1, 12, 0, 0, 0),LocalDateTime.of(2021, 6, 12, 0, 0, 0)));
        this.informacje.add(new Informacja("obraz",kategorie.get(5),"ładny obraz","www.inny_link.com", LocalDateTime.of(2023, 4, 12, 0, 0, 0),LocalDateTime.of(2023, 6, 12, 0, 0, 0)));
        this.informacje.add(new Informacja("batonik",kategorie.get(2),"przyszny batonik","www.zupełnie_inny_link.com", LocalDateTime.of(2023, 4, 30, 0, 0, 0),LocalDateTime.of(2022, 4, 12, 0, 0, 0)));
        this.informacje.add(new Informacja("samochód",kategorie.get(0),"szybki samochód","www.kompletnie_inny_link.com", LocalDateTime.of(2022, 5, 12, 0, 0, 0),LocalDateTime.of(2022, 6, 12, 0, 0, 0)));
    }
    public void zapisz(Informacja info){
        this.informacje.add(info);
    }

    public List<Kategoria> getKategorie()
    {
        return kategorie;
    }
    public List<Informacja> getInformacje() {
        return informacje;
    }
}
