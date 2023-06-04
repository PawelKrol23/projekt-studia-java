package com.example.projekt_studia_java.domain;

import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.validators.KategoriaValidation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kategoria {
    @Size(min=3,max=20,message = "Nazwa musi miec od 3 do 20 liter")
    @Pattern(regexp = "^[a-z]+$",message = "Kategoria musi zawierac tylko male litery")
    @KategoriaValidation
    private String nazwa;
}
