package com.example.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates markers for demo/testing.
 * * REFACTORED STATE:
 * - Uses MarkerStyleFactory to ensure identical styles are shared.
 * - MapMarker now accepts the shared MarkerStyle object.
 */
public class MapDataSource {

    private static final String[] SHAPES = {"PIN", "CIRCLE", "SQUARE"};
    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "ORANGE"};
    private static final int[] SIZES = {10, 12, 14, 16};

    public List<MapMarker> loadMarkers(int count) {
        Random rnd = new Random(7);
        List<MapMarker> out = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            double lat = 12.9000 + rnd.nextDouble() * 0.2000;
            double lng = 77.5000 + rnd.nextDouble() * 0.2000;
            String label = "M-" + i;

            // Force many duplicates by choosing from small pools
            String shape = SHAPES[rnd.nextInt(SHAPES.length)];
            String color = COLORS[rnd.nextInt(COLORS.length)];
            int size = SIZES[rnd.nextInt(SIZES.length)];
            boolean filled = rnd.nextBoolean();

            // REFACTORED: Obtain a shared Flyweight instance from the factory
            MarkerStyle sharedStyle = MarkerStyleFactory.get(shape, color, size, filled);

            // REFACTORED: Pass the shared style to the MapMarker constructor
            out.add(new MapMarker(lat, lng, label, sharedStyle));
        }
        return out;
    }
}