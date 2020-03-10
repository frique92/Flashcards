import java.util.Scanner;

public class Main {

    public static String prepareFullName(String firstName, String lastName) {

        String chap1 = firstName == null ? " " : firstName;
        String chap2 = lastName == null ? " " : lastName;

        return chap1 + " " + chap2;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String firstName = scanner.nextLine();
        firstName = "null".equals(firstName) ? null : firstName;

        String lastName = scanner.nextLine();
        lastName = "null".equals(lastName) ? null : lastName;

        System.out.println(prepareFullName(firstName, lastName));
    }
}