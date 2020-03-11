import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        scanner.nextLine();
        String arrayString = scanner.nextLine();

        String[] array = arrayString.split(" ");
        String[] resArray = new String[count];

        for (int i = 0; i < count; i++) {
            int j = i + 1;

            if (j == count) j = 0;

            resArray[j] = array[i];
        }

        for (int i = 0; i < count; i++) {
            System.out.print(resArray[i] + " ");
        }

    }
}