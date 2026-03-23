package com.game.snakesladders;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("Anu");
        playerNames.add("Nandu");
        playerNames.add("Pallavi");

        
        Game game = new Game(10, playerNames, 6, 6);
        game.start();
    }
}