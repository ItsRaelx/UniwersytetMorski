DROP TABLE osoby;
DROP TABLE miasto;

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

SELECT osoby.Nazwisko,osoby.Data_ur, 2024-EXTRACT(YEAR FROM Data_ur) AS Wiek,Miasto 
FROM osoby
INNER JOIN miasto ON osoby.Nazwisko=miasto.Nazwisko 
WHERE miasto='Gdańsk'AND 2024-EXTRACT(YEAR FROM Data_ur)>(SELECT AVG(2024-EXTRACT(YEAR FROM Data_ur)) FROM osoby INNER JOIN miasto ON osoby.Nazwisko=miasto.Nazwisko WHERE miasto='Gdańsk') ORDER BY Wiek DESC;

SELECT MIN(2024-EXTRACT(YEAR FROM Data_ur)) FROM osoby;
SELECT MAX(2024-EXTRACT(YEAR FROM Data_ur)) FROM osoby;
SELECT nazwisko FROM osoby WHERE (2024-EXTRACT(YEAR FROM Data_ur))=(SELECT MIN(2024-EXTRACT(YEAR FROM Data_ur)) FROM osoby);
SELECT nazwisko FROM osoby WHERE (2024-EXTRACT(YEAR FROM Data_ur))=(SELECT MAX(2024-EXTRACT(YEAR FROM Data_ur)) FROM osoby);

SELECT COUNT(lp) FROM osoby WHERE (EXTRACT(YEAR FROM Data_ur))>1991 AND lp=(SELECT lp from miasto WHERE miasto='Gdańsk');
