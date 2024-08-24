package battleship;

import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    private final String name;
    private final int size;
    private int hits;
    private ArrayList<int[]> currentPosition;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hits = 0;
        this.currentPosition = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits == size;
    }

    public String getCurrentPosition() {
        StringBuilder sb = new StringBuilder();
        for (int[] pos : currentPosition) {
            sb.append(Arrays.toString(pos)).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        return sb.toString();
    }

    public void addCellPosition(int x, int y) {
        currentPosition.add(new int[] { x, y });
    }

    public static Ship getShipAtPosition(Ship[] ships, int[] position) {
        for (Ship ship : ships) {
            for (int[] shipPosition : ship.currentPosition) {
                if (Arrays.equals(shipPosition, position)) {
                    return ship;
                }
            }
        }
        return null;
    }
}
