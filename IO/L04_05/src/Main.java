import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
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
                distances[j][i] = distance; // Macierz odległości jest symetryczna
            }
        }

        return distances;
    }

    // Funkcja pomocnicza do obliczania odległości euklidesowej między dwoma miastami
    private static double calculateDistance(City city1, City city2) {
        double dx = city1.x - city2.x;
        double dy = city1.y - city2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Wybierz pierwszą permutację 0-1-2-3-4....-n-1
    private static int[] getInitialPermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }
        return permutation;
    }

    // Losowo generuj permutacje
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

    // Funkcja do obliczania długości trasy przy użyciu macierzy odległości
    public static double calculateTourLength(double[][] distances, int[] permutation) {
        int n = distances.length;
        double tourLength = 0;
        for (int i = 0; i < n - 1; i++) {
            int from = permutation[i];
            int to = permutation[i + 1];
            tourLength += distances[from][to];
        }
        // Dodaj odległość z ostatniego miasta do pierwszego
        tourLength += distances[permutation[n - 1]][permutation[0]];
        return tourLength;
    }

    // Implementacja algorytmu LocalSearch_1
    public static int[] localSearch1(double[][] distances) {
        boolean isLocalOptimum = false;
        int[] currentSolution = getInitialPermutation(distances.length);
        double currentLength = calculateTourLength(distances, currentSolution);

        do {
            int[] neighborSolution = selectNeighborSolution(currentSolution);
            double newLength = calculateTourLength(distances, neighborSolution);

            if (newLength < currentLength) {
                currentSolution = neighborSolution;
                currentLength = newLength;
            } else {
                isLocalOptimum = true;
            }
        } while (!isLocalOptimum);

        return currentSolution;
    }

    // Funkcja do wyboru rozwiązania z otoczenia (bez zamiany miasta o indeksie 0)
    private static int[] selectNeighborSolution(int[] currentSolution) {
        int[] newSolution = currentSolution.clone();
        Random rand = new Random();
        int i = rand.nextInt(newSolution.length - 1) + 1; // Wybierz indeks od 1 do (n-1)
        int j;
        do {
            j = rand.nextInt(newSolution.length - 1) + 1; // Wybierz indeks od 1 do (n-1)
        } while (i == j);

        // Zamiana dwóch losowych miast (z pominięciem miasta o indeksie 0)
        int temp = newSolution[i];
        newSolution[i] = newSolution[j];
        newSolution[j] = temp;

        return newSolution;
    }

    // Funkcja do zamiany wszystkich możliwych par miast (z pominięciem miasta o indeksie 0)
    private static int[] swapAllPairs(double[][] distances, int[] currentSolution) {
        int[] bestSolution = currentSolution.clone();
        double bestLength = calculateTourLength(distances, bestSolution);
        System.out.println("Początkowa długość trasy (SwapAllPairs): " + bestLength);

        int improvement = 0;
        for (int i = 1; i < currentSolution.length - 1; i++) {
            for (int j = i + 1; j < currentSolution.length; j++) {
                int[] newSolution = currentSolution.clone();
                // Zamiana miast
                int temp = newSolution[i];
                newSolution[i] = newSolution[j];
                newSolution[j] = temp;

                double newLength = calculateTourLength(distances, newSolution);
                if (newLength < bestLength) {
                    bestSolution = newSolution;
                    bestLength = newLength;
                    System.out.println("Poprawa " + (++improvement) + ": Nowa długość trasy = " + bestLength);
                }
            }
        }

        return bestSolution;
    }

    // Funkcja znajdująca najlepsze rozwiązanie spośród wszystkich sąsiadów
    private static int[] findBestNeighbor(double[][] distances, int[] currentSolution) {
        int[] bestNeighbor = currentSolution.clone();
        double bestLength = calculateTourLength(distances, bestNeighbor);

        for (int i = 1; i < currentSolution.length - 1; i++) {
            for (int j = i + 1; j < currentSolution.length; j++) {
                int[] newSolution = currentSolution.clone();
                // Zamiana miast
                int temp = newSolution[i];
                newSolution[i] = newSolution[j];
                newSolution[j] = temp;

                double newLength = calculateTourLength(distances, newSolution);
                if (newLength < bestLength) {
                    bestNeighbor = newSolution;
                    bestLength = newLength;
                }
            }
        }

        return bestNeighbor;
    }

    // Nowy algorytm przeszukiwania lokalnego używający funkcji findBestNeighbor
    public static int[] localSearchBestNeighbor(double[][] distances) {
        int[] currentSolution = generateRandomPermutation(distances.length);
        double currentLength = calculateTourLength(distances, currentSolution);
        System.out.println("Początkowa długość trasy (LocalSearchBestNeighbor): " + currentLength);

        boolean improved;
        int iteration = 0;
        do {
            int[] bestNeighbor = findBestNeighbor(distances, currentSolution);
            double newLength = calculateTourLength(distances, bestNeighbor);

            improved = newLength < currentLength;
            if (improved) {
                currentSolution = bestNeighbor;
                currentLength = newLength;
                System.out.println("Poprawa " + (++iteration) + ": Nowa długość trasy = " + currentLength);
            }
        } while (improved);

        return currentSolution;
    }


    public static void main(String[] args) {
        String filename = "berlin52.tsp";
        List<City> cities = importData(filename);

        System.out.println("Zaimportowano " + cities.size() + " miast.");

        double[][] distances = calculateDistances(cities);

        int[] initialPermutation = getInitialPermutation(cities.size());
        System.out.println("\nPoczątkowa permutacja:");
        for (int value : initialPermutation) {
            System.out.printf("%d ", value);
        }

        double initialTourLength = calculateTourLength(distances, initialPermutation);
        System.out.println("\nPoczątkowa długość trasy: " + initialTourLength);

        int[] optimizedPermutation = localSearch1(distances);
        double optimizedTourLength = calculateTourLength(distances, optimizedPermutation);

        System.out.println("\nZoptymalizowana permutacja (LocalSearch1):");
        for (int k : optimizedPermutation) {
            System.out.printf("%d ", k);
        }
        System.out.println("\nZoptymalizowana długość trasy (LocalSearch1): " + optimizedTourLength);

        int[] swapAllPairsPermutation = swapAllPairs(distances, initialPermutation);
        double swapAllPairsTourLength = calculateTourLength(distances, swapAllPairsPermutation);

        System.out.println("\nZoptymalizowana permutacja (SwapAllPairs):");
        for (int j : swapAllPairsPermutation) {
            System.out.printf("%d ", j);
        }
        System.out.println("\nZoptymalizowana długość trasy (SwapAllPairs): " + swapAllPairsTourLength);

        int[] localSearchBestNeighborPermutation = localSearchBestNeighbor(distances);
        double localSearchBestNeighborTourLength = calculateTourLength(distances, localSearchBestNeighborPermutation);

        System.out.println("\nZoptymalizowana permutacja (LocalSearchBestNeighbor):");
        for (int j : localSearchBestNeighborPermutation) {
            System.out.printf("%d ", j);
        }
        System.out.println("\nZoptymalizowana długość trasy (LocalSearchBestNeighbor): " + localSearchBestNeighborTourLength);
    }
}