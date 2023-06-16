package com.example.projekt_studia_java.domain.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table (name="Uzytkownik")
public class UzytkownikEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=3,max=20,message = "Imie musi miec od 3 do 20 liter")
    @Pattern(regexp = "^[A-Z][a-z]*$",message = "Imie musi sie zaczynac wielka litera i skladac z samych liter")
    private String imie;
    @Size(min=3,max=50,message = "Nazwisko musi miec od 3 do 50 liter ")
    @Pattern(regexp = "^[A-Z][a-z]*$",message = "Nazwisko musi sie zaczynac wielka litera i skladac z samych liter")
    private String nazwisko;
    @Column(name="login" , nullable = false, length = 50)
    @Size(min=3,max=20,message = "login musi miec od 3 do 20 znakow")
    @Pattern(regexp = "^[a-z]+$",message = "login moze zawierac tylko male litery")
    private String login;

    @Column(name="haslo" , nullable = false, length = 200)
    @Size(min=5,message = "haslo musi zawierac conajmniej 5 znakow")
    private String haslo;

    @Email(message = "Email niepoprawny")
    @NotNull(message = "Email nie moze byc pusty")
    private String mail;
    @Min(value = 18,message = "minimalny wiek to 18 lat")
    @NotNull
    private Integer wiek;

    @OneToMany (mappedBy = "uzytkownikEntity",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    List<RolaEntity> role = new ArrayList<>();

    public String wyswietl()
    {
        StringBuilder role_wypisz = new StringBuilder();
        for(RolaEntity rola : role)
        {
            role_wypisz.append(" ").append(rola);
        }
        return role_wypisz.toString();
    }

    public boolean zawieraRole(String nazwaRoli) {
        for(var rola: this.getRole()) {
            if(rola.getRola().equals(nazwaRoli)) {
                return true;
            }
        }

        return false;
    }
}
