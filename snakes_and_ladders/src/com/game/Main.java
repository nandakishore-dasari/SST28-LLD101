package com.game.snakesladders;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Nandini", "Sir", "Gemini");
        Game game = new Game(10, names, 5, 5);
        game.start();
    }
}