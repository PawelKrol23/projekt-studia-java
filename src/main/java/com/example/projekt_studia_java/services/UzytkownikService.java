package com.example.projekt_studia_java.services;

import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.domain.db.RolaEntity;
import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
import com.example.projekt_studia_java.repositories.RolaRepository;
import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void nadajRoleEdycja(UzytkownikEntity uzytkownik, String rolka){

        RolaEntity rola = new RolaEntity();
        rolaRepository.deleteAllByUzytkownikEntity(uzytkownik);
        System.out.println(rolka);
        rola.setRola(rolka);
        rola.setUzytkownikEntity(uzytkownik);
        rolaRepository.save(rola);
        uzytkownik.getRole().add(rola);

        ArrayList<RolaEntity> list = new ArrayList<>();
        list.add(rola);

        uzytkownik.setRole(list);
        uzytkownikRepository.save(uzytkownik);
        uzytkownikRepository.flush();

    }
    public void usun(UzytkownikEntity uzytkownik) {
        uzytkownikRepository.delete(uzytkownik);
    }
}
