package com.example.projekt_studia_java.repository;

import com.example.projekt_studia_java.domain.Informacja;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InformacjaRepository {
    private final List<Informacja> informacje;

    public InformacjaRepository() {
        this.informacje = new ArrayList<>();
        Informacja informacja = new Informacja();
        informacja.setTytul("amogus");
        informacja.setLink("www.link.com");
        informacja.setTresc("amogus");
        this.informacje.add(informacja);
    }

    public List<Informacja> getInformacje() {
        return informacje;
    }
}
