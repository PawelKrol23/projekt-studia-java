package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/uzytkownik")
public class UzytkownikController {
    @Autowired
    UzytkownikRepository uzytkownikRepository;
    @GetMapping
    public String getAllData(Model model)
    {
        return "uzytkownik";
    }
    @GetMapping("/zarejestruj")
    public String formularzDodawaniaUzytkownika(Model model) {
        Uzytkownik uzytkownik;
        uzytkownik = new Uzytkownik();
        model.addAttribute("uzytkownicy",uzytkownik);
        return "zarejestruj";
    }
    @PostMapping("/zarejestruj")
    public String dodajUzytkownika(@ModelAttribute Uzytkownik uzytkownik){

        uzytkownikRepository.zapisz(uzytkownik);
        return "redirect:/uzytkownik";
    }
    @GetMapping("/zaloguj")
    public String logowanieUzytkownika(Model model)
    {
        return "zaloguj";
    }



}
