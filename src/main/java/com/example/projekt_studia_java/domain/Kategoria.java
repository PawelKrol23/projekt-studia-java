package com.example.projekt_studia_java.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Kategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nazwa;
    @Builder.Default
    @OneToMany(mappedBy = "kategoria")
    private List<Informacja> informacje = new ArrayList<>();
    public String toString()
    {
        return nazwa;
    }
}
