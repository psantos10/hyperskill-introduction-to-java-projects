package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private char[][] grid = new char[3][3];
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        String initialGameState = "_________";
        setGrid(initialGameState);
        displayGrid();

        boolean onGoingGame = true;
        char currentPlayer = 'X';

        while (onGoingGame) {
            makeMove(currentPlayer);
            displayGrid();

            char winner = gameResult();
            if (winner == 'X' || winner == 'O') {
                System.out.println(winner + " wins");
                onGoingGame = false;
            } else if (winner == 'D') {
                System.out.println("Draw");
                onGoingGame = false;
            } else {
                // Game is on going
                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    private int[] getCoordinates() {
        int xPos = 0;
        int yPos = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                xPos = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    yPos = scanner.nextInt();
                    break;
                } else {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                }
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        return new int[] { xPos, yPos };
    }

    private void makeMove(char player) {
        while (true) {
            var coordinates = getCoordinates();
            int xPos = coordinates[0];
            int yPos = coordinates[1];

            if (xPos < 1 || xPos > 3 || yPos < 1 || yPos > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (grid[xPos - 1][yPos - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            grid[xPos - 1][yPos - 1] = player;
            break;
        }
    }

    private void displayGrid() {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", grid[0][0], grid[0][1], grid[0][2]);
        System.out.printf("| %c %c %c |\n", grid[1][0], grid[1][1], grid[1][2]);
        System.out.printf("| %c %c %c |\n", grid[2][0], grid[2][1], grid[2][2]);
        System.out.println("---------");
    }

    private void setGrid(String gameState) {
        grid[0] = gameState.substring(0, 3).toCharArray();
        grid[1] = gameState.substring(3, 6).toCharArray();
        grid[2] = gameState.substring(6).toCharArray();
    }

    private boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
                return true;
            }

            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
                return true;
            }
        }

        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }

        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true;
        }

        return false;
    }

    private char gameResult() {
        boolean xWins = checkWinner('X');
        boolean oWins = checkWinner('O');

        if (xWins && oWins || Math.abs(countChar('X') - countChar('O')) > 1) {
            // System.out.println("Impossible");
            throw new IllegalStateException("Impossible");
        } else if (xWins) {
            // System.out.println("X wins");
            return 'X';
        } else if (oWins) {
            // System.out.println("O wins");
            return 'O';
        } else if (countChar('_') == 0) {
            // System.out.println("Draw");
            return 'D';
        } else {
            // System.out.println("Game not finished");
            return 'G';
        }
    }

    private int countChar(char c) {
        int count = 0;

        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == c) {
                    count++;
                }
            }
        }

        return count;
    }
}
