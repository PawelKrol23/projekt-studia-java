package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.services.InformacjaService;
import com.example.projekt_studia_java.services.KategoriaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/informacja")
@RequiredArgsConstructor
public class InformacjaController {
    private final InformacjaService informacjaService;
    private final KategoriaService kategoriaService;

    @GetMapping
    public String getData(Model model,
                         @RequestParam(required = false) String dataFiltrowania,
                         @RequestParam(required = false) String kategoriaFiltrowania,
                         @RequestParam(required = false) String typ,
                         @RequestParam(required = false) String direction,
                         HttpServletResponse response,
                         HttpServletRequest request)
    {
        // Ustaw ciasteczka do responsa
        if(typ != null) {
            Cookie cookie = new Cookie("typ", typ);
            cookie.setMaxAge(-1);
            cookie.setPath("/informacja");
            response.addCookie(cookie);
        }

        if(direction != null) {
            Cookie cookie = new Cookie("direction", direction);
            cookie.setMaxAge(-1);
            cookie.setPath("/informacja");
            response.addCookie(cookie);
        }

        if(dataFiltrowania != null) {
            Cookie cookie = new Cookie("dataFiltrowania", dataFiltrowania);
            cookie.setMaxAge(-1);
            cookie.setPath("/informacja");
            response.addCookie(cookie);
        }

        if(kategoriaFiltrowania != null) {
            Cookie cookie = new Cookie("kategoriaFiltrowania", kategoriaFiltrowania);
            cookie.setMaxAge(-1);
            cookie.setPath("/informacja");
            response.addCookie(cookie);
        }

        // Odczytaj ciasteczka, jeżeli nie zostały podane
        Cookie[] cookies = request.getCookies() == null ? new Cookie[0] : request.getCookies();
        for(var cookie : cookies) {
            if(cookie.getName().equals("typ") && typ == null) {
                typ = cookie.getValue();
            }

            if(cookie.getName().equals("direction") && direction == null) {
                direction = cookie.getValue();
            }

            if(cookie.getName().equals("dataFiltrowania") && dataFiltrowania == null) {
                dataFiltrowania = cookie.getValue();
            }

            if(cookie.getName().equals("kategoriaFiltrowania") && kategoriaFiltrowania == null) {
                kategoriaFiltrowania = cookie.getValue();
            }
        }

        // Ustaw domyślne wartości jeżeli null i wstaw je do modelu
        if(typ == null) {
            typ = "brak";
        }
        model.addAttribute("wybranyTyp", typ);

        if(direction == null) {
            direction = "brak";
        }
        model.addAttribute("wybranyDirection", direction);

        if(dataFiltrowania == null) {
            dataFiltrowania = "zawsze";
        }
        model.addAttribute("wybranaData", dataFiltrowania);

        if(kategoriaFiltrowania == null) {
            kategoriaFiltrowania = "wszystkie";
        }
        model.addAttribute("wybranaKategoria", kategoriaFiltrowania);

        // Pobierz dane z bazy danych
        model.addAttribute("kategorie", informacjaService.sortKategoria());
        model.addAttribute("informacje", informacjaService.sortFilterInformacje(typ, direction, kategoriaFiltrowania, dataFiltrowania));
        return "informacja";
    }
    @GetMapping("/dodaj")
    public String formularzDodawaniaInformacji(Model model) {
        model.addAttribute("kategorie",kategoriaService.getKategorie());
        model.addAttribute("informacja", new Informacja());
        return "dodaj";
    }
    @PostMapping("/dodaj")
    public String dodajInformacja(@Valid @ModelAttribute("informacja") Informacja informacja, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("kategorie",kategoriaService.getKategorie());
            return "dodaj";
        }

        informacjaService.zapisz(informacja);
        return "redirect:/informacja";
    }
    @GetMapping("/edytuj")
    public String formularzEdycjiInformacji(Model model, @RequestParam("informacjaDoEdycji") int id) {
        model.addAttribute("kategorie",kategoriaService.getKategorie());

        InformacjaEntity informacjaEntity = informacjaService.findInformacja(id);

        Informacja informacja = Informacja.builder()
                .dataPrzypomnienia(informacjaEntity.getDataPrzypomnienia())
                .link(informacjaEntity.getLink())
                .tresc(informacjaEntity.getTresc())
                .tytul(informacjaEntity.getTytul())
                .kategoria(informacjaEntity.getKategoria().getNazwa())
                .build();

        model.addAttribute("informacja", informacja);
        model.addAttribute("id", id);

        return "edytuj";
    }
    @PostMapping("/edytuj")
    public String edytujInformacja(@Valid @ModelAttribute("informacja") Informacja informacja, @RequestParam("id") int id, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("kategorie",kategoriaService.getKategorie());
            return "edytuj";
        }

        InformacjaEntity informacjaDoEdycji = informacjaService.findInformacja(id);
        informacjaService.usun(informacjaDoEdycji);
        informacjaService.zapisz(informacja);

        return "redirect:/informacja";
    }
    @GetMapping("/usun")
    public String usuwanie(Model model, @RequestParam("informacjaDoUsuniecia") int id) {

        InformacjaEntity informacjaDoEdycji = informacjaService.findInformacja(id);
        informacjaService.usun(informacjaDoEdycji);

        return "redirect:/informacja";
    }
}
