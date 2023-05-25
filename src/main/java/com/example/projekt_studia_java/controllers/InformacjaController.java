package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.services.InformacjaService;
import com.example.projekt_studia_java.services.KategoriaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/informacja")
@RequiredArgsConstructor
public class InformacjaController {
    private final InformacjaService informacjaService;
    private final KategoriaService kategoriaService;

    @GetMapping
    public String getAllData(Model model)
    {
        model.addAttribute("kategorie",informacjaService.sortKategoria());
        model.addAttribute("informacje",informacjaService.getInformacje());
        return "informacja";
    }
    @GetMapping("/filter")
    public String filter(Model model,
                         @RequestParam String dataFiltrowania,
                         @RequestParam String kategoriaFiltrowania,
                         @RequestParam String typ,
                         @RequestParam String direction,
                         HttpServletResponse response,
                         HttpServletRequest request)
    {
        Cookie ciacho_typ= new Cookie("typ",typ);
        Cookie ciacho_direction = new Cookie("direction",direction);
        Cookie ciacho_dataFiltrowania = new Cookie("dataFiltrowania",dataFiltrowania);
        ciacho_typ.setMaxAge(-1);
        ciacho_direction.setMaxAge(-1);
        ciacho_dataFiltrowania.setMaxAge(-1);
        response.addCookie(ciacho_typ);
        response.addCookie(ciacho_direction);
        response.addCookie(ciacho_dataFiltrowania);

        List<InformacjaEntity> lista = informacjaService.filter(dataFiltrowania, kategoriaFiltrowania);
        lista = informacjaService.sort(typ, direction,lista);

        model.addAttribute("kategorie",informacjaService.sortKategoria());
        model.addAttribute("informacje",lista);
        return "informacja";
    }
    @GetMapping("/dodaj")
    public String formularzDodawaniaInformacji(Model model) {
        model.addAttribute("kategorie",kategoriaService.getKategorie());
        return "dodaj";
    }
    @PostMapping("/dodaj")
    public String dodajInformacja(@ModelAttribute Informacja informacja) {
        informacjaService.zapisz(informacja);
        return "redirect:/informacja";
    }
}
