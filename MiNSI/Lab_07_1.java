import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Lab_07_1 {
    static final int A = 10;
    static final int N = 2;
    static final int ACCURACY = 3;
    static final int[] POPULATION_SIZE = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200};
    static final double MAX = 5.21;
    static final double MIN = -5.21;
    static final double PC = 0.6;
    static final double PM = 0.02;
    static final Random rand = new Random();

    private static double fitnessFunction(String[] x) {
        double sum = A * N;
        for (String xj : x) {
            double xi = decode(xj);
            sum += Math.pow(xi, 2) - (A * Math.cos(2 * Math.PI * xi));
        }
        return sum;
    }

    private static String generateBinary() {
        double range = MAX - MIN;
        long totalPossibleValues = (long) Math.ceil(Math.pow(10, ACCURACY) * range) + 1;
        int binaryLength = (int) Math.ceil(Math.log(totalPossibleValues) / Math.log(2));
        long scaledReal = (long) (rand.nextDouble() * totalPossibleValues);
        StringBuilder binaryString = new StringBuilder(Long.toBinaryString(scaledReal));
        while (binaryString.length() < binaryLength) {
            binaryString.insert(0, "0");
        }
        return binaryString.toString();
    }

    private static String[] generateChromosome() {
        String[] chromosome = new String[N];
        for (int i = 0; i < N; i++) {
            chromosome[i] = generateBinary();
        }
        return chromosome;
    }

    private static String[][] generatePopulation(int populationSize) {
        String[][] population = new String[populationSize][N];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateChromosome();
        }
        return population;
    }

    private static double[] calculateFitness(String[][] population) {
        return Arrays.stream(population).mapToDouble(Lab_07_1::fitnessFunction).toArray();
    }

    private static String[][] rouletteWheelSelection(String[][] population, double[] fitness) {
        int populationSize = population.length;
        String[][] newPopulation = new String[populationSize][N];

        // Normalizacja wartości przystosowania
        double minFitness = Arrays.stream(fitness).min().orElse(0);
        double[] normalizedFitness = new double[populationSize];
        for (int i = 0; i < populationSize; i++) {
            normalizedFitness[i] = minFitness / (fitness[i] + 1e-10); // Dodajemy małą wartość, aby uniknąć dzielenia przez zero
        }

        // Obliczanie sumy wartości przystosowania (suma normalizowanych wartości)
        double totalFitness = Arrays.stream(normalizedFitness).sum();

        // Selekcja ruletkowa
        for (int i = 0; i < populationSize; i++) {
            double randValue = rand.nextDouble() * totalFitness;
            double cumulativeFitness = 0;
            for (int j = 0; j < populationSize; j++) {
                cumulativeFitness += normalizedFitness[j];
                if (cumulativeFitness >= randValue) {
                    newPopulation[i] = population[j].clone();
                    break;
                }
            }
        }
        return newPopulation;
    }


    private static String[][] crossover(String[] parent1, String[] parent2) {
        int numCrossoverPoints = 2;
        int[] crossoverPoints = rand.ints(numCrossoverPoints, 1, parent1[0].length() - 1).sorted().toArray();
        String[] offspring1 = new String[N];
        String[] offspring2 = new String[N];

        for (int i = 0; i < N; i++) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            boolean swap = false;
            int lastPoint = 0;

            for (int point : crossoverPoints) {
                if (swap) {
                    sb1.append(parent2[i], lastPoint, point);
                    sb2.append(parent1[i], lastPoint, point);
                } else {
                    sb1.append(parent1[i], lastPoint, point);
                    sb2.append(parent2[i], lastPoint, point);
                }
                swap = !swap;
                lastPoint = point;
            }
            sb1.append(parent1[i].substring(lastPoint));
            sb2.append(parent2[i].substring(lastPoint));
            offspring1[i] = sb1.toString();
            offspring2[i] = sb2.toString();
        }
        return new String[][]{offspring1, offspring2};
    }

    private static String[] mutate(String[] chromosome) {
        int geneIndex = rand.nextInt(N);
        int bitIndex = rand.nextInt(chromosome[0].length());
        char[] gene = chromosome[geneIndex].toCharArray();
        gene[bitIndex] = (gene[bitIndex] == '0') ? '1' : '0';
        chromosome[geneIndex] = new String(gene);
        return chromosome;
    }

    private static double decode(String binary) {
        double range = MAX - MIN;
        long totalPossibleValues = (long) Math.ceil(Math.pow(10, ACCURACY) * range) + 1;
        long decimal = Long.parseLong(binary, 2);
        return MIN + (decimal / (double) totalPossibleValues) * range;
    }

    public static void main(String[] args) {
        int numEvaluations = 1000;
        int numRuns = 50;

        for (int populationSize : POPULATION_SIZE) {
            double[][] currentFitness = new double[numRuns][numEvaluations];
            double[][] bestCurrentFitness = new double[numRuns][numEvaluations];
            double[][] bestGlobalFitness = new double[numRuns][numEvaluations];

            for (int run = 0; run < numRuns; run++) {
                String[][] population = generatePopulation(populationSize);
                double[] fitness = calculateFitness(population);
                double bestGlobal = Double.MAX_VALUE;

                for (int eval = 0; eval < numEvaluations; eval++) {
                    population = rouletteWheelSelection(population, fitness);

                    for (int i = 0; i < population.length; i++) {
                        if (rand.nextDouble() <= PC) {
                            int partnerIndex = rand.nextInt(population.length);
                            String[][] offspring = crossover(population[i], population[partnerIndex]);
                            population[i] = offspring[0];
                            population[partnerIndex] = offspring[1];
                        }
                        if (rand.nextDouble() <= PM) {
                            population[i] = mutate(population[i]);
                        }
                    }

                    fitness = calculateFitness(population);
                    double bestCurrent = Arrays.stream(fitness).min().orElse(Double.MAX_VALUE);
                    bestGlobal = Math.min(bestGlobal, bestCurrent);

                    for (int i = 0; i < fitness.length; i++) {
                        currentFitness[run][eval] += fitness[i];
                    }
                    currentFitness[run][eval] /= fitness.length;
                    bestCurrentFitness[run][eval] = bestCurrent;
                    bestGlobalFitness[run][eval] = bestGlobal;
                }
            }

            exportToExcel(populationSize, currentFitness, bestCurrentFitness, bestGlobalFitness);
        }
    }

    private static void exportToExcel(int populationSize, double[][] currentFitness, double[][] bestCurrentFitness, double[][] bestGlobalFitness) {
        try (FileWriter writer = new FileWriter("GA_Population_" + populationSize + ".csv")) {
            writer.append("Evaluation,CurrentFitness,BestCurrentFitness,BestGlobalFitness\n");
            for (int eval = 0; eval < currentFitness[0].length; eval++) {
                int finalEval = eval;
                double avgCurrentFitness = Arrays.stream(currentFitness).mapToDouble(run -> run[finalEval]).average().orElse(0);
                int finalEval1 = eval;
                double avgBestCurrentFitness = Arrays.stream(bestCurrentFitness).mapToDouble(run -> run[finalEval1]).average().orElse(0);
                int finalEval2 = eval;
                double avgBestGlobalFitness = Arrays.stream(bestGlobalFitness).mapToDouble(run -> run[finalEval2]).average().orElse(0);

                writer.append(String.format("%d,%.4f,%.4f,%.4f\n", eval + 1, avgCurrentFitness, avgBestCurrentFitness, avgBestGlobalFitness));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
