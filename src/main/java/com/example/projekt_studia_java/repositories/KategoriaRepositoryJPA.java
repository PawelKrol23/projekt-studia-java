package com.example.projekt_studia_java.repositories;

import com.example.projekt_studia_java.domain.Kategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriaRepositoryJPA extends JpaRepository<Kategoria, Integer> {
    Kategoria findByNazwa(String nazwa);
}
