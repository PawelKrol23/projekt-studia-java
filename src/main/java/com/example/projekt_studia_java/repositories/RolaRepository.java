package com.example.projekt_studia_java.repositories;


import com.example.projekt_studia_java.domain.db.RolaEntity;
import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolaRepository extends JpaRepository<RolaEntity, Integer> {
    void deleteAllByUzytkownikEntity(UzytkownikEntity uzytkownikEntity);

}

