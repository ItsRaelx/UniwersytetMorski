CREATE TABLE osoby (
  Lp SERIAL PRIMARY KEY, 
  Nazwisko VARCHAR (50) NOT NULL,
  Data_ur DATE NOT NULL
);

INSERT INTO osoby (Nazwisko, Data_ur) VALUES    
	('Kowalski', '1998.09.01'),	('Malinowska', '1991.10.09'),
	('Nowak', '1993.02.09'), ('Kowalewski', '1992.03.12');
	
CREATE TABLE miasto (
  Lp SERIAL PRIMARY KEY, 
  Nazwisko VARCHAR (50) NOT NULL,
  Miasto VARCHAR (50) NOT NULL
);

INSERT INTO miasto (Nazwisko, Miasto) VALUES    
	('Kowalski', 'Sopot'),	('Malinowska', 'Gdańsk'),
	('Nowak', 'Gdańsk'), ('Kowalewski', 'Gdynia');
	
SeLeCt * FrOm osoby;
sElEcT * fRoM miasto;

SELECT osoby.Nazwisko,osoby.Data_ur,EXTRACT(YEAR FROM Data_ur) AS Rok_ur, 2024-EXTRACT(YEAR FROM Data_ur) AS Wiek,Miasto FROM osoby
INNER JOIN miasto ON osoby.Nazwisko=miasto.Nazwisko;

ALTER TABLE osoby ADD Brutto FLOAT
select * from osoby;

update osoby SET brutto='3820.27' where lp=2;


DO $$ 
DECLARE 
	srednia_pensja FLOAT;
BEGIN
	SELECT round(AVG(brutto)::numeric,2) 
	INTO srednia_pensja
	FROM osoby;
	RAISE NOTICE 'średnia: %', srednia_pensja;
	DROP TABLE IF EXISTS temp;
	CREATE TEMPORARY TABLE temp AS SELECT osoby.Nazwisko, 2024-EXTRACT(YEAR FROM Data_ur) AS Wiek,Miasto FROM osoby
	INNER JOIN miasto ON osoby.Nazwisko=miasto.Nazwisko WHERE brutto>srednia_pensja;
END $$;

SELECT * FROM temp;



DO $$ 
BEGIN
	DROP TABLE IF EXISTS temp;
	CREATE TEMPORARY TABLE temp AS SELECT osoby.Nazwisko,osoby.Data_ur,EXTRACT(YEAR FROM Data_ur) AS Rok_ur, 2024-EXTRACT(YEAR FROM Data_ur) AS Wiek,Miasto, osoby.brutto, round((osoby.brutto/1.23)::numeric,2) AS netto FROM osoby
INNER JOIN miasto ON osoby.Nazwisko=miasto.Nazwisko;
END $$;

SELECT * FROM temp;
