package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private final static Scanner scanner = new Scanner(System.in);

    private int availableWater = 400;
    private int availableMilk = 540;
    private int availableCoffeeBeans = 120;
    private int availableCups = 9;
    private int availableMoney = 550;

    public static void main(String[] args) {
        var machine = new CoffeeMachine();
        machine.selectAction();

        scanner.close();
    }

    private void selectAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();

            System.out.println();

            switch (action) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    showRemainingStock();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid action");
                    System.out.println();
            }
        }
    }

    private void showRemainingStock() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", availableWater);
        System.out.printf("%d ml of milk\n", availableMilk);
        System.out.printf("%d g of coffee beans\n", availableCoffeeBeans);
        System.out.printf("%d of disposable cups\n", availableCups);
        System.out.printf("$%d of money\n", availableMoney);
        System.out.println();
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String option = scanner.next();

        if (!option.equals("back")) {
            showBuyOptions(option);
        }

        System.out.println();
    }

    private void showBuyOptions(String option) {
        switch (option) {
            case "1":
                makeCoffee(250, 0, 16, 4);
                break;
            case "2":
                makeCoffee(350, 75, 20, 7);
                break;
            case "3":
                makeCoffee(200, 100, 12, 6);
                break;
            case "back":
                break;
            default:
                System.out.println("Invalid option");
                System.out.println();
        }
    }

    private void makeCoffee(int water, int milk, int coffeeBeans, int money) {
        if (availableWater < water) {
            System.out.println("Sorry, not enough water!");
            return;
        } else if (availableMilk < milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        } else if (availableCoffeeBeans < coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        } else if (availableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        } else {
            System.out.println("I have enough resources, making you a coffee!");

            availableWater -= water;
            availableMilk -= milk;
            availableCoffeeBeans -= coffeeBeans;
            availableCups--;
            availableMoney += money;
        }
    }

    private void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        availableWater += scanner.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        availableMilk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        availableCoffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        availableCups += scanner.nextInt();

        System.out.println();
    }

    private void take() {
        System.out.printf("I gave you $%d\n", availableMoney);
        availableMoney = 0;

        System.out.println();
    }
}
