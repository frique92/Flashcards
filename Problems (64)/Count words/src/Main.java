import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int chr;
            StringBuilder str = new StringBuilder();

            while ((chr = reader.read()) != -1) {
                str.append((char) chr);
            }

            String res = str.reverse().toString();

            int length = res.split("\\s+").length;

            System.out.println(Math.max(0, length));

        }
    }

}