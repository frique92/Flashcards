import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> dictionary = new HashSet<>();
        Set<String> words = new HashSet<>();

        int d = scanner.nextInt();

        for (int i = 0; i < d; i++) {
            dictionary.add(scanner.next().toLowerCase());
        }

        int l = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < l; i++) {
            String line = scanner.nextLine().toLowerCase();
            String[] arr = line.split(" ");

            words.addAll(Arrays.asList(arr));
        }

        for (String el: words) {
            if (!dictionary.contains(el)) System.out.println(el);
        }

    }
}