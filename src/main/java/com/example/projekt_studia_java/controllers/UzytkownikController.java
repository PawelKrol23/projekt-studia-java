package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
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
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/uzytkownik")
@RequiredArgsConstructor
public class UzytkownikController {

   // @Autowired
    //BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UzytkownikService uzytkownikService;

    @GetMapping
    public String getAllData(Model model) {
        model.addAttribute("uzytkownicy",uzytkownikService.getUzytkownicy());
        return "uzytkownicy";
    }
    @GetMapping("/zarejestruj")
    public String formularzDodawaniaUzytkownika(Model model) {
        model.addAttribute("newUzytkownik", new UzytkownikEntity());
        return "zarejestruj";
    }
    @PostMapping("/zarejestruj")
    public String dodajUzytkownika(@Valid @ModelAttribute("newUzytkownik") UzytkownikEntity newUzytkownik,
                                   BindingResult result)
    {
        if(result.hasErrors())
        {
           return "zarejestruj";
        }

        uzytkownikService.zapisz(newUzytkownik);
        uzytkownikService.nadajRole(newUzytkownik);
        return "redirect:/uzytkownik";
    }
    @GetMapping("/zaloguj")
    public String logowanieUzytkownika()
    {
        return "zaloguj";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, null, auth);
        }

        return "redirect:/";
    }
    @GetMapping("/edytuj_uzytkownika")
    public String formularzEdycjiUzytkownika(Model model, @RequestParam("uzytkownikDoEdycji") int id) {
        UzytkownikEntity uzytkownikEntity = uzytkownikService.findUzytkownik(id);
        model.addAttribute("uzytkownicy",uzytkownikEntity);
        model.addAttribute("id", id);
        return "edytuj_uzytkownika";
    }
    @PostMapping("/edytuj_uzytkownika")
    public String edytujUzytkownika(@Valid @ModelAttribute("uzytkownicy") UzytkownikEntity uzytkownik,
                                    BindingResult result,
                                    @RequestParam("id") int id,
                                    @RequestParam("rolka") String rolka)
    {
        if(result.hasErrors()) {
            return "edytuj_uzytkownika";
        }

        uzytkownikService.edytujUzytkownika(uzytkownik, rolka, id);

        return "redirect:/uzytkownik";
    }

    @GetMapping("/usun/{id}")
    public String usuwanko(@PathVariable("id") int id){
        UzytkownikEntity uzytkownikDoEdycji = uzytkownikService.findUzytkownik(id);
        uzytkownikService.usun(uzytkownikDoEdycji);
        return "redirect:/uzytkownik";
    }


}
