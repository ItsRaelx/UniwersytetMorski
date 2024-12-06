package com.itsraelx;

public class TabuSearch {
    private static final int MAX_ITER = 1000;
    private static final int TABU_TENURE = 50;
    private final double[][] costMatrix;

    public TabuSearch(double[][] costMatrix) {
        this.costMatrix = costMatrix;
    }

    public Solution solve() {
        // Generate initial solution
        Solution currentSolution = new Solution(costMatrix.length);
        currentSolution.shuffleAssignments();
        currentSolution.calculateCost(costMatrix);

        // Initialize best solutions
        Solution bestSolution = new Solution(currentSolution);
        Solution globalBestSolution = new Solution(currentSolution);
        TabuList tabuList = new TabuList(TABU_TENURE);

        for (int iter = 0; iter < MAX_ITER; iter++) {
            Solution bestNeighbor = null;
            double bestNeighborCost = Double.MAX_VALUE;

            // Try all possible swaps
            for (int i = 0; i < costMatrix.length; i++) {
                for (int j = i + 1; j < costMatrix.length; j++) {
                    Solution neighbor = new Solution(currentSolution);
                    int[] assignments = neighbor.getAssignments();
                    int temp = assignments[i];
                    assignments[i] = assignments[j];
                    assignments[j] = temp;

                    neighbor.calculateCost(costMatrix);

                    // Update best neighbor if it's not tabu or if it's better than current best
                    if (!tabuList.containsMove(i, j, assignments[i], assignments[j]) &&
                        neighbor.getCost() < bestNeighborCost) {
                        bestNeighbor = new Solution(neighbor);
                        bestNeighborCost = neighbor.getCost();
                    }
                }
            }

            // If no valid neighbor found, diversify
            if (bestNeighbor == null) {
                currentSolution = new Solution(costMatrix.length);
                currentSolution.shuffleAssignments();
                currentSolution.calculateCost(costMatrix);
                continue;
            }

            // Update current solution
            currentSolution = bestNeighbor;

            // Update best solution if necessary
            if (currentSolution.getCost() < bestSolution.getCost()) {
                bestSolution = new Solution(currentSolution);
            }

            // Update global best if necessary
            if (bestSolution.getCost() < globalBestSolution.getCost()) {
                globalBestSolution = new Solution(bestSolution);
            }

            // Add move to tabu list
            int[] assignments = currentSolution.getAssignments();
            for (int i = 0; i < assignments.length - 1; i++) {
                tabuList.addMove(i, i + 1, assignments[i], assignments[i + 1]);
            }
        }

        return globalBestSolution;
    }
}
