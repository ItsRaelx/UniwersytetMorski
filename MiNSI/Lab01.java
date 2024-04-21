import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Lab01 {

    // Metoda obliczająca liczbę bitów potrzebną do reprezentacji wartości w zadanym zakresie z określoną dokładnością
    private static int calculateBitsNeeded(double a, double b, int d) {
        double range = b - a;
        long totalPossibleValues = (long) (Math.pow(10, d) * range);
        return (int) Math.ceil(Math.log(totalPossibleValues) / Math.log(2));
    }

    // Metoda generująca binarną reprezentację liczby z zakresu [min, max] o długości binaryLength
    public static String generateBinaryFromRange(double min, double max, int binaryLength) {
        Random rand = new Random();
        double range = max - min;
        long totalPossibleValues = (long) (Math.pow(10, 3) * range);
        long scaledReal = (long) ((rand.nextDouble() * totalPossibleValues));
        String binaryString = Long.toBinaryString(scaledReal);

        // Zapewnienie, że ciąg binarny ma wymaganą długość
        while (binaryString.length() < binaryLength) {
            binaryString = "0" + binaryString;
        }
        return binaryString.length() > binaryLength ? binaryString.substring(binaryString.length() - binaryLength) : binaryString;
    }

    // Metoda dekodująca wartość binarną na wartość rzeczywistą w zakresie [a, b]
    private static double decode(String binary, double a, double b, int d) {
        long decimal = Long.parseLong(binary, 2);
        double range = b - a;
        long totalPossibleValues = (long) (Math.pow(10, d) * range);
        return a + (decimal / (double) totalPossibleValues) * range;
    }

    // Funkcja Rastrigina
    public static double rastriginFunction(double x1, double x2) {
        int A = 10;
        return A * 2 + (x1 * x1 - A * Math.cos(2 * Math.PI * x1)) + (x2 * x2 - A * Math.cos(2 * Math.PI * x2));
    }

    // Główna metoda realizująca Algorytm 1 i 2
    public static void main(String[] args) {
        double a = -5.21;
        double b = 5.21;
        int d = 3;
        int M = 2000; // Liczba iteracji

        try (FileWriter fwMin = new FileWriter("fmin.txt");
             FileWriter fwCurrent = new FileWriter("f_current.txt")) {

            double x1 = decode(generateBinaryFromRange(a, b, calculateBitsNeeded(a, b, d)), a, b, d);
            double x2 = decode(generateBinaryFromRange(a, b, calculateBitsNeeded(a, b, d)), a, b, d);
            double[] Xmin = {x1, x2};
            double fmin = rastriginFunction(x1, x2);

            // Zapisanie pierwszych wartości
            fwMin.write(fmin + "\n");
            fwCurrent.write(fmin + "\n");

            // Algorytm błądzenia przypadkowego (Random Walk)
            for (int k = 1; k <= M; k++) {
                x1 = decode(generateBinaryFromRange(a, b, calculateBitsNeeded(a, b, d)), a, b, d);
                x2 = decode(generateBinaryFromRange(a, b, calculateBitsNeeded(a, b, d)), a, b, d);
                double f = rastriginFunction(x1, x2);

                fwCurrent.write(f + "\n");

                if (f < fmin) {
                    fmin = f;
                    Xmin[0] = x1;
                    Xmin[1] = x2;
                    fwMin.write(fmin + "\n");
                }
            }

            System.out.println("Minimum value of f: " + fmin + " at Xmin: (" + Xmin[0] + ", " + Xmin[1] + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
