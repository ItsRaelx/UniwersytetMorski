function sumaLiczb(array) {
    var suma=0;
    array.forEach(element => {
        suma=suma+element
    });
    return suma;
}

var num1=1, num2=2, num3=3;
var tablica=[num1,num2,num3];

document.write("Pierwsza liczba: " + num1 + "<br>");
document.write("Druga liczba: " + num2 + "<br>");
document.write("Trzecia liczba: " + num3 + "<br>");

document.write("Suma: " + sumaLiczb(tablica) + "<br>");