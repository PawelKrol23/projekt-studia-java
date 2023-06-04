package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.services.KategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KategoriaController {
    private final KategoriaService kategoriaService;
    @GetMapping("/kategorie")
    public String formularzDodawaniaKategori(Model model) {
        model.addAttribute("newKategoria",new Kategoria());
        return "kategorie";
    }
    @PostMapping("/kategorie")
    public String dodajKategorie(@Valid @ModelAttribute("newKategoria") Kategoria newKategoria, BindingResult result, Model model){
        if(result.hasErrors())
        {
            return "kategorie";
        }

        kategoriaService.zapisz(newKategoria);

        return "redirect:/kategorie";
    }


}
