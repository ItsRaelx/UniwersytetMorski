function generujTablice(rozmiar, liczba) {
    const array = new Array(rozmiar).fill(liczba);
    return array;
}

var rozmiar=5;
var liczba=3;

document.write("Array: " + generujTablice(rozmiar, liczba) + "<br>");