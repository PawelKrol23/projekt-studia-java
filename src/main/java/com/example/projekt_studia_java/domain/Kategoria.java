package com.example.projekt_studia_java.domain;

import com.example.projekt_studia_java.validators.KategoriaValidation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kategoria {
    @Size(min=3,max=20,message = "Nazwa musi miec od 3 do 20 liter")
    @Pattern(regexp = "^[a-z]+$",message = "Kategoria musi zawierac tylko male litery")
    @KategoriaValidation
    private String nazwa;
}
