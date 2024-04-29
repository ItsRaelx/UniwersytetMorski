function sumaKwadr(array) {
    var suma=0;
    array.forEach(element => {
        suma=suma+(element*element);
    });
    return suma;
}

array=[1, 3, 4, 0];


document.write("Suma: " + sumaKwadr(array) + "<br>");