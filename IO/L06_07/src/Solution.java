public class Solution {
    private final int[] assignments; // assignments[i] represents which task worker i is assigned to
    private double cost;
    private final int size;

    public Solution(int size) {
        this.size = size;
        this.assignments = new int[size];
        // Initially assign each worker to corresponding task (0->0, 1->1, etc.)
        for (int i = 0; i < size; i++) {
            assignments[i] = i;
        }
    }

    public Solution(Solution other) {
        this.size = other.size;
        this.assignments = other.assignments.clone();
        this.cost = other.cost;
    }

    public void swapAssignments(int worker1, int worker2) {
        int temp = assignments[worker1];
        assignments[worker1] = assignments[worker2];
        assignments[worker2] = temp;
    }

    public void calculateCost(double[][] costMatrix) {
        cost = 0;
        for (int i = 0; i < size; i++) {
            cost += costMatrix[i][assignments[i]];
        }
    }

    public double getCost() {
        return cost;
    }

    public int[] getAssignments() {
        return assignments;
    }
}