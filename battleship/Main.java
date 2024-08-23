package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int SIZE = 10;
    private static final char EMPTY_CELL = '~';
    private static final char SHIP_CELL = 'O';
    private static final char HIT_CELL = 'X';
    private static final char MISS_CELL = 'M';
    private static final int[] SHIP_SIZES = { 5, 4, 3, 3, 2 };
    private static final String[] SHIP_NAMES = {
            "Aircraft Carrier",
            "Battleship",
            "Submarine",
            "Cruiser",
            "Destroyer"
    };

    private char[][] board;

    public Game() {
        this.board = new char[SIZE][SIZE];
        initializeBoard();
    }

    public void start() {
        printBoard(true);
        placeShips();
        startTheGame();

        // Close the scanner
        scanner.close();
    }

    private void startTheGame() {
        System.out.println("The game starts!");
        System.err.println();

        printBoard(false);

        System.out.println("Take a shot!");
        System.err.println();

        boolean isValidAttack = false;
        while (!isValidAttack) {
            String attack = scanner.nextLine();
            int[] coordinates = parseCoordinates(attack);

            if (coordinates[0] < 0 || coordinates[0] >= SIZE || coordinates[1] < 0 || coordinates[1] >= SIZE) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            if (board[coordinates[0]][coordinates[1]] == SHIP_CELL) {
                board[coordinates[0]][coordinates[1]] = HIT_CELL;

                printBoard(false);
                System.out.println("You hit a ship!");
                isValidAttack = true;
            } else if (board[coordinates[0]][coordinates[1]] == EMPTY_CELL) {
                board[coordinates[0]][coordinates[1]] = MISS_CELL;

                printBoard(false);
                System.out.println("You missed!");
                isValidAttack = true;
            } else {
                System.out.println("You've already shot there! Try again:");
                continue;
            }

            printBoard(true);
        }
    }

    private void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, EMPTY_CELL);
        }
    }

    /**
     * Print the board
     *
     * @param showShips - if true, show ships on the board
     */
    private void printBoard(boolean showShips) {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < SIZE; j++) {
                if (showShips) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print(board[i][j] == SHIP_CELL ? EMPTY_CELL + " " : board[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    private void placeShips() {
        for (int i = 0; i < SHIP_NAMES.length; i++) {
            System.out.println("Enter the coordinates of the " + SHIP_NAMES[i] + " (" + SHIP_SIZES[i] + " cells):");
            System.out.println();

            placeShip(SHIP_NAMES[i], SHIP_SIZES[i]);
            printBoard(true);
        }
    }

    /**
     * Place a ship on the board
     *
     * @param shipName - name of the ship to place
     * @param shipSize - size of the ship to place
     */
    private void placeShip(String shipName, int shipSize) {
        boolean isShipPlaced = false;

        while (!isShipPlaced) {
            String[] coordinates = scanner.nextLine().split(" ");

            if (coordinates.length != 2) {
                System.out.println("Error! You entered wrong coordinates! Try again:");
                continue;
            }

            int[] start = parseCoordinates(coordinates[0]);
            int[] end = parseCoordinates(coordinates[1]);
            int shipLength = getShipLength(start, end);

            if (shipLength != shipSize) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n", shipName);
                continue;
            }

            if (!isValidShipPlacement(start, end)) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }

            if (!isAreaClear(start, end)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }

            placeShipOnTheBoard(start, end);
            isShipPlaced = true;
        }

        System.out.println();
    }

    /**
     * Parse the coordinates
     *
     * @param coordinate - coordinate to parse
     * @return - parsed coordinates
     */
    private int[] parseCoordinates(String coordinateString) {
        int row = coordinateString.charAt(0) - 'A';
        int col = Integer.parseInt(coordinateString.substring(1)) - 1;

        return new int[] { row, col };
    }

    private int getShipLength(int[] start, int[] end) {
        if (start[0] == end[0]) {
            return Math.abs(start[1] - end[1]) + 1;
        } else {
            return Math.abs(start[0] - end[0]) + 1;
        }
    }

    private boolean isValidShipPlacement(int[] start, int[] end) {
        return start[0] == end[0] || start[1] == end[1];
    }

    private boolean isAreaClear(int[] start, int[] end) {
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

    private void placeShipOnTheBoard(int[] start, int[] end) {
        int startRow = Math.min(start[0], end[0]);
        int endRow = Math.max(start[0], end[0]);
        int startCol = Math.min(start[1], end[1]);
        int endCol = Math.max(start[1], end[1]);

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                board[i][j] = SHIP_CELL;
            }
        }
    }
}
