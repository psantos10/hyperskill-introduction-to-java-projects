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
        String firstLine = String.join(" ", gameState.substring(0, 3).split(""));
        String secondLine = String.join(" ", gameState.substring(3, 6).split(""));
        String thirdLine = String.join(" ", gameState.substring(6).split(""));

        System.out.println("---------");
        System.out.printf("| %s |\n", firstLine);
        System.out.printf("| %s |\n", secondLine);
        System.out.printf("| %s |\n", thirdLine);
        System.out.println("---------");
    }
}
