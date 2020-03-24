import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> arr = new ArrayList<>();

        String line = scanner.nextLine();

        String[] strArr = line.split("\\s+");

        for (String s : strArr) {
            arr.add(Integer.parseInt(s));
        }

        int n = Integer.parseInt(scanner.nextLine());


        ArrayList<Integer> nearestNumbers = new ArrayList<>();

        int nearDistance = Math.abs(arr.get(0) - n);

        for (Integer el : arr) {

            int currDistance = Math.abs(n - el);

            if (currDistance == nearDistance) nearestNumbers.add(el);
            else if (currDistance < nearDistance) {
                nearestNumbers.clear();
                nearestNumbers.add(el);

                nearDistance = currDistance;
            }
        }

        Collections.sort(nearestNumbers);

        for (Integer nearestNumber : nearestNumbers) {
            System.out.print(nearestNumber + " ");
        }

    }
}