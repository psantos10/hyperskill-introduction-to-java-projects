package tictactoe;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    public void start() {
        System.out.println("X O X");
        System.out.println("O X O");
        System.out.println("X X O");
    }
}
