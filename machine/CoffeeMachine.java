package machine;

import java.util.Scanner;

public class CoffeeMachine {
    final static int WATER_PER_CUP = 200;
    final static int MILK_PER_CUP = 50;
    final static int COFFEE_BEANS_PER_CUP = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many cups of coffee you will need:");
        int numberOfCups = scanner.nextInt();

        System.out.printf("For %d cups of coffee you will need:\n", numberOfCups);
        System.out.printf("%d ml of water\n", numberOfCups * WATER_PER_CUP);
        System.out.printf("%d ml of milk\n", numberOfCups * MILK_PER_CUP);
        System.out.printf("%d g of coffee beans\n", numberOfCups * COFFEE_BEANS_PER_CUP);

        scanner.close();
    }
}
