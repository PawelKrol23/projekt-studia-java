package com.example.projekt_studia_java.data;

import com.example.projekt_studia_java.domain.Informacja;
import com.example.projekt_studia_java.domain.Kategoria;
import com.example.projekt_studia_java.repositories.InformacjaRepositoryJPA;
import com.example.projekt_studia_java.repositories.KategoriaRepositoryJPA;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final InformacjaRepositoryJPA informacjaRepositoryJPA;
    private final KategoriaRepositoryJPA kategoriaRepositoryJPA;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        String[] kategorie = {
            "motoryzacja",
            "sport",
            "jedzenie",
            "moda",
            "podróże",
            "sztuka",
            "biznes",
            "technologia",
            "gry i rozrywka",
            "dom i ogród"
        };

        for(String nazwa: kategorie) {
            Kategoria kategoria = Kategoria.builder()
                    .nazwa(nazwa)
                    .build();

            kategoriaRepositoryJPA.save(kategoria);
        }

        Informacja informacja1 = Informacja.builder()
                .tytul("piłka")
                .kategoria(kategoriaRepositoryJPA.findById(2).get())
                .tresc("okrągła piłka")
                .link("www.link.com")
                .dataDodania(LocalDateTime.of(2023, 1, 12, 0, 0, 0))
                .dataPrzypomnienia(LocalDateTime.of(2021, 6, 12, 0, 0, 0))
                .build();

        Informacja informacja2 = Informacja.builder()
                .tytul("obraz")
                .kategoria(kategoriaRepositoryJPA.findById(6).get())
                .tresc("ładny obraz")
                .link("www.inny_link.com")
                .dataDodania(LocalDateTime.of(2023, 4, 12, 0, 0, 0))
                .dataPrzypomnienia(LocalDateTime.of(2023, 6, 12, 0, 0, 0))
                .build();

        Informacja informacja3 = Informacja.builder()
                .tytul("batonik")
                .kategoria(kategoriaRepositoryJPA.findById(3).get())
                .tresc("przyszny batonik")
                .link("www.zupełnie_inny_link.com")
                .dataDodania(LocalDateTime.of(2023, 4, 30, 0, 0, 0))
                .dataPrzypomnienia(LocalDateTime.of(2022, 4, 12, 0, 0, 0))
                .build();

        Informacja informacja4 = Informacja.builder()
                .tytul("samochód")
                .kategoria(kategoriaRepositoryJPA.findById(1).get())
                .tresc("szybki samochód")
                .link("www.kompletnie_inny_link.com")
                .dataDodania(LocalDateTime.of(2022, 5, 12, 0, 0, 0))
                .dataPrzypomnienia(LocalDateTime.of(2022, 6, 12, 0, 0, 0))
                .build();

        informacjaRepositoryJPA.saveAll(Arrays.asList(informacja1, informacja2, informacja3, informacja4));
    }
}
