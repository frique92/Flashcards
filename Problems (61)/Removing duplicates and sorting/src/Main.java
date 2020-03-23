import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Set<String> set = new TreeSet<>();

        int size = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < size; i++) {
            set.add(scanner.nextLine());
        }

        for (String s : set) {
            System.out.println(s);
        }

    }
}