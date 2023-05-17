package com.example.projekt_studia_java.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Uzytkownik {
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private String mail;
    private int wiek;

}
