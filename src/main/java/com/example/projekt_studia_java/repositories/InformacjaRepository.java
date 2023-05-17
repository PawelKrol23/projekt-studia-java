package com.example.projekt_studia_java.repositories;

import com.example.projekt_studia_java.domain.Informacja;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InformacjaRepository {
    private final List<Informacja> informacje;

    public InformacjaRepository() {
        this.informacje = new ArrayList<>();

        this.informacje.add(new Informacja("piłka","sport","okrągła piłka","www.link.com", LocalDateTime.of(2021, 5, 12, 0, 0, 0),LocalDateTime.of(2021, 6, 12, 0, 0, 0)));
        this.informacje.add(new Informacja("obraz","sztuka","ładny obraz","www.inny_link.com", LocalDateTime.of(2023, 5, 12, 0, 0, 0),LocalDateTime.of(2023, 6, 12, 0, 0, 0)));
        this.informacje.add(new Informacja("batonik","jedzenie","przyszny batonik","www.zupełnie_inny_link.com", LocalDateTime.of(2022, 3, 12, 0, 0, 0),LocalDateTime.of(2022, 4, 12, 0, 0, 0)));
        this.informacje.add(new Informacja("samochód","motoryzacja","szybki samochód","www.kompletnie_inny_link.com", LocalDateTime.of(2022, 5, 12, 0, 0, 0),LocalDateTime.of(2022, 6, 12, 0, 0, 0)));
    }
    public void zapisz(Informacja info){
        this.informacje.add(info);
    }

    public List<String> getKategorie()
    {
        List<String> kategorie = new ArrayList<>();
        for(Informacja informacja : informacje)
        {
            String kategoria = informacja.getKategoria();
            if (!kategorie.contains(kategoria))
            {
                kategorie.add(kategoria);
            }
        }
        return kategorie;
    }
    public List<Informacja> getInformacje() {
        return informacje;
    }
}
