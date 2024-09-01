package task4;

import java.util.Scanner;

public class CharacterCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть рядок: ");
        String input = scanner.nextLine();

        char[] chars = input.toCharArray();

        CharCount[] charCounts = new CharCount[256]; // try dynamic

        for (int i = 0; i < 256; i++) {
            charCounts[i] = new CharCount((char) i, 0);
        }

        for (char c : chars) {
            charCounts[c].count++;
        }

        System.out.println("Кількість кожного символу у рядку:");
        for (CharCount pair : charCounts) {
            if (pair.count > 0) {
                System.out.println(pair);
            }
        }
    }

    public static class CharCount {
        char character;
        int count;

        public CharCount(char character, int count) {
            this.character = character;
            this.count = count;
        }

        @Override
        public String toString() {
            return character + " : " + count;
        }
    }

}
