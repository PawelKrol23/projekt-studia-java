package com.example.projekt_studia_java.domain.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Kategoria")
public class KategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nazwa;
    @Builder.Default
    @OneToMany(mappedBy = "kategoria")
    private List<InformacjaEntity> informacje = new ArrayList<>();
    public String toString()
    {
        return nazwa;
    }
}
