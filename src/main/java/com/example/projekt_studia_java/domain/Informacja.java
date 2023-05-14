package com.example.projekt_studia_java.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
@Setter
//@ToString(includeFieldNames = false)
public class Informacja {
    private String tytul;
    private String kategoria;
    private String tresc;
    private String link;
    private LocalDateTime dataDodania;
    private LocalDateTime dataPrzypomnienia;

    public String toString()
    {
        if(dataDodania == null || dataPrzypomnienia == null)
        {
            return tytul + " " + kategoria  + " " + tresc + " " + link;
        }
        else
        {
            String data1 = dataDodania.getDayOfMonth() + ":" + dataDodania.getMonthValue() + ":" + dataDodania.getYear();
            String data2 = dataPrzypomnienia.getDayOfMonth() + ":" + dataPrzypomnienia.getMonthValue() + ":" + dataPrzypomnienia.getYear();
            return tytul + " " + kategoria  + " " + tresc + " " + link + " " + data1 + " " + data2;
        }
    }

}