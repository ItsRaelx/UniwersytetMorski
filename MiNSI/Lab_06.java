import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("DuplicatedCode")
public class Lab_06 {
    static int A = 10;
    static int N = 2;
    static int ACCURACY = 3;
    static double MAX = 5.21;
    static double MIN = -5.21;
    static int POPULATION_SIZE = 20;

    private static double fitnessFunction(double min, double max, int accuracy, String[] x, int a, int n) {
        double sum = a * n;
        for (String xj : x) {
            double range = max - min;
            double totalPossibleValues = (int) Math.ceil(Math.pow(10, accuracy) * range) + 1;
            long decimal = Long.parseLong(xj, 2);
            double xi = min + (decimal / totalPossibleValues) * range;
            sum += Math.pow(xi, 2) - (a * Math.cos(2 * Math.PI * xi)) + Math.pow(xi, 2) - (a * Math.sin(2 * Math.PI * xi));
        }
        return sum;
    }

    private static String generateBinary(double min, double max, int accuracy, Double num) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max.");
        }
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy must be non-negative.");
        }

        double range = max - min;
        long totalPossibleValues = (long) Math.ceil(Math.pow(10, accuracy) * range) + 1;
        int binaryLength = (int) Math.ceil(Math.log(totalPossibleValues) / Math.log(2));

        long scaledReal;

        if (num == null) {
            Random rand = new Random();
            scaledReal = (long) ((rand.nextDouble() * totalPossibleValues));
        } else {
            if (num < min || num > max) {
                throw new IllegalArgumentException("num must be within the range [min, max].");
            }
            scaledReal = (long) ((num - min) * Math.pow(10, accuracy));
        }

        StringBuilder binaryString = new StringBuilder(Long.toBinaryString(scaledReal));

        while (binaryString.length() < binaryLength) {
            binaryString.insert(0, "0");
        }

        return binaryString.toString();
    }

    private static String[] generateChromosome(int numGenes) {
        String[] chromosome = new String[numGenes];
        Random rand = new Random();

        for (int i = 0; i < numGenes; i++) {
            double num = MIN + (MAX - MIN) * rand.nextDouble();
            chromosome[i] = generateBinary(MIN, MAX, ACCURACY, num);
        }

        return chromosome;
    }

    private static String[][] generatePopulation(int populationSize, int numGenes) {
        String[][] population = new String[populationSize][numGenes];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateChromosome(numGenes);
        }
        return population;
    }

    private static double[] calculateFitness(String[][] population) {
        double[] fitness = new double[population.length];
        for (int i = 0; i < population.length; i++) {
            fitness[i] = fitnessFunction(MIN, MAX, ACCURACY, population[i], A, N);
        }
        return fitness;
    }

    private static String[][] rouletteWheelSelection(String[][] population, double[] fitness) {
        int populationSize = population.length;
        String[][] newPopulation = new String[populationSize][];
        double totalFitness = Arrays.stream(fitness).sum();

        Random rand = new Random();
        for (int i = 0; i < populationSize; i++) {
            double randValue = rand.nextDouble() * totalFitness;
            double cumulativeFitness = 0;
            for (int j = 0; j < populationSize; j++) {
                cumulativeFitness += fitness[j];
                if (cumulativeFitness >= randValue) {
                    newPopulation[i] = population[j].clone();
                    break;
                }
            }
        }

        return newPopulation;
    }

    private static double calculateAverageFitness(double[] fitness) {
        return Arrays.stream(fitness).average().orElse(0);
    }

    public static void main(String[] args) {
        String[][] population1 = generatePopulation(POPULATION_SIZE, N);

        double[] fitness1 = calculateFitness(population1);

        String[][] population2 = rouletteWheelSelection(population1, fitness1);

        double[] fitness2 = calculateFitness(population2);

        double avgFitness1 = calculateAverageFitness(fitness1);
        double avgFitness2 = calculateAverageFitness(fitness2);

        System.out.println("Średni fitness z P(1): " + avgFitness1);
        System.out.println("Średni fitness z P(2): " + avgFitness2);
    }
}
