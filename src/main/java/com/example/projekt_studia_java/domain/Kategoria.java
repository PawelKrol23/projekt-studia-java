package com.example.projekt_studia_java.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
public class Kategoria {
    private String nazwa;
    public String toString()
    {
        return nazwa;
    }
}
