package com.example.projekt_studia_java.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=3,max=20,message = "Imie musi miec od 3 do 20 liter ")
    @Pattern(regexp = "^[A-Z][a-z]*$",message = "Imie musi sie zaczynac wielka litera")
    private String imie;
    @Size(min=3,max=50,message = "Nazwisko musi miec od 3 do 50 liter ")
    @Pattern(regexp = "^[A-Z][a-z]*$",message = "Nazwisko musi sie zaczynac wielka litera")
    private String nazwisko;
    @Size(min=3,max=20,message = "login musi miec od 3 do 20 znakow")
    @Pattern(regexp = "^[a-z]+$",message = "login musi zawierac tylko male litery")
    private String login;

    @Size(min=5,message = "haslo musi zawierac conajmniej 5 znakow")
    private String haslo;

    @Email
    private String mail;
    @Min(value = 18,message = "minimalny wiek to 18 lat")
    @NotNull
    private Integer wiek;

}
