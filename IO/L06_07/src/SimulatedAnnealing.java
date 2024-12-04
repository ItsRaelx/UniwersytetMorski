import java.util.Random;

public class SimulatedAnnealing {
    private static final double INITIAL_TEMPERATURE = 1000;
    private static final double COOLING_RATE = 0.2;
    private static final int N = 500;
    private static final int MAX_ITER = 1000;
    private final Random random = new Random();
    private final double[][] costMatrix;

    public SimulatedAnnealing(double[][] costMatrix) {
        this.costMatrix = costMatrix;
    }

    public Solution solve() {
        Solution currentSolution = new Solution(costMatrix.length);
        currentSolution.calculateCost(costMatrix);
        Solution bestSolution = new Solution(currentSolution);

        double temperature = INITIAL_TEMPERATURE;

        for (int iter = 0; iter < MAX_ITER; iter++) {
            for (int i = 0; i < N; i++) {
                Solution newSolution = new Solution(currentSolution);

                // Generate neighbor by swapping three random assignments (A)
                /* Original code (2 swaps):
                int worker1 = random.nextInt(costMatrix.length);
                int worker2 = random.nextInt(costMatrix.length);
                newSolution.swapAssignments(worker1, worker2);
                */

                // New code with 3 swaps (B)
                int worker1 = random.nextInt(costMatrix.length);
                int worker2 = random.nextInt(costMatrix.length);
                int worker3 = random.nextInt(costMatrix.length);
                newSolution.swapAssignments(worker1, worker2);
                newSolution.swapAssignments(worker2, worker3);
                newSolution.calculateCost(costMatrix);

                double costDifference = currentSolution.getCost() - newSolution.getCost();

                if (costDifference > 0 || Math.exp(costDifference / temperature) > random.nextDouble()) {
                    currentSolution = newSolution;
                    if (currentSolution.getCost() < bestSolution.getCost()) {
                        bestSolution = new Solution(currentSolution);
                    }
                }
            }
            temperature *= COOLING_RATE;
        }

        return bestSolution;
    }
}