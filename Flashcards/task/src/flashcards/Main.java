package flashcards;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numbersOfCards = scanner.nextInt();
        scanner.nextLine();

        String[] cards = new String[numbersOfCards];
        String[] definitionCards = new String[numbersOfCards];

        int count = 0;
        for (int i = 0; i < numbersOfCards; i++) {
            count++;

            System.out.println("The card #" + count + ":");
            String name = scanner.nextLine();
            System.out.println("The definition of the card #" + count + ":");
            String definition = scanner.nextLine();

            cards[i] = name;
            definitionCards[i] = definition;
        }

        for (int i = 0; i < cards.length; i++) {
            System.out.println("Print the definition of \"" + cards[i] + "\":");
            String definition = scanner.nextLine();
            if (definition.equals(definitionCards[i])) {
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer. The correct one is \"" + definitionCards[i] + "\".");
            }
        }

    }
}
