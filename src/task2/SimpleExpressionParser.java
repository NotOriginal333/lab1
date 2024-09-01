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
            double result = evaluateExpression(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static double evaluateExpression(String expression) throws Exception {
        Pattern pattern = Pattern.compile("\\s*(\\d+\\.?\\d*)\\s*([+\\-*/])\\s*(\\d+\\.?\\d*)\\s*(=\\s*\\?\\s*)?$");
        Matcher matcher = pattern.matcher(expression);

        if (!matcher.matches()) {
            throw new Exception("Неправильний формат виразу.");
        }

        double operand1 = Double.parseDouble(matcher.group(1));
        String operator = matcher.group(2);
        double operand2 = Double.parseDouble(matcher.group(3));

        double result;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Ділення на нуль.");
                }
                result = operand1 / operand2;
                break;
            default:
                throw new Exception("Непідтримувана операція.");
        }

        return result;
    }
}
