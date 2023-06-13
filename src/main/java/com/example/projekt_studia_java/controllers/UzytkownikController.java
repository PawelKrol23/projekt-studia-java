package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.services.UzytkownikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/uzytkownik")
@RequiredArgsConstructor
public class UzytkownikController {
    private final UzytkownikService uzytkownikService;
    @GetMapping
    public String getAllData(Model model) {
        model.addAttribute("uzytkownicy",uzytkownikService.getUzytkownicy());
        return "uzytkownik";
    }
    @GetMapping("/zarejestruj")
    public String formularzDodawaniaUzytkownika(Model model) {
        model.addAttribute("newUzytkownik", new Uzytkownik());
        return "zarejestruj";
    }
    @PostMapping("/zarejestruj")
    public String dodajUzytkownika(@Valid @ModelAttribute Uzytkownik newUzytkownik, BindingResult result){
        if(result.hasErrors())
        {
           return "zarejestruj";
        }

        uzytkownikService.zapisz(newUzytkownik);

        return "redirect:/uzytkownik";
    }
    @GetMapping("/zaloguj")
    public String logowanieUzytkownika(Model model)
    {
        return "zaloguj";
    }

    @PostMapping("/zaloguj")
    public String zalogujUzytkownika(@RequestParam("login") String login, @RequestParam("haslo") String haslo){

        if (login.equals("admin") && haslo.equals("admin123")) {
            return "redirect:/informacja";
        } else {
            return "redirect:/";
        }
    }


}
