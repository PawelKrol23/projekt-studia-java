package com.example.projekt_studia_java.repositories;

import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InformacjaRepository extends JpaRepository<InformacjaEntity, Integer> {
    List<InformacjaEntity> findByDataDodaniaAfter(LocalDateTime dataDodania);
}
