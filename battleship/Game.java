package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int SIZE = 10;
    private static final int SHIPS_COUNT = 5;
    private static final char EMPTY_CELL = '~';
    private static final char SHIP_CELL = 'O';
    private static final char HIT_CELL = 'X';
    private static final char MISS_CELL = 'M';

    private char[][] board;
    private Ship[] ships;

    public Game() {
        this.board = new char[SIZE][SIZE];
        this.ships = new Ship[SHIPS_COUNT];
        initializeShips();
        initializeBoard();
    }

    public void start() {
        printBoard(true);
        placeShips();
        startTheGame();

        // Close the scanner
        scanner.close();
    }

    private void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, EMPTY_CELL);
        }
    }

    private void initializeShips() {
        ships[0] = new Ship("Aircraft Carrier", 5);
        ships[1] = new Ship("Battleship", 4);
        ships[2] = new Ship("Submarine", 3);
        ships[3] = new Ship("Cruiser", 3);
        ships[4] = new Ship("Destroyer", 2);
    }

    private void startTheGame() {
        printMessage("The game starts!", false);
        printBoard(false);
        printMessage("Take a shot!", false);

        while (!isGameOver()) {
            attack();
        }
    }

    private boolean isGameOver() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }

        printMessage("You sank the last ship. You won. Congratulations!", false);
        return true;
    }

    private void attack() {
        boolean isValidAttack = false;
        while (!isValidAttack) {
            String attack = scanner.nextLine();
            int[] coordinates = parseCoordinates(attack);

            System.out.println();

            if (coordinates[0] < 0 || coordinates[0] >= SIZE || coordinates[1] < 0 || coordinates[1] >= SIZE) {
                printMessage("Error! You entered the wrong coordinates! Try again:", false);
                continue;
            }

            if (board[coordinates[0]][coordinates[1]] == SHIP_CELL) {
                board[coordinates[0]][coordinates[1]] = HIT_CELL;

                printBoard(false);

                var hittedShip = Ship.getShipAtPosition(ships, coordinates);
                hittedShip.hit();

                if (hittedShip.isSunk()) {
                    printMessage("You sank a ship! Specify a new target:", false);
                } else {
                    printMessage("You hit a ship! Try again:", false);
                }

                isValidAttack = true;
            } else if (board[coordinates[0]][coordinates[1]] == EMPTY_CELL) {
                board[coordinates[0]][coordinates[1]] = MISS_CELL;

                printBoard(false);
                printMessage("You missed! Try again:", false);
                isValidAttack = true;
            } else {
                board[coordinates[0]][coordinates[1]] = HIT_CELL;

                printBoard(false);

                printMessage("You hit a ship! Try again:", false);
                continue;
            }
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
        for (int i = 0; i < this.ships.length; i++) {
            var shipName = ships[i].getName();
            var shipSize = ships[i].getSize();

            var message = String.format("Enter the coordinates of the %s (%d cells):", shipName, shipSize);
            printMessage(message, false);

            placeShip(shipName, shipSize);
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
                printMessage("Error! You entered wrong coordinates! Try again:");
                continue;
            }

            int[] start = parseCoordinates(coordinates[0]);
            int[] end = parseCoordinates(coordinates[1]);
            int shipLength = getShipLength(start, end);

            if (shipLength != shipSize) {
                var message = String.format("Error! Wrong length of the %s! Try again:", shipName);
                printMessage(message);
                continue;
            }

            if (!isValidShipPlacement(start, end)) {
                printMessage("Error! Wrong ship location! Try again:");
                continue;
            }

            if (!isAreaClear(start, end)) {
                printMessage("Error! You placed it too close to another one. Try again:");
                continue;
            }

            placeShipOnTheBoard(shipName, start, end);

            isShipPlaced = true;
        }

        System.out.println();
    }

    private void printMessage(String message) {
        printMessage(message, true);
    }

    private void printMessage(String message, boolean emptyFirstLine) {
        if (emptyFirstLine) {
            System.out.println();
        }

        System.out.printf("%s\n\n", message);
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

    private void placeShipOnTheBoard(String shipName, int[] start, int[] end) {
        int startRow = Math.min(start[0], end[0]);
        int endRow = Math.max(start[0], end[0]);
        int startCol = Math.min(start[1], end[1]);
        int endCol = Math.max(start[1], end[1]);
        Ship ship = findShipByName(shipName);

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                board[i][j] = SHIP_CELL;

                // Set the ship's position
                ship.addCellPosition(i, j);
            }
        }
    }

    private Ship findShipByName(String name) {
        for (Ship ship : ships) {
            if (ship.getName().equals(name)) {
                return ship;
            }
        }

        return null;
    }
}
