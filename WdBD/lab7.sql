DROP TABLE Rejestracja;
DROP TABLE Mieszkanie;
DROP TABLE Dochody;

CREATE TABLE Rejestracja (
    Lp INT PRIMARY KEY,
    Data DATE NOT NULL,
    Nazwisko VARCHAR(50) NOT NULL
);

CREATE TABLE Mieszkanie (
    Lp INT PRIMARY KEY,
    Nazwisko VARCHAR(50) NOT NULL,
    Miasto VARCHAR(50) NOT NULL
);

CREATE TABLE Dochody (
    Lp INT PRIMARY KEY,
    Nazwisko VARCHAR(50) NOT NULL,
    Brutto DECIMAL(10, 2) NOT NULL
);

INSERT INTO Rejestracja (Lp, Data, Nazwisko) VALUES
(1, '1998-09-01', 'Kowalski'),
(2, '1991-10-09', 'Malinowska'),
(3, '2001-02-09', 'Nowak'),
(4, '2002-03-12', 'Kowalewski');

INSERT INTO Mieszkanie (Lp, Nazwisko, Miasto) VALUES
(1, 'Kowalski', 'Sopot'),
(2, 'Nowak', 'Gdańsk'),
(3, 'Malinowska', 'Gdańsk'),
(4, 'Kowalewski', 'Gdynia');

INSERT INTO Dochody (Lp, Nazwisko, Brutto) VALUES
(1, 'Kowalski', 8120),
(2, 'Malinowska', 7891),
(3, 'Nowak', 9882),
(4, 'Kowalewski', 6789);

-- Zad 2. Średnie zarobki wyżej niż minimalne w Gdańsku
DROP FUNCTION IF EXISTS wyswietl_zarobki_wyzej_min_gdansk();
CREATE OR REPLACE FUNCTION wyswietl_zarobki_wyzej_min_gdansk()
RETURNS SETOF Dochody AS
$$
SELECT * FROM Dochody WHERE Brutto / 1.24 > (
    SELECT MIN(Brutto / 1.24) FROM Dochody
    JOIN Mieszkanie ON Dochody.Nazwisko = Mieszkanie.Nazwisko
    WHERE Mieszkanie.Miasto = 'Gdańsk'
)
$$ LANGUAGE SQL;

SELECT*FROM wyswietl_zarobki_wyzej_min_gdansk();

-- Zad 3. Osoba zarabiająca najwięcej
DROP FUNCTION IF EXISTS osoba_zarabiajaca_najwiecej();
CREATE OR REPLACE FUNCTION osoba_zarabiajaca_najwiecej()
RETURNS SETOF VARCHAR(50) AS
$$
SELECT Nazwisko FROM Dochody ORDER BY Brutto DESC LIMIT 1;
$$ LANGUAGE SQL;

SELECT*FROM osoba_zarabiajaca_najwiecej();

-- Zad 4. Osoba zarabiająca najwięcej w Gdańsku
DROP FUNCTION IF EXISTS osoba_zarabiajaca_najwiecej_w_gdansku();
CREATE OR REPLACE FUNCTION osoba_zarabiajaca_najwiecej_w_gdansku()
RETURNS SETOF VARCHAR(50) AS
$$
SELECT Dochody.Nazwisko FROM Dochody
JOIN Mieszkanie ON Dochody.Nazwisko = Mieszkanie.Nazwisko
WHERE Mieszkanie.Miasto = 'Gdańsk'
ORDER BY Dochody.Brutto DESC LIMIT 1;
$$ LANGUAGE SQL;

SELECT*FROM osoba_zarabiajaca_najwiecej_w_gdansku();

-- Zad 5. Średnie zarobki brutto w Gdańsku
DROP FUNCTION IF EXISTS srednie_zarobki_brutto_gdansk();
CREATE OR REPLACE FUNCTION srednie_zarobki_brutto_gdansk()
RETURNS SETOF DECIMAL(10, 2) AS
$$
SELECT AVG(Brutto) FROM Dochody
JOIN Mieszkanie ON Dochody.Nazwisko = Mieszkanie.Nazwisko
WHERE Mieszkanie.Miasto = 'Gdańsk';
$$ LANGUAGE SQL;

SELECT*FROM srednie_zarobki_brutto_gdansk();

-- Zad 6. Średnie zarobki netto w Gdańsku
DROP FUNCTION IF EXISTS srednie_zarobki_netto_gdansk();
CREATE OR REPLACE FUNCTION srednie_zarobki_netto_gdansk()
RETURNS SETOF DECIMAL(10, 2) AS
$$
SELECT AVG(Brutto / 1.24) FROM Dochody
JOIN Mieszkanie ON Dochody.Nazwisko = Mieszkanie.Nazwisko
WHERE Mieszkanie.Miasto = 'Gdańsk';
$$ LANGUAGE SQL;

SELECT*FROM srednie_zarobki_netto_gdansk();

-- Zad 7. Podwyższenie pensji w Gdańsku
DROP FUNCTION IF EXISTS podwyzsz_pensje_gdansk();
CREATE OR REPLACE FUNCTION podwyzsz_pensje_gdansk()
RETURNS VOID AS
$$
UPDATE Dochody SET Brutto = Brutto * 1.005
WHERE EXISTS (
    SELECT 1 FROM Mieszkanie
    WHERE Dochody.Nazwisko = Mieszkanie.Nazwisko AND Mieszkanie.Miasto = 'Gdańsk'
);
$$ LANGUAGE SQL;

SELECT*FROM podwyzsz_pensje_gdansk();

-- Zad 8. Liczba mieszkańców w miastach
DROP FUNCTION IF EXISTS liczba_mieszkancow();
CREATE OR REPLACE FUNCTION liczba_mieszkancow()
RETURNS TEXT AS
$$
SELECT Miasto, COUNT(*) FROM Mieszkanie GROUP BY Miasto;
$$ LANGUAGE SQL;

SELECT*FROM liczba_mieszkancow();
