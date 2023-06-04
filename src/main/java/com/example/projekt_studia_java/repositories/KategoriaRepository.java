package com.example.projekt_studia_java.repositories;

import com.example.projekt_studia_java.domain.db.KategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriaRepository extends JpaRepository<KategoriaEntity, Integer> {
    KategoriaEntity findByNazwa(String nazwa);
}
