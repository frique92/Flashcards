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
    private ArrayList<String> log;

    Flashcards() {
        flashcards = new LinkedHashMap<>();
        scanner = new Scanner(System.in);
        flashcardsIsWorking = true;
        hardestCards = new HashMap<>();
        log = new ArrayList<>();
    }

    public void start() {
        while (flashcardsIsWorking) {
            outputMsg("Input the action (add, remove, import, export, ask, exit):");
            String command = getInputUser();

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
                case ("log"):
                    exportLog();
                    break;
                case ("exit"):
                    exit();
                    break;
                default:
                    outputMsg("Unexpected command: " + command);
            }
        }
    }

    private void add() {
        String term = "";
        String definition = "";
        boolean inputDataIsCorrect = true;

        outputMsg("The card:");
        term = getInputUser();

        if (flashcards.containsKey(term)) {
            outputMsg("The card \"" + term + "\" already exists. Try again:");
            inputDataIsCorrect = false;
        }

        if (inputDataIsCorrect) {
            outputMsg("The definition of the card:");
            definition = getInputUser();
            if (flashcards.containsValue(definition)) {
                outputMsg("The definition \"" + definition + "\" already exists. Try again:");
                inputDataIsCorrect = false;
            }
        }

        if (inputDataIsCorrect) {
            flashcards.put(term, definition);
            outputMsg("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
        }

    }

    private void remove() {
        outputMsg("The card:");
        String term = getInputUser();

        if (flashcards.containsKey(term)) {
            flashcards.remove(term);
            hardestCards.remove(term);
            outputMsg("The card has been removed.");
        } else outputMsg("Can't remove \"" + term + "\": there is no such card.");
    }

    private void importData() {

        outputMsg("File name:");
        String fileName = getInputUser();

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

            outputMsg(count + " cards have been loaded.");

        } catch (FileNotFoundException e) {
            outputMsg("File not found.");
        }

    }

    private void exportData() {

        outputMsg("File name:");
        String fileName = getInputUser();

        File file = new File(fileName);

        try (PrintWriter writer = new PrintWriter(file)) {

            for (Map.Entry<String, String> entry : flashcards.entrySet()) {
                writer.println(entry.getKey());
                writer.println(entry.getValue());
                writer.println(hardestCards.get(entry.getKey()));
            }

            outputMsg(flashcards.size() + " cards have been saved.");

        } catch (IOException e) {
            outputMsg("File not found.");
        }

    }

    private void exportLog() {

        outputMsg("File name:");
        String fileName = getInputUser();

        File file = new File(fileName);

        try (PrintWriter writer = new PrintWriter(file)) {

            for (String s : log) {
                writer.println(s);
            }

            outputMsg("The log has been saved.");

        } catch (IOException e) {
            outputMsg("File not found.");
        }

    }

    private void ask() {
        int numberOfCards = 0;
        ArrayList<String> keys = new ArrayList<>(flashcards.keySet());
        Random random = new Random();

        outputMsg("How many times to ask?");
        numberOfCards = Integer.parseInt(getInputUser());

        for (int i = 0; i < numberOfCards; i++) {
            String randomKey = keys.get(random.nextInt(keys.size()));

            outputMsg("Print the definition of \"" + randomKey + "\":");
            String definition = getInputUser();

            if (flashcards.get(randomKey).equals(definition)) {
                outputMsg("Correct answer");
            } else {
                String anyKeyByDefinition = getKeyByDefinition(definition);

                if (anyKeyByDefinition == null) {
                    outputMsg("Wrong answer. The correct one is \"" + flashcards.get(randomKey) + "\".");
                } else {
                    outputMsg("Wrong answer. The correct one is \""
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
        outputMsg("Bye bye!");
        flashcardsIsWorking = false;
    }

    private void getHardestCards() {
        if (hardestCards.size() == 0) {
            outputMsg("There are no cards with errors.");
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
                outputMsg("The hardest card is \"" + terms.get(0) + "\". You have " +
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

                outputMsg("The hardest cards are " + str + ". You have " +
                        maxErrors + " errors answering it.");
            }

        }
    }

    private void resetHardestCards() {
        hardestCards.clear();
        outputMsg("Card statistics has been reset.");
    }

    private String getKeyByDefinition(String definition) {

        for (Map.Entry<String, String> entry : flashcards.entrySet()) {
            if (entry.getValue().equals(definition)) return entry.getKey();
        }

        return null;
    }

    private void outputMsg(String message) {
        System.out.println(message);
        log.add(message);
    }

    private String getInputUser() {
        String data = scanner.nextLine();
        log.add(data);

        return data;
    }

}


