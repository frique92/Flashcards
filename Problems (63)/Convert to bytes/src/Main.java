import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;

        byte[] arr = inputStream.readAllBytes();
        for (byte b : arr) {
            System.out.print(b);
        }
    }
}