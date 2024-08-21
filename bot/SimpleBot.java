package bot;

import java.util.Scanner;

public class SimpleBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! My name is Aid.");
        System.out.println("I was created in 2024.");

        System.out.println("Please, remind me your name.");
        String name = scanner.nextLine();

        System.out.printf("What a great name you have, %s\n", name);

        // Guess the age
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();

        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.printf("Your age is %d; that's a good time to start programming!\n", age);

        // Teach the bot to count
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int count = scanner.nextInt();
        for (int i = 0; i <= count; i++) {
            System.out.printf("%d!\n", i);
        }
        System.out.println("Completed, have a nice day!");

        // Close the scanner
        scanner.close();
    }
}
