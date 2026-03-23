package com.game.snakesladders;
import java.util.*;

public class Board {
    private int size;
    private Map<Integer, Jumper> jumpers;

    public Board(int n, int numSnakes, int numLadders) {
        this.size = n;
        this.jumpers = new HashMap<>();
        initializeBoard(numSnakes, numLadders);
    }

    private void initializeBoard(int numSnakes, int numLadders) {
        Random rand = new Random();
        int maxCell = size * size;

        // Add Snakes
        while (jumpers.size() < numSnakes) {
            int start = rand.nextInt(maxCell - 1) + 2;
            int end = rand.nextInt(start - 1) + 1;
            if (isValidPlacement(start, end)) {
                jumpers.put(start, new Snake(start, end));
            }
        }

        // Add Ladders
        int target = numSnakes + numLadders;
        while (jumpers.size() < target) {
            int start = rand.nextInt(maxCell - 2) + 2;
            int end = rand.nextInt(maxCell - start) + (start + 1);
            if (isValidPlacement(start, end)) {
                jumpers.put(start, new Ladder(start, end));
            }
        }
    }

    private boolean isValidPlacement(int start, int end) {
        if (start >= size * size || jumpers.containsKey(start)) return false;
        int startRow = (start - 1) / size;
        int endRow = (end - 1) / size;
        return Math.abs(startRow - endRow) >= 1; 
    }

    public int getNextPosition(int pos) {
        if (jumpers.containsKey(pos)) {
            return jumpers.get(pos).getEnd();
        }
        return pos;
    }
}