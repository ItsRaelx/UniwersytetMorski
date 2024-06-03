# Notatka dotycząca podstaw SQL na podstawie powyższych przykładów

SQL (Structured Query Language) jest językiem używanym do zarządzania i manipulowania bazami danych. Poniższe przykłady i wyjaśnienia obejmują różne aspekty SQL, które pozwalają na tworzenie, modyfikowanie i usuwanie tabel, wstawianie danych, wykonywanie kwerend, łączenie tabel, wykonywanie obliczeń oraz używanie funkcji.

## Tworzenie tabel

### Przykład tworzenia tabeli

```sql
CREATE TABLE osoby (
  user_id SERIAL PRIMARY KEY, 
  nazwisko VARCHAR(50) NOT NULL, 
  imie VARCHAR(50) NOT NULL, 
  wiek INT
);
```

- `CREATE TABLE` - polecenie tworzenia nowej tabeli.
- `user_id SERIAL PRIMARY KEY` - kolumna `user_id`, która automatycznie inkrementuje się przy każdym nowym wierszu i jest kluczem głównym.
- `nazwisko VARCHAR(50) NOT NULL` - kolumna `nazwisko` typu tekstowego (maksymalnie 50 znaków), która nie może być pusta.
- `imie VARCHAR(50) NOT NULL` - kolumna `imie` typu tekstowego (maksymalnie 50 znaków), która nie może być pusta.
- `wiek INT` - kolumna `wiek` typu całkowitego.

### Wyświetlanie zawartości tabeli

```sql
SELECT * FROM osoby;
```

- `SELECT *` - wybiera wszystkie kolumny.
- `FROM osoby` - z tabeli `osoby`.

### Wstawianie rekordów

```sql
INSERT INTO osoby (user_id, nazwisko, imie, wiek) VALUES
    (1, 'Kowalski', 'Jan', 25),
    (2, 'Malinowska', 'Maria', 30),
    (3, 'Nowicki', 'Karol', 35),
    (4, 'Nowicka', 'Zofia', 40);
```

- `INSERT INTO osoby (kolumny)` - wstawia dane do tabeli `osoby` w podanych kolumnach.
- `VALUES` - określa wartości do wstawienia.

### Autoincrement

Wstawianie rekordów z kolumną `SERIAL` (automatyczny inkrement):

```sql
CREATE TABLE osoby1 (
  user_id SERIAL PRIMARY KEY, 
  nazwisko VARCHAR(50) NOT NULL, 
  imie VARCHAR(50) NOT NULL, 
  wiek INT
);

INSERT INTO osoby1 (nazwisko, imie, wiek) VALUES
    ('Kowalski', 'Jan', 25),
    ('Malinowska', 'Maria', 30);
```

Kolumna `user_id` zostanie automatycznie wygenerowana.

### Kwerendy podstawowe

```sql
SELECT imie, nazwisko FROM osoby1;
SELECT imie AS name, nazwisko AS surname FROM osoby1;
SELECT * FROM osoby1 WHERE user_id = 2;
SELECT * FROM osoby1 WHERE user_id != 2;
```

- `SELECT imie, nazwisko` - wybiera konkretne kolumny `imie` i `nazwisko`.
- `AS` - alias (zmiana nazwy kolumny w wyniku).
- `WHERE` - warunek filtrowania.

## Modyfikacja tabel

### Dodawanie kolumny

```sql
ALTER TABLE osoby ADD data_osoby DATE;
```

- `ALTER TABLE` - modyfikacja struktury tabeli.
- `ADD data_osoby DATE` - dodanie kolumny `data_osoby` typu `DATE`.

### Aktualizacja rekordów

```sql
UPDATE osoby 
SET data_osoby = '2020-08-01' 
WHERE user_id = 1;
```

- `UPDATE` - aktualizacja danych w tabeli.
- `SET` - ustawienie nowych wartości.
- `WHERE` - określenie, które wiersze mają być zaktualizowane.

### Usuwanie kolumny

```sql
ALTER TABLE osoby DROP COLUMN netto;
```

- `DROP COLUMN netto` - usunięcie kolumny `netto`.

### Usuwanie rekordów

```sql
DELETE FROM osoby_kopia WHERE user_id = 1;
DELETE FROM osoby_kopia WHERE user_id IN (2, 4);
DELETE FROM osoby_kopia WHERE user_id BETWEEN 2 AND 4;
```

- `DELETE FROM` - usunięcie rekordów z tabeli.
- `IN` - wybór rekordów z określonymi wartościami.
- `BETWEEN` - wybór rekordów w zakresie wartości.

## Zaawansowane kwerendy

### Łączenie tabel (JOIN)

```sql
CREATE TABLE miasta (
  user_id SERIAL PRIMARY KEY, 
  nazwisko VARCHAR(50) NOT NULL, 
  miasto VARCHAR(50) NOT NULL
);

CREATE TABLE wojewodztwa (
  user_id SERIAL PRIMARY KEY, 
  nazwisko VARCHAR(50) NOT NULL, 
  wojewodztwo VARCHAR(50) NOT NULL
);

INSERT INTO miasta (nazwisko, miasto) VALUES
    ('Nowicki', 'Gdańsk'),
    ('Kowalski', 'Katowice'),
    ('Nowicka', 'Szczecin'),
    ('Malinowska', 'Kraków');

INSERT INTO wojewodztwa (nazwisko, wojewodztwo) VALUES
    ('Kowalski', 'śląskie'),
    ('Nowicki', 'pomorskie'),
    ('Nowicka', 'zachodniopomorskie'),
    ('Malinowska', 'małopolskie');
```

```sql
SELECT osoby.nazwisko, wiek, miasto
FROM osoby
INNER JOIN miasta ON osoby.nazwisko = miasta.nazwisko;
```

- `INNER JOIN` - łączenie tabel zwracające tylko pasujące rekordy.
- `ON` - warunek łączenia.

### Obliczenia w kwerendach

```sql
SELECT nazwisko, ROUND((netto * 1.23)::NUMERIC, 2) AS "brutto" FROM osoby;
```

- `ROUND` - zaokrąglanie wartości.
- `::NUMERIC` - konwersja typu danych.

### Widoki

```sql
CREATE VIEW widok1 AS
SELECT osoby.nazwisko, wiek, miasto
FROM osoby
INNER JOIN miasta ON osoby.nazwisko = miasto.nazwisko;

SELECT * FROM widok1;
```

- `CREATE VIEW` - tworzenie widoku, który jest zapisanym zapytaniem.
- `SELECT * FROM widok1` - używanie widoku jak tabeli.

### Operacje na dacie

```sql
SELECT CURRENT_DATE;
SELECT NOW()::DATE;

SELECT DATE_PART('Year', CURRENT_DATE);
SELECT AGE(CURRENT_DATE, '2020-02-20');

SELECT nazwisko,
    EXTRACT(YEAR FROM data_osoby) AS "rok",
    EXTRACT(MONTH FROM data_osoby) AS "m-c",
    EXTRACT(DAY FROM data_osoby) AS "dzień"
FROM osoby;
```

- `CURRENT_DATE` - bieżąca data.
- `NOW()` - bieżąca data i czas.
- `DATE_PART` i `EXTRACT` - wyodrębnianie części daty.
- `AGE` - różnica między datami.

## Funkcje agregujące

```sql
SELECT ROUND(AVG(netto)::NUMERIC, 2) AS "średnia netto" FROM osoby;
SELECT COUNT(*) AS "ile rekordów" FROM osoby;
SELECT MIN(netto) AS "min." FROM osoby;
SELECT MAX(netto) AS "max." FROM osoby;
SELECT SUM(netto) AS "suma" FROM osoby;
```

- `AVG` - średnia.
- `COUNT` - liczba rekordów.
- `MIN` - minimalna wartość.
- `MAX` - maksymalna wartość.
- `SUM` - suma wartości.

## Bloki kodu (DO)

```sql
DO $$ 
DECLARE 
    ile INT;
BEGIN
    SELECT COUNT(*) 
    INTO ile
    FROM osoby;
    RAISE NOTICE 'ilość rekordów: %', ile;
END $$;
```

- `DO $$ ... $$` - anonimowy blok kodu.
- `DECLARE` - deklaracja zmiennych.
- `BEGIN ... END` - blok kodu do wykonania.
- `RAISE NOTICE` - wyświetlanie komunikatów.

## Grupowanie danych

### Przykład grupowania

```sql
SELECT COUNT(*) AS "ilość mieszkańców", miasto 
FROM miasta
GROUP BY miasto;

SELECT AVG(wiek) AS "wiek średni", miasto 
FROM miasta
INNER JOIN osoby ON miasta.nazwisko = osoby.nazwisko
GROUP BY miasto;
```

- `GROUP BY` - grupowanie danych według kolumn.
- `HAVING` - warunki na grupach danych.

## Funkcje użytkownika

### Przykład funkcji

```sql
CREATE OR REPLACE FUNCTION f1() RETURNS INT AS
$$
    SELECT COUNT(*) FROM osoby;
$$ LANGUAGE SQL;

SELECT f1();
```

- `CREATE OR REPLACE FUNCTION` - tworzenie funkcji.
- `RETURNS` - typ

 zwracanej wartości.
- `LANGUAGE SQL` - język implementacji funkcji.

### Funkcja zwracająca rekordy

```sql
CREATE OR REPLACE FUNCTION f1() RETURNS SETOF osoby AS 
$$
    SELECT * FROM osoby;
$$ LANGUAGE SQL;

SELECT * FROM f1();
```

- `RETURNS SETOF` - funkcja zwraca wiele rekordów.

Powyższe przykłady przedstawiają podstawowe operacje SQL, które można wykonywać na bazach danych. Każdy z tych tematów można zgłębić, aby lepiej zrozumieć i efektywnie używać SQL w codziennej pracy z bazami danych.
