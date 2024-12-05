package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalSearch2 {
    private static final int MAX_ITERATIONS = 100;

    // Klasa reprezentująca miasto
    static class City {
        int id;
        double x;
        double y;

        City(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    // Klasa przechowująca wynik
    public static class Result {
        public int[] permutation;
        public double tourLength;
        public int iterations;

        public Result(int[] permutation, double tourLength, int iterations) {
            this.permutation = permutation;
            this.tourLength = tourLength;
            this.iterations = iterations;
        }
    }

    // Funkcja do importowania danych z pliku
    public static List<City> importData(String filename) {
        List<City> cities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean coordinatesSection = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().equals("NODE_COORD_SECTION")) {
                    coordinatesSection = true;
                    continue;
                }
                if (line.trim().equals("EOF")) {
                    break;
                }
                if (coordinatesSection) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0]);
                        double x = Double.parseDouble(parts[1]);
                        double y = Double.parseDouble(parts[2]);
                        cities.add(new City(id, x, y));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    // Funkcja do obliczania odległości między miastami
    public static double[][] calculateDistances(List<City> cities) {
        int n = cities.size();
        double[][] distances = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                City city1 = cities.get(i);
                City city2 = cities.get(j);
                double distance = calculateDistance(city1, city2);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }

        return distances;
    }

    // Funkcja pomocnicza do obliczania odległości euklidesowej
    private static double calculateDistance(City city1, City city2) {
        double dx = city1.x - city2.x;
        double dy = city1.y - city2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Generowanie losowej permutacji
    private static int[] generateRandomPermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int j = rand.nextInt(n);
            int temp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = temp;
        }
        return permutation;
    }

    // Obliczanie długości trasy
    public static double calculateTourLength(double[][] distances, int[] permutation) {
        double length = 0;
        for (int i = 0; i < permutation.length - 1; i++) {
            length += distances[permutation[i]][permutation[i + 1]];
        }
        length += distances[permutation[permutation.length - 1]][permutation[0]];
        return length;
    }

    // LocalSearch2 z pierwszym lepszym rozwiązaniem
    public static Result localSearch2FirstImprovement(double[][] distances) {
        int t = 0;
        int[] best = generateRandomPermutation(distances.length);
        double bestLength = calculateTourLength(distances, best);

        System.out.println("LocalSearch2 (First Improvement) - Start");
        System.out.println("Początkowe najlepsze rozwiązanie: " + bestLength);

        while (t < MAX_ITERATIONS) {
            boolean local = false;
            int[] vc = generateRandomPermutation(distances.length);
            double vcLength = calculateTourLength(distances, vc);

            while (!local) {
                int[] vn = selectFirstBetterNeighbor(distances, vc);
                double vnLength = calculateTourLength(distances, vn);

                if (vnLength < vcLength) {
                    vc = vn;
                    vcLength = vnLength;
                } else {
                    local = true;
                }
            }

            if (vcLength < bestLength) {
                best = vc.clone();
                bestLength = vcLength;
                System.out.println("Iteracja " + t + ": Znaleziono lepsze rozwiązanie: " + bestLength);
            }
            t++;
        }

        return new Result(best, bestLength, t);
    }

    // LocalSearch2 z najlepszym rozwiązaniem z otoczenia
    public static Result localSearch2BestImprovement(double[][] distances) {
        int t = 0;
        int[] best = generateRandomPermutation(distances.length);
        double bestLength = calculateTourLength(distances, best);

        System.out.println("LocalSearch2 (Best Improvement) - Start");
        System.out.println("Początkowe najlepsze rozwiązanie: " + bestLength);

        while (t < MAX_ITERATIONS) {
            boolean local = false;
            int[] vc = generateRandomPermutation(distances.length);
            double vcLength = calculateTourLength(distances, vc);

            while (!local) {
                int[] vn = findBestNeighbor(distances, vc);
                double vnLength = calculateTourLength(distances, vn);

                if (vnLength < vcLength) {
                    vc = vn;
                    vcLength = vnLength;
                } else {
                    local = true;
                }
            }

            if (vcLength < bestLength) {
                best = vc.clone();
                bestLength = vcLength;
                System.out.println("Iteracja " + t + ": Znaleziono lepsze rozwiązanie: " + bestLength);
            }
            t++;
        }

        return new Result(best, bestLength, t);
    }

    // Funkcja znajdująca pierwszego lepszego sąsiada
    private static int[] selectFirstBetterNeighbor(double[][] distances, int[] currentSolution) {
        double currentLength = calculateTourLength(distances, currentSolution);

        for (int i = 1; i < currentSolution.length - 1; i++) {
            for (int j = i + 1; j < currentSolution.length; j++) {
                int[] newSolution = currentSolution.clone();
                int temp = newSolution[i];
                newSolution[i] = newSolution[j];
                newSolution[j] = temp;

                double newLength = calculateTourLength(distances, newSolution);
                if (newLength < currentLength) {
                    return newSolution;
                }
            }
        }

        return currentSolution.clone();
    }

    // Funkcja znajdująca najlepszego sąsiada
    private static int[] findBestNeighbor(double[][] distances, int[] currentSolution) {
        int[] bestNeighbor = currentSolution.clone();
        double bestLength = calculateTourLength(distances, bestNeighbor);

        for (int i = 1; i < currentSolution.length - 1; i++) {
            for (int j = i + 1; j < currentSolution.length; j++) {
                int[] newSolution = currentSolution.clone();
                int temp = newSolution[i];
                newSolution[i] = newSolution[j];
                newSolution[j] = temp;

                double newLength = calculateTourLength(distances, newSolution);
                if (newLength < bestLength) {
                    bestNeighbor = newSolution.clone();
                    bestLength = newLength;
                }
            }
        }

        return bestNeighbor;
    }

    public static void main(String[] args) {
        String filename = "berlin52.tsp";
        List<City> cities = importData(filename);
        System.out.println("Zaimportowano " + cities.size() + " miast.");

        double[][] distances = calculateDistances(cities);

        // Test pierwszej wersji (First Improvement)
        Result resultFirst = localSearch2FirstImprovement(distances);
        System.out.println("\nWyniki LocalSearch2 (First Improvement):");
        System.out.println("Końcowa długość trasy: " + resultFirst.tourLength);
        System.out.println("Liczba iteracji: " + resultFirst.iterations);
        System.out.println("Końcowa permutacja:");
        for (int i : resultFirst.permutation) {
            System.out.print(i + " ");
        }

        System.out.println("\n");

        // Test drugiej wersji (Best Improvement)
        Result resultBest = localSearch2BestImprovement(distances);
        System.out.println("\nWyniki LocalSearch2 (Best Improvement):");
        System.out.println("Końcowa długość trasy: " + resultBest.tourLength);
        System.out.println("Liczba iteracji: " + resultBest.iterations);
        System.out.println("Końcowa permutacja:");
        for (int i : resultBest.permutation) {
            System.out.print(i + " ");
        }
    }
}