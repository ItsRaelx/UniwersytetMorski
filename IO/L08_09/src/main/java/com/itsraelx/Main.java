package com.itsraelx;

import java.io.IOException;

public class Main {
    private static final int NUMBER_OF_EXPERIMENTS = 10;

    public static void main(String[] args) throws IOException {
        double[][] costMatrix = ImportData.importData("assign100.txt"); // Tested with "assign100.txt" and "assign500.txt"
        double totalCost = 0;

        for (int i = 0; i < NUMBER_OF_EXPERIMENTS; i++) {
            TabuSearch tb = new TabuSearch(costMatrix);
            Solution solution = tb.solve();
            totalCost += solution.getCost();


            System.out.println("Solution " + (i + 1) + ":");
            System.out.println(solution.getCost());
//            System.out.println("Assignments (Worker -> Task):");
//            int[] assignments = solution.getAssignments();
//            for (int j = 0; j < assignments.length; j++) {
//                System.out.println("Worker " + j + " -> Task " + assignments[j]);
//            }
//            System.out.println();
        }

        double averageCost = totalCost / NUMBER_OF_EXPERIMENTS;
        System.out.println("Average cost over " + NUMBER_OF_EXPERIMENTS + " experiments: " + averageCost);
    }
}