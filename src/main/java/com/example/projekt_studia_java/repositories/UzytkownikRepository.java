package com.example.projekt_studia_java.repositories;


import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.domain.Uzytkownik;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UzytkownikRepository {
    private final List<Uzytkownik> uzytkownicy;

    public UzytkownikRepository()
    {
        this.uzytkownicy = new ArrayList<>();
        this.uzytkownicy.add(new Uzytkownik("szef","szefu","admin","admin","mail@admina.pl",28));
    }
    public void zapisz(Uzytkownik user){
        this.uzytkownicy.add(user);
    }

    public List<Uzytkownik> getUzytkownicy()
    {
        return uzytkownicy;
    }
}
