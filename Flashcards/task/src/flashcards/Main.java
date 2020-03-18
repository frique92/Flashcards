package flashcards;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> flashcards = new LinkedHashMap<>();

        System.out.println("Input the number of cards:");

        int numbersOfCards = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numbersOfCards; i++) {
            String name = "";
            String definition = "";
            boolean cardPresent = true;
            boolean definitionPresent = true;

            System.out.println("The card #" + i + ":");
            while (cardPresent) {
                name = scanner.nextLine();
                if (flashcards.containsValue(name)) {
                    System.out.println("The card \"" + name + "\" already exists. Try again:");
                } else cardPresent = false;
            }

            System.out.println("The definition of the card #" + i + ":");
            while (definitionPresent) {
                definition = scanner.nextLine();
                if (flashcards.containsKey(definition)) {
                    System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                } else definitionPresent = false;
            }

            flashcards.put(definition, name);
        }

        for (Map.Entry<String, String> entry : flashcards.entrySet()) {
            System.out.println("Print the definition of \"" + entry.getValue() + "\":");
            String definition = scanner.nextLine();

            if (entry.getKey().equals(definition)) System.out.println("Correct answer.");
            else if (flashcards.containsKey(definition)) {
                System.out.println("Wrong answer. The correct one is \""
                        + entry.getKey() + "\", you've just written the definition of \""
                        + flashcards.get(definition) + "\".");
            } else System.out.println("Wrong answer. The correct one is \"" + entry.getKey() + "\".");

        }

    }
}
