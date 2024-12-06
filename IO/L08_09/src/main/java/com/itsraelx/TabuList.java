package com.itsraelx;

import java.util.ArrayList;

class Move {
    private final int worker1;
    private final int worker2;
    private final int task1;
    private final int task2;

    public Move(int worker1, int worker2, int task1, int task2) {
        this.worker1 = worker1;
        this.worker2 = worker2;
        this.task1 = task1;
        this.task2 = task2;
    }

    public int getWorker1() {
        return worker1;
    }

    public int getWorker2() {
        return worker2;
    }

    public int getTask1() {
        return task1;
    }

    public int getTask2() {
        return task2;
    }
}

public class TabuList {
    private final int size;
    private final ArrayList<Move> moves;

    public TabuList(int size) {
        this.size = size;
        this.moves = new ArrayList<>();
    }

    public void addMove(int worker1, int worker2, int task1, int task2) {
        Move move = new Move(worker1, worker2, task1, task2);
        if (moves.size() == size) {
            moves.removeFirst();
        }

        if (!moves.contains(move)) {
            moves.add(move);
        }
    }

    public boolean containsMove(int worker1, int worker2, int task1, int task2) {
        return moves.contains(new Move(worker1, worker2, task1, task2));
    }
}
