package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.services.UzytkownikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        //Uzytkownik uzytkownik = new Uzytkownik();
        model.addAttribute("newUzytkownik",new Uzytkownik());
        return "zarejestruj";
    }
    @PostMapping("/zarejestruj")
    public String dodajUzytkownika(@Valid @ModelAttribute("newUzytkownik") Uzytkownik newUzytkownik, BindingResult result, Model model){
        if(result.hasErrors())
        {
           System.out.println(result.getErrorCount());
           result.getAllErrors().forEach(el->System.out.println(el));
           return"zarejestruj";
        }

        uzytkownikService.zapisz(newUzytkownik);

        return "redirect:/uzytkownik";
    }
    @GetMapping("/zaloguj")
    public String logowanieUzytkownika(Model model)
    {
        return "zaloguj";
    }
}
