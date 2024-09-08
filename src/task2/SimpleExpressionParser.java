package task2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleExpressionParser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть вираз: ");
        String expression = scanner.nextLine().trim();

        try {
            final double result = evaluateExpression(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static double evaluateExpression(final String expression) throws Exception {
        Pattern pattern = Pattern.compile(
                "\\s*(\\d+\\.?\\d*)\\s*([+\\-*/])\\s*(\\d+\\.?\\d*)\\s*(=\\s*\\?\\s*)?$");
        Matcher matcher = pattern.matcher(expression);

        if (!matcher.matches()) {
            throw new Exception("Неправильний формат виразу.");
        }

        final double operand1 = Double.parseDouble(matcher.group(1));
        final String operator = matcher.group(2);
        final double operand2 = Double.parseDouble(matcher.group(3));

        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Ділення на нуль.");
                }
                yield operand1 / operand2;
            }
            default -> throw new Exception("Непідтримувана операція.");
        };
    }
}
