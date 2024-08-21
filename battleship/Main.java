package battleship;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int SIZE = 10;
    private static final char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        System.out.println("Enter the coordinates of the ship:");
        String input = scanner.nextLine();
        String[] coordinates = input.split(" ");

        if (coordinates.length != 2) {
            System.out.println("Error!");
            return;
        }

        String coord1 = coordinates[0];
        String coord2 = coordinates[1];

        int[] start = getCoordinates(coord1);
        int[] end = getCoordinates(coord2);

        if (!isValidCoordinate(start) || !isValidCoordinate(end)) {
            System.out.println("Error!");
            return;
        }

        if (!isValidShipPlacement(start, end)) {
            System.out.println("Error!");
            return;
        }

        int shipLength = getShipLength(start, end);
        String shipParts = getShipParts(start, end);

        System.out.println("Length: " + shipLength);
        System.out.println("Parts: " + shipParts);
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '~';
            }
        }
    }

    private static void printBoard() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getCoordinates(String input) {
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        return new int[] { row, col };
    }

    private static boolean isValidCoordinate(int[] coordinate) {
        int row = coordinate[0];
        int col = coordinate[1];
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    private static boolean isValidShipPlacement(int[] start, int[] end) {
        return start[0] == end[0] || start[1] == end[1];
    }

    private static int getShipLength(int[] start, int[] end) {
        if (start[0] == end[0]) {
            return Math.abs(start[1] - end[1]) + 1;
        } else {
            return Math.abs(start[0] - end[0]) + 1;
        }
    }

    private static String getShipParts(int[] start, int[] end) {
        StringBuilder parts = new StringBuilder();
        if (start[0] == end[0]) {
            int row = start[0];
            int startCol = Math.min(start[1], end[1]);
            int endCol = Math.max(start[1], end[1]);
            for (int col = startCol; col <= endCol; col++) {
                parts.append((char) ('A' + row)).append(col + 1).append(" ");
            }
        } else {
            int col = start[1];
            int startRow = Math.min(start[0], end[0]);
            int endRow = Math.max(start[0], end[0]);
            for (int row = startRow; row <= endRow; row++) {
                parts.append((char) ('A' + row)).append(col + 1).append(" ");
            }
        }
        return parts.toString().trim();
    }
}
