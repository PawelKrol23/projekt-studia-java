package com.example.projekt_studia_java.repository;

import com.example.projekt_studia_java.domain.Informacja;
import lombok.ToString;
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
        this.informacje.add(new Informacja("samochód","motoryzacja","szybki samochód","www.zupełnie_inny_link.com", LocalDateTime.of(2022, 5, 12, 0, 0, 0),LocalDateTime.of(2022, 6, 12, 0, 0, 0)));
    }

    public List<Informacja> getInformacje() {
        return informacje;
    }
}
