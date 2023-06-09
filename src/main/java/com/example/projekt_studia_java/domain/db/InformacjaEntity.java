package com.example.projekt_studia_java.domain.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Informacja")
//@ToString(includeFieldNames = false)
public class InformacjaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tytul;
    @ManyToOne
    private KategoriaEntity kategoria;
    private String tresc;
    private String link;
    @CreationTimestamp
    private LocalDateTime dataDodania;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataPrzypomnienia;


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
