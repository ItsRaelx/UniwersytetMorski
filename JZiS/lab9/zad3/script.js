var num1=5,num2=8;
document.write("Num1: " + num1+"<br>");
document.write("Num2: " + num2+"<br>");

var error="";

if(num1>100 || num1<0) {
    error=error+"Error: Num 1 poza zakresem<br>"
}

if(num2>100 || num2<0) {
    error=error+"Error: Num 2 poza zakresem<br>"
}

if(error=="") {
    document.write("Suma: "+ (num1 + num2) +"<br>");
} else {
    document.write(error);
}