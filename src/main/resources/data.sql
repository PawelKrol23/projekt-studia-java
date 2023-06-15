
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

-- Uzytkownicy
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('Szef', 'Szefu', 'admin', 'admin', 'mail@admina.pl', 28);
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('Adminek', 'Adminekv2', 'admin2', 'admin2', 'mail@admina2.pl', 20);
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('Szefito', 'Szefito', 'user', 'user', 'mail@user.pl', 20);
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('Szefi', 'Szefi', 'user2', 'user2', 'innymail@user.pl', 21);
INSERT INTO Uzytkownik (imie, nazwisko, login, haslo, mail, wiek)
VALUES ('Jestem_weak', 'Weaked', 'user_weak', 'user_weak', 'mail@user.pl', 29);

--Role
INSERT INTO Role (rola, Uzytkownik_id)
VALUES ('ADMIN', SELECT id FROM Uzytkownik WHERE login = 'admin');
INSERT INTO Role (rola, Uzytkownik_id)
VALUES ('ADMIN', SELECT id FROM Uzytkownik WHERE login = 'admin2');
INSERT INTO Role (rola, Uzytkownik_id)
VALUES ('USER', SELECT id FROM Uzytkownik WHERE login = 'user');
INSERT INTO Role (rola, Uzytkownik_id)
VALUES ('USER', SELECT id FROM Uzytkownik WHERE login = 'user2');
INSERT INTO Role (rola, Uzytkownik_id)
VALUES ('USER_WEAK', SELECT id FROM Uzytkownik WHERE login = 'user_weak');

-- Informacje
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id, Uzytkownik_id)
VALUES ('2023-01-12 00:00:00', '2021-06-12', 'www.link.com', 'okrągła piłka', 'piłka', SELECT id FROM kategoria WHERE nazwa = 'sport', SELECT id FROM uzytkownik WHERE login = 'user');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id, Uzytkownik_id)
VALUES ('2023-04-12 00:00:00', '2023-06-12', 'www.inny_link.com', 'ładny obraz', 'obraz', SELECT id FROM kategoria WHERE nazwa = 'sztuka', SELECT id FROM uzytkownik WHERE login = 'user');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id, Uzytkownik_id)
VALUES ('2023-04-30 00:00:00', '2022-04-12', 'www.zupełnie_inny_link.com', 'przyszny batonik', 'batonik', SELECT id FROM kategoria WHERE nazwa = 'jedzenie', SELECT id FROM uzytkownik WHERE login = 'user2');
INSERT INTO Informacja (data_dodania, data_przypomnienia, link, tresc, tytul, kategoria_id, Uzytkownik_id)
VALUES ('2022-05-12 00:00:00', '2022-06-12', 'www.kompletnie_inny_link.com', 'szybki samochód', 'samochód', SELECT id FROM kategoria WHERE nazwa = 'motoryzacja', SELECT id FROM uzytkownik WHERE login = 'user2');