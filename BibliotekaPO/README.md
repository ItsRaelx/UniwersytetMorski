# Dokumentacja projektu Biblioteka Z12

## Wprowadzenie
Projekt Biblioteka Z12 jest aplikacją konsolową w języku Java, umożliwiającą zarządzanie zasobami bibliotecznymi oraz użytkownikami. Zastosowano tu wiele elementów programowania obiektowego, takich jak klasy abstrakcyjne, dziedziczenie i enkapsulacja.

## Struktura projektu

### Klasy

#### `Material`
- **Typ**: Klasa abstrakcyjna
- **Cel**: Stanowi bazę dla różnych typów materiałów w bibliotece (książki, czasopisma).
- **Zawiera**: 
  - Pola `title`, `author`, `borrowedBy` 
  - Metody abstrakcyjne, takie jak `displayInfo()`

#### `Book`
- **Typ**: Klasa dziedzicząca po `Material`
- **Cel**: Reprezentuje konkretny typ materiału - książkę.
- **Zawiera**: 
  - Dodatkowe pole `genre`
  - Implementację metody `displayInfo()`

#### `Magazine`
- **Typ**: Klasa dziedzicząca po `Material`
- **Cel**: Reprezentuje inny typ materiału - czasopismo.
- **Zawiera**: 
  - Dodatkowe pole `issueNumber`
  - Implementację metody `displayInfo()`

#### `User`
- **Typ**: Klasa abstrakcyjna
- **Cel**: Stanowi bazę dla różnych typów użytkowników (np. czytelników).
- **Zawiera**: 
  - Pola `name`, `id`
  - Metodę abstrakcyjną `displayInfo()`

#### `Reader`
- **Typ**: Klasa dziedzicząca po `User`
- **Cel**: Reprezentuje konkretny typ użytkownika - czytelnika.
- **Zawiera**: Implementację metody `displayInfo()`

#### `UserManager`
- **Typ**: Klasa zwykła
- **Cel**: Zarządzanie listą użytkowników.
- **Zawiera**: 
  - Listę użytkowników
  - Metody do dodawania, usuwania i wyświetlania użytkowników

#### `Z12Library`
- **Typ**: Klasa główna
- **Cel**: Zarządzanie całą biblioteką.
- **Zawiera**: 
  - Listę materiałów
  - Instancję `UserManager`
  - Metody do zarządzania materiałami i użytkownikami
  - Metodę `manageLibrary()` do obsługi menu konsolowego

## Kluczowe elementy programowania obiektowego

- **Abstrakcja**: Użycie klas abstrakcyjnych (`Material`, `User`) pozwala na zdefiniowanie ogólnych cech i zachowań, które potem są specyfikowane w klasach dziedziczących.
- **Dziedziczenie**: Klasy takie jak `Book`, `Magazine`, i `Reader` dziedziczą po klasach abstrakcyjnych, rozszerzając ich funkcjonalność.
- **Enkapsulacja**: Dane, takie jak `title`, `author` w `Material` czy `name`, `id` w `User` są ukryte i dostępne tylko przez metody tych klas.
- **Polimorfizm**: Metody abstrakcyjne jak `displayInfo()` w `Material` i `User` są implementowane różnie w klasach pochodnych, co pozwala na polimorficzne ich wykorzystanie.

## Funkcjonalności

1. **Dodawanie materiałów**: Użytkownik może dodać nowe materiały do biblioteki, takie jak książki i czasopisma, podając ich tytuł, autora oraz dodatkowe informacje specyficzne dla typu materiału (np. gatunek dla książek, numer wydania dla czasopism).

2. **Usuwanie materiałów**: Materiały mogą być usuwane z biblioteki na podstawie ich tytułu.

3. **Wypożyczanie materiałów**: Czytelnicy mogą wypożyczać dostępne materiały. Każda książka lub czasopismo może być wypożyczone tylko przez jednego użytkownika na raz.

4. **Zwracanie materiałów**: Wypożyczone materiały mogą być zwracane do biblioteki.

5. **Dodawanie użytkowników**: Nowi czytelnicy mogą być dodawani do systemu.

6. **Usuwanie użytkowników**: Czytelnicy mogą być usuwani z systemu.

7. **Wyświetlanie listy materiałów**: Program wyświetla listę wszystkich materiałów w bibliotece wraz z informacjami, czy są wypożyczone, i przez kogo.

8. **Wyświetlanie listy użytkowników**: Dostępna jest funkcja wyświetlająca listę wszystkich zarejestrowanych użytkowników.

9. **Wyświetlanie wypożyczonych materiałów przez użytkownika**: Program umożliwia wyświetlenie listy materiałów wypożyczonych przez konkretnego użytkownika.

## Przykład użycia

Po uruchomieniu programu użytkownikowi prezentowane jest menu konsolowe z opcjami do wyboru. Może on wybrać odpowiednią opcję przez wpisanie odpowiedniego numeru. Na przykład, wybierając '1', użytkownik może dodać nowy materiał do biblioteki.

## Wnioski
Projekt Biblioteka Z12 jest przykładem efektywnego wykorzystania zasad programowania obiektowego do stworzenia modularnej i łatwej w rozbudowie aplikacji konsolowej do zarządzania biblioteką.
