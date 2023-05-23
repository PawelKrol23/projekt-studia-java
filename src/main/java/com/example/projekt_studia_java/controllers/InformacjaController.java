package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.db.InformacjaEntity;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import com.example.projekt_studia_java.services.InformacjaService;
import com.example.projekt_studia_java.services.KategoriaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/informacja")
@RequiredArgsConstructor
public class InformacjaController {
    private final InformacjaService informacjaService;
    private final InformacjaRepository informacjaRepository; //Static error z .zapisz
    private final KategoriaService kategoriaService;

    @GetMapping
    public String getAllData(Model model,
                             @RequestParam(required = false) String typ,
                             @RequestParam(required = false) String direction,
                             HttpServletResponse response,
                             HttpServletRequest request) {
        model.addAttribute("informacje", informacjaService.getInformacje());
        return "informacja";
    }
    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String dataFiltrowania, @RequestParam String typ, @RequestParam String direction, HttpServletResponse response , HttpServletRequest request)
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

        List<InformacjaEntity> lista = informacjaService.filter(dataFiltrowania);
        lista = informacjaService.sort(typ, direction,lista);
        model.addAttribute("informacje",lista);
        return "informacja";
    }
    @GetMapping("/dodaj")
    public String formularzDodawaniaInformacji(Model model){
        model.addAttribute("kategorie",kategoriaService.getKategorie());
        return "dodaj";
    }
    @PostMapping("/dodaj")
    public String dodajInformacja(@ModelAttribute Informacja informacja){
//        List<Kategoria> lista = kategoriaService.getKategorie();
//        Kategoria kategoria1 = null;
//        for(Kategoria element : lista)
//        {
//            if(Objects.equals(element.getNazwa(), kategoria))
//            {
//                kategoria1 = element;
//            }
//        }
//        informacjaRepository.zapisz(new Informacja(null,tytul,kategoria1,tresc,link,LocalDateTime.now().withSecond(0).withNano(0),LocalDateTime.of(2030,8,26,0,0,0)));
        System.out.println(informacja);
        informacjaService.zapisz(informacja);
        return "redirect:/informacja";
    }
}
