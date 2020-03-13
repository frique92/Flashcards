import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        while (!str.equals("0")) {
            try {
                int num = Integer.parseInt(str);
                System.out.println(num * 10);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Invalid user input: " + str);
            }
            str = scanner.nextLine();
        }

    }
}