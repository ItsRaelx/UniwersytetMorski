var lista=[10, 0, 1, 100, 88, 90, 99];
var suma=0;
var i=0;

lista.forEach(element => {
    if(element<90) {
        i++;
        suma=suma+element;
        document.write(element + "<br>");
    }
});

document.write("Średnia: " + suma/i + "<br>");
document.write("Suma: " + suma + "<br>");
document.write("Ilość: " + suma + "<br>");