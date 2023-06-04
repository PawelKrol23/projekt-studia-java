package com.example.projekt_studia_java.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Informacja {
    @Size(min=3,max=20,message = "Nazwa musi miec od 3 do 20 liter")
    private String tytul;
    private String kategoria;
    @Size(min=5,max=500,message = "Tresc musi miec od 5 do 500 liter")
    private String tresc;
    @NotBlank(message="Link nie moze byc pusty")
    private String link;
    private String dataPrzypomnienia;
}
