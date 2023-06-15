package com.example.projekt_studia_java.controllers;


import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
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
    public String dodajUzytkownika(@Valid @ModelAttribute("newUzytkownik") UzytkownikEntity newUzytkownik, BindingResult result){
        if(result.hasErrors())
        {
           return "zarejestruj";
        }

        uzytkownikService.zapisz(newUzytkownik);
        uzytkownikService.nadajRole(newUzytkownik);
        return "redirect:/uzytkownik";
    }
    @GetMapping("/zaloguj")
    public String logowanieUzytkownika(Model model)
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
    public String edytujInformacja(@Valid @ModelAttribute("uzytkownicy") UzytkownikEntity uzytkownik, @RequestParam("id") int id, @RequestParam("rolka") String rolka, BindingResult result, Model model) {
        System.out.println("XDDDDD");
        if(result.hasErrors()) {
            model.addAttribute("uzytkownicy",uzytkownikService.getUzytkownicy());
            return "edytuj_uzytkownika";
        }
        UzytkownikEntity uzytkownikDoEdycji = uzytkownikService.findUzytkownik(id);
        uzytkownikDoEdycji.setImie(uzytkownik.getImie());

        uzytkownikDoEdycji.setNazwisko(uzytkownik.getNazwisko());
        uzytkownikDoEdycji.setLogin(uzytkownik.getLogin());
        uzytkownikDoEdycji.setMail(uzytkownik.getMail());
        uzytkownikDoEdycji.setWiek(uzytkownik.getWiek());
        uzytkownikService.nadajRoleEdycja(uzytkownikDoEdycji,rolka);
        uzytkownikService.zapisz(uzytkownikDoEdycji);
        return "redirect:/uzytkownik";
    }
    //@GetMapping("/usun")
    @GetMapping("/usun/{id}")
    public String usuwanko(@PathVariable("id") int id){
        UzytkownikEntity uzytkownikDoEdycji = uzytkownikService.findUzytkownik(id);
        uzytkownikService.usun(uzytkownikDoEdycji);
        return "redirect:/uzytkownik";
    }

//    @GetMapping("/usun")
//    public String usuwanie(Model model, @RequestParam("uzytkownikDoUsuniecia") int id) {
//        UzytkownikEntity uzytkownikDoEdycji = uzytkownikService.findUzytkownik(id);
//        uzytkownikService.usun(uzytkownikDoEdycji);
//        return "redirect:/uzytkownik";
//    }

}
