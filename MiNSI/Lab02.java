import java.util.Random;

public class Lab02 {
    // Stałe i parametry dostępne globalnie
    private static final double A = -5.21;
    private static final double B = 5.21;
    private static final int D = 3; // dokładność do 3 miejsc po przecinku
    private static final Random rand = new Random();

    public static void main(String[] args) {
        // Obliczanie liczby bitów
        int m1 = liczbaBitow(A, B, D);
        int m2 = m1;  // ponieważ przedziały są takie same

        // Generowanie genotypów już z ograniczeniami
        double x1 = generujGenotyp(A, B, D, m1);
        double x2 = generujGenotyp(A, B, D, m2);

        // Enkodowanie i dekodowanie wartości
        String bin_x1 = enkoduj(x1, A, B, m1);
        String bin_x2 = enkoduj(x2, A, B, m2);
        double dec_x1 = dekoduj(bin_x1, A, B, m1);
        double dec_x2 = dekoduj(bin_x2, A, B, m2);

        // Wypisanie wyników
        System.out.println("x1 = " + x1 + ", Binarnie: " + bin_x1 + ", Dekodowane: " + dec_x1);
        System.out.println("x2 = " + x2 + ", Binarnie: " + bin_x2 + ", Dekodowane: " + dec_x2);
        System.out.println("Chromosom = " + bin_x1 + bin_x2);

        // Obliczenie funkcji Rastrigina
        double f = funkcjaRastrigina(dec_x1, dec_x2);
        System.out.println("Wartość funkcji Rastrigina: " + f);
    }

    // Metody pomocnicze
    private static int liczbaBitow(double a, double b, int d) {
        double przedzial = b - a;
        double liczbaPodprzedzialow = przedzial * Math.pow(10, d);
        return (int) Math.ceil(Math.log(liczbaPodprzedzialow) / Math.log(2));
    }

    private static double generujGenotyp(double a, double b, int d, int m) {
        int liczbaWartosci = (int) Math.pow(2, m);
        int wybranyIndex = rand.nextInt(liczbaWartosci);
        return a + (wybranyIndex * ((b - a) / (liczbaWartosci - 1)));
    }

    private static String enkoduj(double x, double a, double b, int m) {
        int liczbaWartosci = (int) Math.pow(2, m);
        int index = (int) Math.round(((x - a) / (b - a)) * (liczbaWartosci - 1));
        return String.format("%" + m + "s", Integer.toBinaryString(index)).replace(' ', '0');
    }

    private static double dekoduj(String binary, double a, double b, int m) {
        int liczbaWartosci = (int) Math.pow(2, m);
        int decimal = Integer.parseInt(binary, 2);
        return a + decimal * ((b - a) / (liczbaWartosci - 1));
    }

    private static double funkcjaRastrigina(double x1, double x2) {
        int n = 2;
        int A = 10;
        return A * n + (x1 * x1 - A * Math.cos(2 * Math.PI * x1)) + (x2 * x2 - A * Math.cos(2 * Math.PI * x2));
    }
}
