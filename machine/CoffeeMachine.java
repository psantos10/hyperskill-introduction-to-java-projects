package machine;

import java.util.Scanner;

public class CoffeeMachine {
    final static int WATER_PER_CUP = 200;
    final static int MILK_PER_CUP = 50;
    final static int COFFEE_BEANS_PER_CUP = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has:");
        int availableWater = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        int availableMilk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int availableCoffeeBeans = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int numberOfCups = scanner.nextInt();

        int maxCups = Math.min(availableWater / WATER_PER_CUP,
                Math.min(availableMilk / MILK_PER_CUP, availableCoffeeBeans / COFFEE_BEANS_PER_CUP));

        if (numberOfCups > maxCups) {
            System.out.printf("No, I can make only %d cup(s) of coffee\n", maxCups);
        } else if (numberOfCups == maxCups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            int extraCups = maxCups - numberOfCups;
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", extraCups);
        }

        scanner.close();
    }
}
