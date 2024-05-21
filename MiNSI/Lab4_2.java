package org.example;

import java.util.Random;

public class Lab4_2 {

    private static int calculateBinaryLength(double lowerBound, double upperBound, int precision) {
        double range = upperBound - lowerBound;
        long totalValues = (long) (Math.pow(10, precision) * range);
        return (int) Math.ceil(Math.log(totalValues) / Math.log(2));
    }

    public static String generateBinaryInRange(double lowerBound, double upperBound, int binaryLength) {
        Random rand = new Random();
        double range = upperBound - lowerBound;
        long totalValues = (long) (Math.pow(10, 3) * range);
        long scaledReal = (long) ((rand.nextDouble() * totalValues));
        String binaryString = Long.toBinaryString(scaledReal);

        while (binaryString.length() < binaryLength) {
            binaryString = "0" + binaryString;
        }
        return binaryString.length() > binaryLength ? binaryString.substring(binaryString.length() - binaryLength) : binaryString;
    }

    public static String encode(double value, double lowerBound, double upperBound, int precision) {
        double subintervals = Math.pow(10, precision);
        double decimalValue = ((value - lowerBound) / (upperBound - lowerBound)) * subintervals;
        long intValue = Math.round(decimalValue);
        String binaryString = Long.toBinaryString(intValue);

        if (value < 0) {
            binaryString = "1" + binaryString;
        } else {
            binaryString = "0" + binaryString;
        }

        return binaryString;
    }

    private static double decode(String binary, double lowerBound, double upperBound, int precision) {
        long decimal = Long.parseLong(binary, 2);
        double range = upperBound - lowerBound;
        long totalValues = (long) (Math.pow(10, precision) * range);
        return lowerBound + (decimal / (double) totalValues) * range;
    }

    public static double rastriginFunction(double x1, double x2) {
        final int A = 10;
        return A * 2 + (x1 * x1 - A * Math.cos(2 * Math.PI * x1)) + (x2 * x2 - A * Math.cos(2 * Math.PI * x2));
    }

    private static String[] performCrossover(String parent1, String parent2, double lowerBound, double upperBound, int precision) {
        Random random = new Random();
        int crossoverPoint1 = random.nextInt(parent1.length());
        int crossoverPoint2 = random.nextInt(parent1.length());

        if (crossoverPoint1 > crossoverPoint2) {
            int temp = crossoverPoint1;
            crossoverPoint1 = crossoverPoint2;
            crossoverPoint2 = temp;
        }

        String child1 = parent1.substring(0, crossoverPoint1) + parent2.substring(crossoverPoint1, crossoverPoint2) + parent1.substring(crossoverPoint2);
        String child2 = parent2.substring(0, crossoverPoint1) + parent1.substring(crossoverPoint1, crossoverPoint2) + parent2.substring(crossoverPoint2);

        return new String[]{child1, child2, Integer.toString(crossoverPoint1), Integer.toString(crossoverPoint2)};
    }

    public static void main(String[] args) {
        final double lowerBound = -5.21;
        final double upperBound = 5.21;
        final int precision = 3;

        int binaryLength = calculateBinaryLength(lowerBound, upperBound, precision);

        String binaryX11 = generateBinaryInRange(lowerBound, upperBound, binaryLength);
        String binaryX12 = generateBinaryInRange(lowerBound, upperBound, binaryLength);
        String chromosome1 = binaryX11 + binaryX12;
        double decodedX11 = decode(binaryX11, lowerBound, upperBound, precision);
        double decodedX12 = decode(binaryX12, lowerBound, upperBound, precision);
        double result1 = rastriginFunction(decodedX11, decodedX12);

        String binaryX21 = generateBinaryInRange(lowerBound, upperBound, binaryLength);
        String binaryX22 = generateBinaryInRange(lowerBound, upperBound, binaryLength);
        String chromosome2 = binaryX21 + binaryX22;
        double decodedX21 = decode(binaryX21, lowerBound, upperBound, precision);
        double decodedX22 = decode(binaryX22, lowerBound, upperBound, precision);
        double result2 = rastriginFunction(decodedX21, decodedX22);

        String[] crossoverPoints = performCrossover(chromosome1, chromosome2, lowerBound, upperBound, precision);

        System.out.println("Original Chromosomes:");
        System.out.println(chromosome1);
        System.out.println(chromosome2);
        System.out.println("Crossover Points: " + crossoverPoints[2] + ", " + crossoverPoints[3]);
        System.out.println("After Crossover:");
        System.out.println(crossoverPoints[0]);
        System.out.println(crossoverPoints[1]);
    }
}
