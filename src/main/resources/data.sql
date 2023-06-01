
-- Kategorie
INSERT INTO Kategoria (nazwa) VALUES ('Motoryzacja');
INSERT INTO Kategoria (nazwa) VALUES ('Sport');
INSERT INTO Kategoria (nazwa) VALUES ('Jedzenie');
INSERT INTO Kategoria (nazwa) VALUES ('Moda');
INSERT INTO Kategoria (nazwa) VALUES ('Podróże');
INSERT INTO Kategoria (nazwa) VALUES ('Sztuka');
INSERT INTO Kategoria (nazwa) VALUES ('Biznes');
INSERT INTO Kategoria (nazwa) VALUES ('Technologia');
INSERT INTO Kategoria (nazwa) VALUES ('Gry i rozrywka');
INSERT INTO Kategoria (nazwa) VALUES ('Dom i ogród');

-- Informacje
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2023-01-12 00:00:00', '2021-06-12 00:00:00', 'www.link.com', 'okrągła piłka', 'piłka', SELECT id FROM kategoria WHERE nazwa = 'Sport');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2023-04-12 00:00:00', '2023-06-12 00:00:00', 'www.inny_link.com', 'ładny obraz', 'obraz', SELECT id FROM kategoria WHERE nazwa = 'Sztuka');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2023-04-30 00:00:00', '2022-04-12 00:00:00', 'www.zupełnie_inny_link.com', 'przyszny batonik', 'batonik', SELECT id FROM kategoria WHERE nazwa = 'Jedzenie');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2022-05-12 00:00:00', '2022-06-12 00:00:00', 'www.kompletnie_inny_link.com', 'szybki samochód', 'samochód', SELECT id FROM kategoria WHERE nazwa = 'Motoryzacja');

-- Uzytkownicy
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('szef', 'szefu', 'admin', 'admin', 'mail@admina.pl', 28);