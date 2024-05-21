package org.example;

import java.util.Arrays;
import java.util.Random;

public class Lab4_3 {

    public static void main(String[] args) {
        // Rodzice z poprzedniego polecenia
        int[] parent1 = {1, 3, 2, 7, 9, 4, 5, 10, 6, 8};
        int[] parent2 = {3, 7, 6, 1, 5, 9, 8, 2, 10, 4};

        System.out.println("Rodzic 1: " + Arrays.toString(parent1));
        System.out.println("Rodzic 2: " + Arrays.toString(parent2));

        // Wykonanie operatora inwersji dla rodziców
        int[] invertedParent1 = inversion(parent1);
        int[] invertedParent2 = inversion(parent2);

        System.out.println("Rodzic 1 po inwersji: " + Arrays.toString(invertedParent1));
        System.out.println("Rodzic 2 po inwersji: " + Arrays.toString(invertedParent2));
    }

    // Operator inwersji
    public static int[] inversion(int[] chromosome) {
        Random random = new Random();

        // Losowe wybieranie dwóch różnych pozycji w chromosomie
        int position1 = random.nextInt(chromosome.length);
        int position2;
        do {
            position2 = random.nextInt(chromosome.length);
        } while (position2 == position1);

        // Odwrócenie kolejności elementów pomiędzy wybranymi pozycjami
        int start = Math.min(position1, position2);
        int end = Math.max(position1, position2);
        while (start < end) {
            int temp = chromosome[start];
            chromosome[start] = chromosome[end];
            chromosome[end] = temp;
            start++;
            end--;
        }

        return chromosome;
    }
}
