
-- Kategorie
INSERT INTO Kategoria (nazwa) VALUES ('motoryzacja');
INSERT INTO Kategoria (nazwa) VALUES ('sport');
INSERT INTO Kategoria (nazwa) VALUES ('jedzenie');
INSERT INTO Kategoria (nazwa) VALUES ('moda');
INSERT INTO Kategoria (nazwa) VALUES ('podróże');
INSERT INTO Kategoria (nazwa) VALUES ('sztuka');
INSERT INTO Kategoria (nazwa) VALUES ('biznes');
INSERT INTO Kategoria (nazwa) VALUES ('technologia');
INSERT INTO Kategoria (nazwa) VALUES ('gry');
INSERT INTO Kategoria (nazwa) VALUES ('dom');

-- Informacje
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2023-01-12 00:00:00', '2021-06-12 00:00:00', 'www.link.com', 'okrągła piłka', 'piłka', SELECT id FROM kategoria WHERE nazwa = 'sport');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2023-04-12 00:00:00', '2023-06-12 00:00:00', 'www.inny_link.com', 'ładny obraz', 'obraz', SELECT id FROM kategoria WHERE nazwa = 'sztuka');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2023-04-30 00:00:00', '2022-04-12 00:00:00', 'www.zupełnie_inny_link.com', 'przyszny batonik', 'batonik', SELECT id FROM kategoria WHERE nazwa = 'jedzenie');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id)
VALUES ('2022-05-12 00:00:00', '2022-06-12 00:00:00', 'www.kompletnie_inny_link.com', 'szybki samochód', 'samochód', SELECT id FROM kategoria WHERE nazwa = 'motoryzacja');

-- Uzytkownicy
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('szef', 'szefu', 'admin', 'admin', 'mail@admina.pl', 28);