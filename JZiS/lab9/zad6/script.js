var tablica=[
    [1234, 1235, 1236, 1237],
    ["Iwański", "Kmiecik", "Wulkowicz", "Knasiak"],
    [2004, 2005, 2006, 2022]
]

var i=0;

tablica[2].forEach(element => {
    if((2024-element)>10) {
        document.write(tablica[1][i] + "<br>");
    }
    i++;
});