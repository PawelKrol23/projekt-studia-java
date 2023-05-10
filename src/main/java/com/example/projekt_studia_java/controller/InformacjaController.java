package com.example.projekt_studia_java.controller;

import com.example.projekt_studia_java.repository.InformacjaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class InformacjaController {
    private final InformacjaRepository informacjaRepository;

    @GetMapping("/informacja")
    public String getAllData(Model model) {
        model.addAttribute("informacje", informacjaRepository.getInformacje());
        return "informacja";
    }


}
