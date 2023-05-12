package com.example.projekt_studia_java.controller;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.repository.InformacjaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/informacja")
public class InformacjaController {
    @Autowired
    InformacjaRepository informacjaRepository;

    @GetMapping("/")
    public String getAllData(Model model) {
        model.addAttribute("informacje", informacjaRepository.getInformacje());
        return "informacja";
    }
    @GetMapping("/sort")
    public String sort(Model model, @RequestParam String sort)
    {
        List<Informacja> lista = informacjaRepository.getInformacje();

        switch (sort)
        {
            case "alfabetycznieR":
                lista.sort(java.util.Comparator.comparing(Informacja::getTytul));
                break;
            case "alfabetycznieM":
                lista.sort(java.util.Comparator.comparing(Informacja::getTytul));
                java.util.Collections.reverse(lista);
                break;
            case "kategoriaR":
                lista.sort(java.util.Comparator.comparing(Informacja::getKategoria));
                break;
            case "kategoriaM":
                lista.sort(java.util.Comparator.comparing(Informacja::getKategoria));
                java.util.Collections.reverse(lista);
                break;
        }

        model.addAttribute("informacje", lista);

        return "informacja";
    }


}
