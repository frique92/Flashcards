package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Flashcards flashcards = new Flashcards();
        flashcards.start();

    }
}

class Flashcards {

    boolean flashcardsIsWorking;
    private Map<String, String> flashcards;
    private Scanner scanner;
    private Map<String, Integer> hardestCards;

    Flashcards() {
        flashcards = new LinkedHashMap<>();
        scanner = new Scanner(System.in);
        flashcardsIsWorking = true;
        hardestCards = new HashMap<>();
    }

    public void start() {
        while (flashcardsIsWorking) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String command = scanner.nextLine();

            switch (command) {
                case ("add"):
                    add();
                    break;
                case ("remove"):
                    remove();
                    break;
                case ("ask"):
                    ask();
                    break;
                case ("import"):
                    importData();
                    break;
                case ("export"):
                    exportData();
                    break;
                case ("hardest card"):
                    getHardestCards();
                    break;
                case ("reset stats"):
                    resetHardestCards();
                    break;
                case ("exit"):
                    exit();
                    break;
                default:
                    System.out.println("Unexpected command: " + command);
            }
        }
    }

    private void add() {
        String term = "";
        String definition = "";
        boolean inputDataIsCorrect = true;

        System.out.println("The card:");
        term = scanner.nextLine();

        if (flashcards.containsKey(term)) {
            System.out.println("The card \"" + term + "\" already exists. Try again:");
            inputDataIsCorrect = false;
        }

        if (inputDataIsCorrect) {
            System.out.println("The definition of the card:");
            definition = scanner.nextLine();
            if (flashcards.containsValue(definition)) {
                System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                inputDataIsCorrect = false;
            }
        }

        if (inputDataIsCorrect) {
            flashcards.put(term, definition);
            System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
        }

    }

    private void remove() {
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (flashcards.containsKey(term)) {
            flashcards.remove(term);
            System.out.println("The card has been removed.");
        } else System.out.println("Can't remove \"" + term + "\": there is no such card.");
    }

    private void importData() {

        System.out.println("File name:");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        try (Scanner scannerFile = new Scanner(file)) {
            int count = 0;
            while (scannerFile.hasNext()) {

                String term = scannerFile.nextLine();
                String definition = scannerFile.nextLine();
                int mistakes = Integer.parseInt(scannerFile.nextLine());

                flashcards.put(term, definition);
                hardestCards.put(term, mistakes);
                count++;
            }

            System.out.println(count + " cards have been loaded.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }

    private void exportData() {

        System.out.println("File name:");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        try (PrintWriter writer = new PrintWriter(file)) {

            for (Map.Entry<String, String> entry : flashcards.entrySet()) {
                writer.println(entry.getKey());
                writer.println(entry.getValue());
                writer.println(hardestCards.get(entry.getKey()));
            }

            System.out.println(flashcards.size() + " cards have been saved.");

        } catch (IOException e) {
            System.out.print("File not found.");
        }

    }

    private void ask() {
        int numberOfCards = 0;
        ArrayList<String> keys = new ArrayList<>(flashcards.keySet());
        Random random = new Random();

        System.out.println("How many times to ask?");
        numberOfCards = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfCards; i++) {
            String randomKey = keys.get(random.nextInt(keys.size()));

            System.out.println("Print the definition of \"" + randomKey + "\":");
            String definition = scanner.nextLine();

            if (flashcards.get(randomKey).equals(definition)) {
                System.out.println("Correct answer");
            } else {
                String anyKeyByDefinition = getKeyByDefinition(definition);

                if (anyKeyByDefinition == null) {
                    System.out.println("Wrong answer. The correct one is \"" + flashcards.get(randomKey) + "\".");
                } else {
                    System.out.println("Wrong answer. The correct one is \""
                            + flashcards.get(randomKey) + "\", you've just written the definition of \""
                            + anyKeyByDefinition + "\".");
                }

                if (hardestCards.containsKey(randomKey)) {
                    hardestCards.put(randomKey, hardestCards.get(randomKey) + 1);
                } else {
                    hardestCards.put(randomKey, 1);
                }
            }

        }

    }

    private void exit() {
        System.out.println("Bye bye!");
        flashcardsIsWorking = false;
    }

    private void getHardestCards() {
        if (hardestCards.size() == 0) {
            System.out.println("There are no cards with errors.");
        } else {

            ArrayList<String> terms = new ArrayList<>();
            int maxErrors = 0;

            for (Map.Entry<String, Integer> entry : hardestCards.entrySet()) {
                if (entry.getValue() > maxErrors) {
                    terms.clear();
                    terms.add(entry.getKey());
                    maxErrors = entry.getValue();
                } else if (entry.getValue() == maxErrors) {
                    terms.add(entry.getKey());
                }
            }

            if (terms.size() == 1) {
                System.out.println("The hardest card is \"" + terms.get(0) + "\". You have " +
                        maxErrors + " errors answering it.");
            } else {
                StringBuilder str = new StringBuilder();
                for (String term : terms) {
                    if ("".equals(str.toString())) {
                        str.append("\"").append(term).append("\"");
                    } else {
                        str.append(", \"").append(term).append("\"");
                    }
                }

                System.out.println("The hardest cards are " + str + ". You have " +
                        maxErrors + " errors answering it.");
            }

        }
    }

    private void resetHardestCards() {
        hardestCards.clear();
        System.out.println("Card statistics has been reset.");
    }

    private String getKeyByDefinition(String definition) {

        for (Map.Entry<String, String> entry : flashcards.entrySet()) {
            if (entry.getValue().equals(definition)) return entry.getKey();
        }

        return null;
    }

}


