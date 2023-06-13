package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.Uzytkownik;
import com.example.projekt_studia_java.services.UzytkownikService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public String dodajUzytkownika(@Valid @ModelAttribute("newUzytkownik") Uzytkownik newUzytkownik, BindingResult result){
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
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, null, auth);
        }
        return "index";
    }
}
