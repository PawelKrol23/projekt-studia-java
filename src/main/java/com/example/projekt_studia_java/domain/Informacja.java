package com.example.projekt_studia_java.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Informacja {
    private String tytul;
    private String kategoria;
    private String tresc;
    private String link;
}
