import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();
        int from = scanner.nextInt();
        int to = scanner.nextInt();

        char[] chars = word.toCharArray();

        StringBuilder res = new StringBuilder();
        for (int i = from; i <= to; i++) {
            res.append(chars[i]);
        }

        System.out.println(res);
    }
}