package task5;

public class SubstringCounter {

    final static String[][] matrix = {
            {"hello", "world", "java"},
            {"example", "hello", "substring"},
            {"test", "string", "hello"}
    };

    final static String substring = "hello";

    public static void main(String[] args) {
        final int count = countSubstringOccurrences(matrix, substring);
        System.out.println("Кількість входжень підрядка \"" + substring + "\": " + count);
    }

    public static int countSubstringOccurrences(final String[][] matrix, final String substring) {
        int count = 0;

        for (String[] strings : matrix) {
            for (String currentString : strings) {
                count += countOccurrencesInString(currentString, substring);
            }
        }

        return count;
    }

    public static int countOccurrencesInString(final String str, final String substring) {
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count;
    }
}
