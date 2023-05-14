package com.example.projekt_studia_java.controller;

import com.example.projekt_studia_java.domain.Informacja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.projekt_studia_java.service.InformacjaService;

import java.util.List;

@Controller
@RequestMapping("/informacja")
public class InformacjaController {
    @Autowired
    InformacjaService serwis;

    @GetMapping("/")
    public String getAllData(Model model) {

        model.addAttribute("informacje", serwis.getInformacjaRepository().getInformacje());
        return "informacja";
    }
    @GetMapping("/sort")
    public String sortowanie(@RequestParam String typ, @RequestParam String direction)
    {
        serwis.sort(typ,direction);
        return "redirect:/informacja/";
    }
}
