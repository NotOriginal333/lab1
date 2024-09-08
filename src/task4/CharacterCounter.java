package task4;

import java.util.Scanner;

public class CharacterCounter {

    final static String devString = "ddAABBCCCAABC"; //A-4 B-3 C-4 d-2

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть рядок: ");
        //String input = scanner.nextLine();


        char[] chars = devString.toCharArray(); //input.toCharArray();

        boolean[] processed = new boolean[256];

        System.out.println("Кількість кожного символу у рядку:");

        for (char c : chars) {
            if (!processed[c]) {
                countChars ch = new countChars(c);
                System.out.println(ch.getSymbol() + " : " + ch.countSymbols(chars));
                processed[c] = true;
            }
        }
    }

    public static class countChars {
        private final char symbol;
        private int count = 0;

        public countChars(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        public int getCount() {
            return count;
        }

        public int countSymbols(char[] chars) {
            for (char ch : chars) {
                if (ch == symbol) {
                    count++;
                }
            }
            return count;
        }

    }
}
