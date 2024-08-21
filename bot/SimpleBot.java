package bot;

import java.util.Scanner;

public class SimpleBot {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greet("Aid", "2024");
        remindName();
        guessAge();
        count();
        test();
        end();

        // Close the scanner
        scanner.close();
    }

    static void greet(String assistantName, String birthYear) {
        System.out.printf("Hello! My name is %s.\n", assistantName);
        System.out.printf("I was created in %s.\n", birthYear);
    }

    static void remindName() {
        System.out.println("Please, remind me your name.");
        String name = scanner.nextLine();
        System.out.printf("What a great name you have, %s\n", name);
    }

    static void guessAge() {
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();

        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.printf("Your age is %d; that's a good time to start programming!\n", age);
    }

    static void count() {
        System.out.println("Now I will prove to you that I can count to any number you want.");

        int count = scanner.nextInt();
        for (int i = 0; i <= count; i++) {
            System.out.printf("%d!\n", i);
        }
    }

    static void test() {
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times.");
        System.out.println("2. To decompose a program into several small subroutines.");
        System.out.println("3. To determine the execution time of a program.");
        System.out.println("4. To interrupt the execution of a program.");

        int answer = scanner.nextInt();
        while (answer != 2) {
            System.out.println("Please, try again.");
            answer = scanner.nextInt();
        }
    }

    static void end() {
        System.out.println("Congratulations, have a nice day!");
    }
}
