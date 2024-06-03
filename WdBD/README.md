# Notatka dotycząca podstaw SQL

SQL (Structured Query Language) jest językiem używanym do zarządzania i manipulowania bazami danych. Poniżej znajduje się szczegółowy przegląd podstawowych zagadnień SQL, takich jak tworzenie i modyfikowanie tabel, wstawianie i usuwanie danych, wykonywanie kwerend, łączenie tabel, obliczenia, widoki, funkcje użytkownika, grupowanie danych oraz typy danych.

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

### Typy danych w SQL

- `INT` - liczba całkowita.
- `SERIAL` - liczba całkowita autoinkrementowana.
- `VARCHAR(n)` - ciąg znaków o zmiennej długości, maksymalnie `n` znaków.
- `TEXT` - długi ciąg znaków.
- `DATE` - data (rok, miesiąc, dzień).
- `FLOAT` - liczba zmiennoprzecinkowa.
- `NUMERIC` - liczba o stałej precyzji.
- `BOOLEAN` - wartość logiczna (TRUE lub FALSE).

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

Widoki w SQL to zapisane zapytania, które można traktować jak tabele. Są one przydatne do skomplikowanych zapytań, które często się powtarzają.

#### Tworzenie widoku

```sql
CREATE VIEW widok1 AS
SELECT osoby.nazwisko, wiek, miasto
FROM osoby
INNER JOIN miasta ON osoby.nazwisko = miasta.nazwisko;
```

- `CREATE VIEW` - tworzenie widoku.
- `AS` - definiowanie zapytania, które tworzy widok.

#### Używanie widoku

```sql
SELECT * FROM widok1;
```

- `SELECT * FROM widok1` - wybieranie danych z widoku.

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

Bloki kodu `DO` pozwalają na wykonywanie bardziej skomplikowanych operacji, takich jak używanie zmiennych, wywoływanie funkcji i operacje warunkowe.

```sql
DO $$ 
DECLARE 
    ile INT;
BEGIN
    SELECT COUNT(*) 
    INTO ile
    FROM osoby;
    RAISE NOTICE 'ilość rekord

ów: %', ile;
END $$;
```

- `DO $$` - początek bloku kodu.
- `DECLARE` - deklaracja zmiennych.
- `BEGIN ... END` - blok kodu do wykonania.
- `SELECT ... INTO` - przypisanie wartości do zmiennej.
- `RAISE NOTICE` - wyświetlenie komunikatu.

## Grupowanie danych

```sql
SELECT COUNT(*) AS "ilość mieszkańców", miasto 
FROM miasta
GROUP BY miasto;

SELECT AVG(wiek) AS "wiek średni", miasto 
FROM miasta
INNER JOIN osoby ON miasta.nazwisko = osoby.nazwisko
GROUP BY miasto;
```

- `GROUP BY` - grupowanie wyników według kolumn.

## Funkcje użytkownika

Funkcje użytkownika pozwalają na tworzenie niestandardowych funkcji w SQL, które mogą wykonywać różnorodne operacje na danych.

### Przykład funkcji aktualizującej tabelę

```sql
CREATE OR REPLACE FUNCTION f1() RETURNS void AS
$$
    UPDATE osoby SET nazwisko = 'Kowalski' WHERE user_id = 1;
$$ LANGUAGE SQL;

SELECT f1();
```

- `CREATE OR REPLACE FUNCTION` - tworzenie lub zastępowanie funkcji.
- `RETURNS void` - funkcja nie zwraca wartości.
- `LANGUAGE SQL` - język implementacji funkcji.

### Przykład funkcji zwracającej wartość

```sql
CREATE OR REPLACE FUNCTION f1(naz TEXT) RETURNS INT AS
$$
    SELECT netto FROM osoby WHERE nazwisko = naz;
$$ LANGUAGE SQL;

SELECT f1('Malinowska');
```

- `RETURNS INT` - funkcja zwraca wartość typu `INT`.
- `TEXT` - typ argumentu wejściowego.

### Przykład funkcji zwracającej rekordy

```sql
CREATE OR REPLACE FUNCTION f1() RETURNS SETOF osoby AS 
$$
    SELECT * FROM osoby;
$$ LANGUAGE SQL;

SELECT * FROM f1();
```

- `RETURNS SETOF` - funkcja zwraca wiele rekordów.

### Przykład funkcji z OUT parametrami

```sql
CREATE OR REPLACE FUNCTION f1(OUT surname TEXT, OUT age INT, OUT city TEXT)
RETURNS SETOF RECORD AS 
$$
    SELECT osoby.nazwisko, wiek, miasto 
    FROM osoby 
    INNER JOIN miasta ON osoby.nazwisko = miasta.nazwisko;
$$ LANGUAGE SQL;

SELECT * FROM f1();
```

- `RETURNS SETOF RECORD` - funkcja zwraca rekordy (wynik zapytania).

## Podstawowe operacje SQL

### Tworzenie tabeli

```sql
CREATE TABLE nazwa_tabeli (
  kolumna1 TYP,
  kolumna2 TYP,
  ...
);
```

### Usuwanie tabeli

```sql
DROP TABLE nazwa_tabeli;
```

### Wstawianie danych

```sql
INSERT INTO nazwa_tabeli (kolumna1, kolumna2, ...) VALUES (wartość1, wartość2, ...);
```

### Aktualizacja danych

```sql
UPDATE nazwa_tabeli SET kolumna1 = wartość1 WHERE warunek;
```

### Usuwanie danych

```sql
DELETE FROM nazwa_tabeli WHERE warunek;
```

### Selekcja danych

```sql
SELECT kolumna1, kolumna2, ... FROM nazwa_tabeli WHERE warunek;
```

### Sortowanie wyników

```sql
SELECT kolumna1, kolumna2, ... FROM nazwa_tabeli ORDER BY kolumna1 [ASC|DESC];
```

- `ASC` - sortowanie rosnące (domyślnie).
- `DESC` - sortowanie malejące.

### Grupowanie wyników

```sql
SELECT kolumna1, COUNT(*) FROM nazwa_tabeli GROUP BY kolumna1;
```

### Filtrowanie grup

```sql
SELECT kolumna1, COUNT(*) FROM nazwa_tabeli GROUP BY kolumna1 HAVING COUNT(*) > 1;
```

- `HAVING` - warunek filtrowania grup.

### Łączenie tabel

#### INNER JOIN

```sql
SELECT kolumna1, kolumna2
FROM tabela1
INNER JOIN tabela2 ON tabela1.kolumna = tabela2.kolumna;
```

- `INNER JOIN` - zwraca tylko te rekordy, które mają pasujące wartości w obu tabelach.

#### LEFT JOIN

```sql
SELECT kolumna1, kolumna2
FROM tabela1
LEFT JOIN tabela2 ON tabela1.kolumna = tabela2.kolumna;
```

- `LEFT JOIN` - zwraca wszystkie rekordy z tabeli lewej oraz pasujące rekordy z tabeli prawej, a tam gdzie brak pasujących wartości, zwraca NULL.

#### RIGHT JOIN

```sql
SELECT kolumna1, kolumna2
FROM tabela1
RIGHT JOIN tabela2 ON tabela1.kolumna = tabela2.kolumna;
```

- `RIGHT JOIN` - zwraca wszystkie rekordy z tabeli prawej oraz pasujące rekordy z tabeli lewej, a tam gdzie brak pasujących wartości, zwraca NULL.

#### FULL JOIN

```sql
SELECT kolumna1, kolumna2
FROM tabela1
FULL JOIN tabela2 ON tabela1.kolumna = tabela2.kolumna;
```

- `FULL JOIN` - zwraca wszystkie rekordy, gdzie istnieją pasujące wartości w jednej z tabel lub w obu tabelach.

Powyższa notatka obejmuje podstawowe oraz nieco bardziej zaawansowane operacje SQL, umożliwiając efektywne zarządzanie i manipulowanie danymi w bazach danych.
