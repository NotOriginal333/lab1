package task1;

import java.util.Scanner;

public class DecimalToBinary {

    public static String convertToBinary(int decimalNumber) {
        StringBuilder binaryNumber = new StringBuilder();

        if (decimalNumber == 0) {
            return "0";
        }

        while (decimalNumber > 0) {
            int remainder = decimalNumber % 2;
            binaryNumber.insert(0, remainder);
            decimalNumber = decimalNumber / 2;
        }

        return binaryNumber.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле число: ");
        int decimalNumber = scanner.nextInt();

        String binaryNumber = convertToBinary(decimalNumber);

        System.out.println("Число " + decimalNumber + " у двійковій системі: " + binaryNumber);
    }
}
