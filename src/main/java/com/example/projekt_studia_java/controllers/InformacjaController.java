package com.example.projekt_studia_java.controllers;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.repositories.InformacjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.projekt_studia_java.services.InformacjaService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/informacja")
public class InformacjaController {
    @Autowired
    InformacjaService serwis;
    @Autowired
    InformacjaRepository informacjaRepository; //Static error z .zapisz

    @GetMapping
    public String getAllData(Model model, @RequestParam(required = false) String typ, @RequestParam(required = false) String direction) {
        if(typ != null && direction != null)
        {
            serwis.sort(typ,direction);
        }

        model.addAttribute("informacje", serwis.getInformacjaRepository().getInformacje());
        return "informacja";
    }
    @GetMapping("/dodaj")
    public String formularzDodawaniaInformacji(Model model){
        Informacja informacja;
        informacja = new Informacja();
        model.addAttribute("informacje",informacja);
        return "dodaj";
    }
    @PostMapping("/dodaj")
    public String dodajInformacja(@ModelAttribute Informacja informacja){
        informacja.setDataDodania(LocalDateTime.now());
        informacja.setDataPrzypomnienia(LocalDateTime.of(2030,8,26,0,0,0));
        informacjaRepository.zapisz(informacja);
        return "redirect:/informacja";
    }


   /* @GetMapping("/sort")
    public String sortowanie(@RequestParam String typ, @RequestParam String direction)
    {
        serwis.sort(typ,direction);
        return "redirect:/informacja/";
    }*/
}
