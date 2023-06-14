package com.example.projekt_studia_java.domain.db;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "Role")
@Data

public class RolaEntity {
    //KLASA RÓL
    @GeneratedValue (strategy = GenerationType .IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ROLA" , nullable = false, length = 50)
    private String rola;
    @JoinColumn(name="Uzytkownik_id")
    @ManyToOne
    UzytkownikEntity uzytkownikEntity;
}