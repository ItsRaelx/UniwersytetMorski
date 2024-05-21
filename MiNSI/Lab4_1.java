package org.example;

import java.util.Random;

public class Lab4_1 {
    private static final double A = -5.21;
    private static final double B = 5.21;
    private static final int D = 3;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        // Obliczanie liczby bitów
        int m1 = calculateBits(A, B, D);
        int m2 = m1;

        // Generowanie genotypów
        double x1 = generateGenotype(A, B, D, m1);
        double x2 = generateGenotype(A, B, D, m2);

        // Enkodowanie i dekodowanie wartości
        String bin_x1 = encode(x1, A, B, m1);
        String bin_x2 = encode(x2, A, B, m2);
        double dec_x1 = decode(bin_x1, A, B, m1);
        double dec_x2 = decode(bin_x2, A, B, m2);

        // Wypisanie wyników
        System.out.println("x1 = " + x1 + ", Binarnie: " + bin_x1 + ", Dekodowane: " + dec_x1);
        System.out.println("x2 = " + x2 + ", Binarnie: " + bin_x2 + ", Dekodowane: " + dec_x2);
        System.out.println("Chromosom = " + bin_x1 + bin_x2);

        // Obliczenie funkcji Rastrigina
        double f = rastriginFunction(dec_x1, dec_x2);
        System.out.println("Wartość funkcji Rastrigina: " + f);

        // Mutacja rozwiązania
        double mutationProbability = 0.1;
        String mutated_x1 = mutate(bin_x1, mutationProbability);
        String mutated_x2 = mutate(bin_x2, mutationProbability);
        double mutated_dec_x1 = decode(mutated_x1, A, B, m1);
        double mutated_dec_x2 = decode(mutated_x2, A, B, m2);

        // Wypisanie zmutowanych rozwiązań
        System.out.println("\nZmutowane rozwiązania:");
        System.out.println("Mutated x1: " + mutated_x1 + ", Dekodowane: " + mutated_dec_x1);
        System.out.println("Mutated x2: " + mutated_x2 + ", Dekodowane: " + mutated_dec_x2);

        // Obliczenie wartości funkcji Rastrigina dla zmutowanych rozwiązań
        double mutated_f = rastriginFunction(mutated_dec_x1, mutated_dec_x2);
        System.out.println("Wartość funkcji Rastrigina po mutacji: " + mutated_f);

        // Sprawdzenie czy wartość funkcji uległa zmianie po mutacji
        if (f != mutated_f) {
            System.out.println("\nWartość funkcji uległa zmianie po mutacji.");
        } else {
            System.out.println("\nWartość funkcji nie uległa zmianie po mutacji.");
        }
    }

    // Obliczenie liczby bitów
    private static int calculateBits(double a, double b, int d) {
        double interval = b - a;
        double subintervalCount = interval * Math.pow(10, d);
        return (int) Math.ceil(Math.log(subintervalCount) / Math.log(2));
    }

    // Generowanie genotypu
    private static double generateGenotype(double a, double b, int d, int m) {
        int valueCount = (int) Math.pow(2, m);
        int selectedIndex = rand.nextInt(valueCount);
        return a + (selectedIndex * ((b - a) / (valueCount - 1)));
    }

    // Enkodowanie wartości
    private static String encode(double x, double a, double b, int m) {
        int valueCount = (int) Math.pow(2, m);
        int index = (int) Math.round(((x - a) / (b - a)) * (valueCount - 1));
        return String.format("%" + m + "s", Integer.toBinaryString(index)).replace(' ', '0');
    }

    // Dekodowanie wartości
    private static double decode(String binary, double a, double b, int m) {
        int valueCount = (int) Math.pow(2, m);
        int decimal = Integer.parseInt(binary, 2);
        return a + decimal * ((b - a) / (valueCount - 1));
    }

    // Mutacja
    private static String mutate(String binary, double mutationProbability) {
        StringBuilder mutatedBinary = new StringBuilder(binary);
        for (int i = 0; i < binary.length(); i++) {
            if (rand.nextDouble() < mutationProbability) {
                char bit = binary.charAt(i);
                char mutatedBit = (bit == '0') ? '1' : '0';
                mutatedBinary.setCharAt(i, mutatedBit);
            }
        }
        return mutatedBinary.toString();
    }

    // Funkcja Rastrigina
    private static double rastriginFunction(double x1, double x2) {
        int A = 10;
        return 10 * 2 + (x1 * x1 - A * Math.cos(2 * Math.PI * x1)) + (x2 * x2 - A * Math.cos(2 * Math.PI * x2));
    }
}
