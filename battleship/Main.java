package battleship;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int SIZE = 10;
    private static final char[][] board = new char[SIZE][SIZE];

    private static final int[] SHIP_LENGTHS = { 5, 4, 3, 3, 2 };
    private static final String[] SHIP_NAMES = { "Aircraft Carrier", "Battleship", "Submarine", "Cruiser",
            "Destroyer" };

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        for (int i = 0; i < SHIP_NAMES.length; i++) {
            placeShip(SHIP_NAMES[i], SHIP_LENGTHS[i]);
            printBoard();
        }
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

    private static void placeShip(String shipName, int shipSize) {
        boolean placed = false;
        while (!placed) {
            System.out.println("Enter the coordinates of the " + shipName + " (" + shipSize + " cells):");
            String input = scanner.nextLine();
            String[] coordinates = input.split(" ");

            if (coordinates.length != 2) {
                System.out.println("Error! Wrong input format! Try again:");
                continue;
            }

            int[] start = getCoordinates(coordinates[0]);
            int[] end = getCoordinates(coordinates[1]);

            if (!isValidCoordinate(start) || !isValidCoordinate(end)) {
                System.out.println("Error! Coordinates out of bounds! Try again:");
                continue;
            }

            if (!isValidShipPlacement(start, end)) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }

            int shipLength = getShipLength(start, end);

            if (shipLength != shipSize) {
                System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
                continue;
            }

            if (!isAreaClear(start, end)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }

            placeShipOnBoard(start, end);
            placed = true;
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

    private static boolean isAreaClear(int[] start, int[] end) {
        int startRow = Math.min(start[0], end[0]);
        int endRow = Math.max(start[0], end[0]);
        int startCol = Math.min(start[1], end[1]);
        int endCol = Math.max(start[1], end[1]);

        for (int i = startRow - 1; i <= endRow + 1; i++) {
            for (int j = startCol - 1; j <= endCol + 1; j++) {
                if (i >= 0 && i < SIZE && j >= 0 && j < SIZE && board[i][j] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeShipOnBoard(int[] start, int[] end) {
        if (start[0] == end[0]) {
            int row = start[0];
            int startCol = Math.min(start[1], end[1]);
            int endCol = Math.max(start[1], end[1]);
            for (int col = startCol; col <= endCol; col++) {
                board[row][col] = 'O';
            }
        } else {
            int col = start[1];
            int startRow = Math.min(start[0], end[0]);
            int endRow = Math.max(start[0], end[0]);
            for (int row = startRow; row <= endRow; row++) {
                board[row][col] = 'O';
            }
        }
    }
}
