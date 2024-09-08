package task4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharacterCounter {

    final static String devString = "AABBCCCAABC"; //A-4 B-3 C-4

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть рядок: ");
        //String input = scanner.nextLine();


        char[] chars = devString.toCharArray(); //input.toCharArray();

        Map<Character, Integer> result = countChars(chars);

        System.out.println("Кількість кожного символу у рядку:");
        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static Map<Character, Integer> countChars(char[] chars) {
        Map<Character, Integer> charCounts = new HashMap<>();

        for (char c : chars) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        return charCounts;
    }
}
