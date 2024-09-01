package task5;

public class SubstringCounter {

    public static void main(String[] args) {
        String[][] matrix = {
                {"hello", "world", "java"},
                {"example", "hello", "substring"},
                {"test", "string", "hello"}
        };

        String substring = "hello";

        int count = countSubstringOccurrences(matrix, substring);
        System.out.println("Кількість входжень підрядка \"" + substring + "\": " + count);
    }

    public static int countSubstringOccurrences(String[][] matrix, String substring) {
        int count = 0;

        for (String[] strings : matrix) {
            for (String currentString : strings) {
                count += countOccurrencesInString(currentString, substring);
            }
        }

        return count;
    }

    public static int countOccurrencesInString(String str, String substring) {
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count;
    }
}
