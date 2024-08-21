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

    public void start() {
        Scanner scanner = new Scanner(System.in);

        String gameState = scanner.nextLine();
        setGrid(gameState);
        displayGrid();
        analyzeGameState();

        scanner.close();
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

    private void analyzeGameState() {
        boolean xWins = checkWinner('X');
        boolean oWins = checkWinner('O');

        if (xWins && oWins || Math.abs(countChar('X') - countChar('O')) > 1) {
            System.out.println("Impossible");
        } else if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else if (countChar('_') == 0) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
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
