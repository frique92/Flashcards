import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {

        char[] arr = new char[10];

        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = reader.read(arr);

            for (int i = num - 1; i >= 0; i--) {
                System.out.print(arr[i]);
            }
        }
    }
}