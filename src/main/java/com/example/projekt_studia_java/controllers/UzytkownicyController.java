package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
import com.example.projekt_studia_java.services.UzytkownikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/uzytkownicy")
@RequiredArgsConstructor
public class UzytkownicyController {
    private final UzytkownikService uzytkownikService;
    @GetMapping
    public String getAllData(Model model) {
        model.addAttribute("uzytkownicy",uzytkownikService.getUzytkownicy());
        return "uzytkownicy";
    }

    @PostMapping("/edytuj")
    public String edytujInformacja(@Valid @ModelAttribute("uzytkownicy") UzytkownikEntity uzytkownik, @RequestParam("id") int id, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("uzytkownicy",uzytkownikService.getUzytkownicy());
            return "edytuj";
        }
        UzytkownikEntity uzytkownikDoEdycji = uzytkownikService.findUzytkownik(id);
        uzytkownikService.usun(uzytkownikDoEdycji);
        uzytkownikService.zapisz(uzytkownik);
        return "redirect:/uzytkownicy";
    }

}
