import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String arrayLine = scanner.nextLine();
        int rotation = scanner.nextInt();

        String[] arrayStr = arrayLine.split(" ");
        int[] resArray = new int[arrayStr.length];

        for (int i = 0; i < arrayStr.length; i++) {
            resArray[i] = Integer.parseInt(arrayStr[i]);
        }

        //System.out.println(Arrays.toString(resArray));

        int[] rotArray = new int[resArray.length];

        for (int i = 0; i < resArray.length; i++) {

            int resIndex = i;

            for (int j = 0; j < rotation % resArray.length; j++) {

                if (resIndex == resArray.length - 1) resIndex = 0;
                else resIndex++;
            }

            rotArray[resIndex] = resArray[i];
        }

        for (int i = 0; i < rotArray.length; i++) {
            System.out.print(rotArray[i] + " ");
        }
    }
}