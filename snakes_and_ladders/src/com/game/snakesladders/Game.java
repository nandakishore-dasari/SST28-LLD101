package com.game.snakesladders;
import java.util.*;

public class Game {
    private Board board;
    private Dice dice;
    private List<Player> players;
    private List<Player> winners;
    private int boardSize;

    public Game(int n, List<String> names, int snakes, int ladders) {
        this.board = new Board(n, snakes, ladders);
        this.dice = new Dice();
        this.players = new LinkedList<>();
        this.winners = new ArrayList<>();
        this.boardSize = n * n;

        for (int i = 0; i < names.size(); i++) {
            this.players.add(new Player(names.get(i)));
        }
    }

    public void start() {
        // Game continues until only 1 player is left
        while (players.size() > 1) {
            Player currentPlayer = players.remove(0); // Poll from front
            int roll = dice.roll();
            int nextPos = currentPlayer.getPosition() + roll;

            if (nextPos <= boardSize) {
                int finalPos = board.getNextPosition(nextPos);
                System.out.println(currentPlayer.getName() + " rolled " + roll + " and moved to " + finalPos);
                currentPlayer.setPosition(finalPos);

                if (finalPos == boardSize) {
                    System.out.println("⭐ " + currentPlayer.getName() + " Finished!");
                    winners.add(currentPlayer);
                    continue; // Remove from active players
                }
            } else {
                System.out.println(currentPlayer.getName() + " rolled " + roll + " but needs exact move. Stays at " + currentPlayer.getPosition());
            }
            players.add(currentPlayer); // Move to back of line
        }
        printResults();
    }

    private void printResults() {
        System.out.println("\n--- Final Rankings ---");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println("Rank " + (i + 1) + ": " + winners.get(i).getName());
        }
        if (players.size() > 0) {
            System.out.println("Last Place: " + players.get(0).getName());
        }
    }
}