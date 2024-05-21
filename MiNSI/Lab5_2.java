package org.example;

import java.util.Random;

public class Lab5_2 {
    private static final double A = -5.21;
    private static final double B = 5.21;
    private static final int D = 3;
    private static final int GENOTYPE_SIZE = 10; // Liczba genotypów w chromosomie
    private static final int POPULATION_SIZE = 20;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        // Obliczanie liczby bitów
        int m = calculateBits(A, B, D);

        // Generowanie populacji osobników
        String[][] population = generatePopulation(POPULATION_SIZE, A, B, m, GENOTYPE_SIZE);
        double[] rastriginPopulation = new double[population.length];
        double rastriginSum = 0.0;

        // Wypisanie wygenerowanej populacji
        System.out.println("Wygenerowana populacja:");
        for (int i = 0; i < POPULATION_SIZE; i++) {
            StringBuilder chromosome = new StringBuilder();
            for (int j = 0; j < GENOTYPE_SIZE; j++) {
                chromosome.append(population[i][j]).append(" ");
            }
            System.out.println("Chromosom " + (i + 1) + ": " + chromosome);
        }

        // Obliczanie wartości funkcji przystosowania dla populacji
        for (int i = 0; i < POPULATION_SIZE; i++) {
            double[] values = new double[GENOTYPE_SIZE];
            for (int j = 0; j < GENOTYPE_SIZE; j++) {
                values[j] = decode(population[i][j], A, B, m);
            }
            rastriginPopulation[i] = rastriginFunction(values);
            rastriginSum += rastriginPopulation[i];
        }

        // Obliczanie średniej wartości funkcji przystosowania
        double rastriginValue = rastriginSum / POPULATION_SIZE;
        int smaller = 0;
        int larger = 0;

        // Klasyfikacja populacji
        for (double rastrigin : rastriginPopulation) {
            if (rastrigin < rastriginValue) {
                smaller++;
            } else {
                larger++;
            }
        }

        // Wypisanie wyników
        System.out.println("Średnia wartość: " + rastriginValue);
        System.out.println("Mniejszych od średniej: " + smaller);
        System.out.println("Większych/Równych od średniej: " + larger);
    }

    // Obliczanie liczby bitów
    private static int calculateBits(double a, double b, int d) {
        double interval = b - a;
        double subintervalCount = interval * Math.pow(10, d);
        return (int) Math.ceil(Math.log(subintervalCount) / Math.log(2));
    }

    // Generowanie populacji osobników
    private static String[][] generatePopulation(int N, double a, double b, int m, int genotypeSize) {
        String[][] population = new String[N][genotypeSize];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < genotypeSize; j++) {
                String genotype = generateGenotype(a, b, m);
                // Sprawdzenie dopuszczalności genotypu
                while (!isGenotypeValid(genotype, a, b, m)) {
                    genotype = generateGenotype(a, b, m); // Generowanie nowego genotypu, jeśli poprzedni był niedopuszczalny
                }
                population[i][j] = genotype;
            }
        }
        return population;
    }

    // Funkcja Rastrigina dla 10 zmiennych
    private static double rastriginFunction(double[] x) {
        double A = 10.0;
        double sum = 0.0;
        for (double value : x) {
            sum += Math.pow(value, 2) - A * Math.cos(2 * Math.PI * value);
        }
        return A * x.length + sum;
    }

    // Sprawdzenie dopuszczalności genotypu
    private static boolean isGenotypeValid(String genotype, double a, double b, int m) {
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
