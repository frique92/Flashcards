import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        char[] charOfFirstWord = line.toLowerCase().toCharArray();
        line = scanner.nextLine();
        char[] charOfSecondWord = line.toLowerCase().toCharArray();

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        addArrayToHashMap(map1, charOfFirstWord);
        addArrayToHashMap(map2, charOfSecondWord);

        int countOfDeleteChar = 0;

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                Integer value1 = entry.getValue();
                Integer value2 = map2.get(entry.getKey());

                if (value1 > value2) {
                    countOfDeleteChar += value1 - value2;
                    map1.put(entry.getKey(), value2);
                }

            } else {
                countOfDeleteChar += entry.getValue();
            }
        }

        for (Map.Entry<Character, Integer> entry : map2.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                Integer value1 = entry.getValue();
                Integer value2 = map1.get(entry.getKey());

                if (value1 > value2) {
                    countOfDeleteChar += value1 - value2;
                    map2.put(entry.getKey(), value2);
                }

            } else {
                countOfDeleteChar += entry.getValue();
            }
        }

        System.out.println(countOfDeleteChar);

    }

    public static void addArrayToHashMap(Map<Character, Integer> map, char[] array) {
        for (char ch : array) {
            if (map.containsKey(ch)) {
                int value = map.get(ch);
                map.put(ch, value + 1);
            } else {
                map.put(ch, 1);
            }
        }
    }
}