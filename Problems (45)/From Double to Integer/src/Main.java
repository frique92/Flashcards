import java.util.*;

public class Main {

    public static int convert(Double val) {
        if (val.isNaN()) return 0;
        else if (val.equals(Double.POSITIVE_INFINITY)) return Integer.MAX_VALUE;
        else if (val.equals(Double.NEGATIVE_INFINITY)) return Integer.MIN_VALUE;
        else {
            return val.intValue();
        }

    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double doubleVal = new Double(scanner.nextDouble() / scanner.nextDouble());
        System.out.println(convert(doubleVal));

        Long val = Long.parseLong("4321");

    }
}