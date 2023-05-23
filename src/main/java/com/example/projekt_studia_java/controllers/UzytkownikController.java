package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.services.UzytkownikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/uzytkownik")
@RequiredArgsConstructor
public class UzytkownikController {
    private final UzytkownikService uzytkownikService;
    @GetMapping
    public String getAllData(Model model)
    {
        model.addAttribute("uzytkownicy",uzytkownikService.getUzytkownicy());
        return "uzytkownik";
    }
    @GetMapping("/zarejestruj")
    public String formularzDodawaniaUzytkownika(Model model) {
        Uzytkownik uzytkownik = new Uzytkownik();
        model.addAttribute("uzytkownicy",uzytkownik);
        return "zarejestruj";
    }
    @PostMapping("/zarejestruj")
    public String dodajUzytkownika(@ModelAttribute Uzytkownik uzytkownik){
        uzytkownikService.zapisz(uzytkownik);
        return "redirect:/uzytkownik";
    }
    @GetMapping("/zaloguj")
    public String logowanieUzytkownika(Model model)
    {
        return "zaloguj";
    }
}
