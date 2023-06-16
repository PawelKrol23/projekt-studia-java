package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.db.RolaEntity;
import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
import com.example.projekt_studia_java.repositories.RolaRepository;
import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UzytkownikService {
    private final UzytkownikRepository uzytkownikRepository;
    private final RolaRepository rolaRepository;
    public List<UzytkownikEntity> getUzytkownicy() {
        return uzytkownikRepository.findAll();
    }

    public void zapisz(UzytkownikEntity uzytkownik) {
        uzytkownikRepository.save(uzytkownik);
    }
    public UzytkownikEntity findUzytkownik(int id)
    {
        return uzytkownikRepository.findById(id).get();
    }
    public void nadajRole(UzytkownikEntity uzytkownik){
        RolaEntity rola = new RolaEntity();
        rola.setRola("USER_WEAK");
        rola.setUzytkownikEntity(uzytkownik);
        rolaRepository.save(rola);
        uzytkownik.getRole().add(rola);
    }
    @Transactional
    public void edytujUzytkownika(UzytkownikEntity uzytkownik, String rolka, int id){

        // Pobranie uzytkownika z bazy i aktualizacja wartosci
        UzytkownikEntity uzytkownikDoEdycji = uzytkownikRepository.findById(id).get();
        uzytkownikDoEdycji.setImie(uzytkownik.getImie());
        uzytkownikDoEdycji.setNazwisko(uzytkownik.getNazwisko());
        uzytkownikDoEdycji.setLogin(uzytkownik.getLogin());
        uzytkownikDoEdycji.setMail(uzytkownik.getMail());
        uzytkownikDoEdycji.setWiek(uzytkownik.getWiek());

        // Usuniecie istniejacych rol
        rolaRepository.deleteAllByUzytkownikEntity(uzytkownikDoEdycji);
        uzytkownikDoEdycji.getRole().clear();

        // Wstawienie nowych rol
        for(String a: rolka.split(","))
        {
            RolaEntity rola = new RolaEntity();
            rola.setRola(a);
            rola.setUzytkownikEntity(uzytkownikDoEdycji);
            RolaEntity zapisanaRola = rolaRepository.save(rola);
            uzytkownik.getRole().add(zapisanaRola);
        }

        // Zapisanie i odswiezenie bazy danych
        uzytkownikRepository.save(uzytkownikDoEdycji);
        uzytkownikRepository.flush();
    }
    public void usun(UzytkownikEntity uzytkownik) {
        uzytkownikRepository.delete(uzytkownik);
    }
}
