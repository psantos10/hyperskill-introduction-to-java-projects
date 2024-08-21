package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        String gameState = scanner.nextLine();
        displayGrid(gameState);

        scanner.close();
    }

    void displayGrid(String gameState) {
        var firstLine = gameState.substring(0, 3).toCharArray();
        var secondLine = gameState.substring(3, 6).toCharArray();
        var thirdLine = gameState.substring(6).toCharArray();

        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", firstLine[0], firstLine[1], firstLine[2]);
        System.out.printf("| %c %c %c |\n", secondLine[0], secondLine[1], secondLine[2]);
        System.out.printf("| %c %c %c |\n", thirdLine[0], thirdLine[1], thirdLine[2]);
        System.out.println("---------");
    }
}
