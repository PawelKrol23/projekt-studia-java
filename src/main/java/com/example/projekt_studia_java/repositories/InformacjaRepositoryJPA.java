package com.example.projekt_studia_java.repositories;

import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacjaRepositoryJPA extends JpaRepository<InformacjaEntity, Integer> {
}
