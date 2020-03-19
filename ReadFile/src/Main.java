import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        File file = new File("dataset_91069.txt");

        Map<String, Long> populations = new LinkedHashMap<>();

        try (Scanner scanner = new Scanner(file)) {

            boolean firstLine = true;
            while (scanner.hasNext()) {
                if (firstLine) {
                    scanner.nextLine();
                    firstLine = false;
                    continue;
                }

                String year = scanner.next();
                long population = Long.parseLong(scanner.next().replace(",", ""));

                populations.put(year, population);

            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: ");
        }

        long lastPopulation = 0;

        long largestIncrease = 0;
        String largestYear = "";

        for (Map.Entry<String, Long> entry : populations.entrySet()) {

            if (lastPopulation != 0 && entry.getValue() - lastPopulation > largestIncrease) {
                largestIncrease = entry.getValue() - lastPopulation;
                largestYear = entry.getKey();

            }

            lastPopulation = entry.getValue();
        }

        System.out.println(largestYear);

    }
}
