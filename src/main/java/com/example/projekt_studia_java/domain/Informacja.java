package com.example.projekt_studia_java.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@ToString(includeFieldNames = false)
public class Informacja {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String tytul;
    @ManyToOne
    private Kategoria kategoria;
    private String tresc;
    private String link;
    private LocalDateTime dataDodania;
    private LocalDateTime dataPrzypomnienia;


    public String writeDataDodania()
    {
        int monthVal = dataDodania.getMonthValue();
        String month;

        if(monthVal <= 9) {
            month = "0" + monthVal;
        }
        else {
            month = String.valueOf(monthVal);
        }
        return dataDodania.getDayOfMonth() + ":" + month + ":" + dataDodania.getYear();
    }

    public String writeDataPrzypomnienia()
    {
        int monthVal = dataPrzypomnienia.getMonthValue();
        String month;

        if(monthVal <= 9) {
            month = "0" + monthVal;
        }
        else {
            month = String.valueOf(monthVal);
        }
        return dataPrzypomnienia.getDayOfMonth() + ":" + month + ":" + dataPrzypomnienia.getYear();
    }






}
