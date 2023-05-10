package com.example.projekt_studia_java.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class Informacja {
    private String tytul;
    private String tresc;
    private String link;
    private LocalDateTime dataDodania;
    private LocalDateTime dataPrzypomnienia;

}
