package com.game.snakesladders;
import java.util.*;

public class Game {
    private Board board;
    private Dice dice;
    private List<Player> players;
    private List<Player> winners;
    private int boardSize;

    public Game(int n, List<String> names, int s, int l) {
        this.board = new Board(n, s, l);
        this.dice = new Dice();
        this.players = new LinkedList<>();
        this.winners = new ArrayList<>();
        this.boardSize = n * n;
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i)));
        }
    }

    public void start() {
        while (players.size() > 1) {
            Player p = players.remove(0);
            int roll = dice.roll();
            int next = p.getPosition() + roll;

            if (next <= boardSize) {
                int finalPos = board.getNextPosition(next);
                System.out.println(p.getName() + " rolled " + roll + " -> " + finalPos);
                p.setPosition(finalPos);
                if (finalPos == boardSize) {
                    System.out.println("⭐ " + p.getName() + " Finished!");
                    winners.add(p);
                    continue;
                }
            } else {
                System.out.println(p.getName() + " stayed at " + p.getPosition());
            }
            players.add(p);
        }
        System.out.println("\nRankings: " + winners.get(0).getName() + " (1st)");
    }
}