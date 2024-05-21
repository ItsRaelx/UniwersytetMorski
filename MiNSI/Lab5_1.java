package org.example;

import java.util.Random;

public class Lab5_1 {
    private static final double A = -5.21;
    private static final double B = 5.21;
    private static final int D = 3;
    private static final int POPULATION_SIZE = 20;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        // Obliczanie liczby bitów
        int m = calculateBits(A, B, D);

        // Generowanie populacji osobników
        String[][] population = generatePopulation(POPULATION_SIZE, A, B, m);
        double[] rastriginPopulation = new double[population.length];
        double rastriginSum = 0.0;

        // Wypisanie wygenerowanej populacji
        System.out.println("Wygenerowana populacja:");
        for (int i = 0; i < POPULATION_SIZE; i++) {
            String osobnik1 = population[i][0];
            String osobnik2 = population[i][1];
            double decoded1 = decode(osobnik1, A, B, m);
            double decoded2 = decode(osobnik2, A, B, m);
            double rastrigin = rastriginFunction(decoded1, decoded2);
            rastriginSum += rastrigin;
            rastriginPopulation[i] = rastrigin;
            System.out.println("Osobnik " + (i + 1) + ": " + osobnik1 + " | " + osobnik2 + ", Dekodowany: " + decoded1 + " | " + decoded2 + ", Rastrigin: " + rastrigin);
        }

        double rastriginValue = rastriginSum / POPULATION_SIZE;
        int smaller = 0;
        int larger = 0;
        System.out.println("Średnia wartość: " + rastriginValue);

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (rastriginValue < rastriginPopulation[i]) {
                smaller++;
            } else {
                larger++;
            }
        }
        System.out.println("Mniejszych od średniej: " + smaller);
        System.out.println("Większych/Równych od średniej: " + larger);

    }

    // Obliczenie liczby bitów
    private static int calculateBits(double a, double b, int d) {
        double interval = b - a;
        double subintervalCount = interval * Math.pow(10, d);
        return (int) Math.ceil(Math.log(subintervalCount) / Math.log(2));
    }

    // Generowanie populacji osobników
    private static String[][] generatePopulation(int N, double a, double b, int m) {
        String[][] population = new String[N][2];
        for (int i = 0; i < N; i++) {
            String genotype1 = generateGenotype(a, b, m);
            String genotype2 = generateGenotype(a, b, m);
            // Sprawdzenie dopuszczalności osobnika
            while (!isIndividualValid(genotype1, a, b, m)) {
                genotype1 = generateGenotype(a, b, m); // Generowanie nowego osobnika, jeśli poprzedni był niedopuszczalny
            }
            while (!isIndividualValid(genotype2, a, b, m)) {
                genotype2 = generateGenotype(a, b, m); // Generowanie nowego osobnika, jeśli poprzedni był niedopuszczalny
            }
            population[i][0] = genotype1;
            population[i][1] = genotype2;
        }
        return population;
    }

    // Funkcja Rastrigina dla dwóch zmiennych x1 i x2
    private static double rastriginFunction(double x1, double x2) {
        double A = 10.0;
        return 10 * 2 + (Math.pow(x1, 2) - A * Math.cos(2 * Math.PI * x1)) + (Math.pow(x2, 2) - A * Math.cos(2 * Math.PI * x2));
    }


    // Sprawdzenie dopuszczalności osobnika
    private static boolean isIndividualValid(String genotype, double a, double b, int m) {
        double decodedValue = decode(genotype, a, b, m);
        return decodedValue >= a && decodedValue <= b;
    }

    // Generowanie genotypu
    private static String generateGenotype(double a, double b, int m) {
        int valueCount = (int) Math.pow(2, m);
        int selectedIndex = rand.nextInt(valueCount);
        return String.format("%" + m + "s", Integer.toBinaryString(selectedIndex)).replace(' ', '0');
    }

    // Dekodowanie wartości
    private static double decode(String binary, double a, double b, int m) {
        int valueCount = (int) Math.pow(2, m);
        int decimal = Integer.parseInt(binary, 2);
        return a + decimal * ((b - a) / (valueCount - 1));
    }
}
