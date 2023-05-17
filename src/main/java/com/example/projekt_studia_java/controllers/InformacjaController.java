package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.projekt_studia_java.services.InformacjaService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/informacja")
public class InformacjaController {
    @Autowired
    InformacjaService serwis;
    @Autowired
    InformacjaRepository informacjaRepository; //Static error z .zapisz

    @GetMapping
    public String getAllData(Model model, @RequestParam(required = false) String typ, @RequestParam(required = false) String direction, HttpServletResponse response , HttpServletRequest request) {
        if(typ != null && direction != null)
        {
            Cookie ciacho_typ= new Cookie("typ",typ);
            Cookie ciacho_direction = new Cookie("direction",direction);
            ciacho_typ.setMaxAge(-1);
            ciacho_direction.setMaxAge(-1);
            response.addCookie(ciacho_typ);
            response.addCookie(ciacho_direction);
            serwis.sort(typ,direction);
        }
        else if(request.getCookies()!=null)
        {
            String nazwa;
            Cookie[] tablica = request.getCookies();
            for(var cookie: tablica)
            {
                nazwa=cookie.getName();
                if(nazwa.equals("typ"))
                {
                    typ=cookie.getValue();
                }
                if(nazwa.equals("direction"))
                {
                    direction=cookie.getValue();
                }
            }
            if(typ != null && direction != null)
            {
                serwis.sort(typ, direction);
            }

        }
        model.addAttribute("informacje", serwis.getInformacjaRepository().getInformacje());
        return "informacja";
    }
    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String dataFiltrowania)
    {
        List<Informacja> lista = serwis.filter(dataFiltrowania);
        model.addAttribute("informacje",lista);
        return "informacja";
    }
    @GetMapping("/dodaj")
    public String formularzDodawaniaInformacji(Model model){
        Informacja informacja;
        informacja = new Informacja();
        model.addAttribute("informacje",informacja);
        model.addAttribute("kategorie",informacjaRepository.getKategorie());
        return "dodaj";
    }
    @PostMapping("/dodaj")
    public String dodajInformacja(@ModelAttribute Informacja informacja){
        informacja.setDataDodania(LocalDateTime.now().withSecond(0).withNano(0));
        informacja.setDataPrzypomnienia(LocalDateTime.of(2030,8,26,0,0,0));
        informacjaRepository.zapisz(informacja);
        return "redirect:/informacja";
    }
}
