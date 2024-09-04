package task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("UAH", 1.0);     // 1 UAH = 1 UAH
        exchangeRates.put("USD", 41.26);   // 1 USD = 41.26 UAH
        exchangeRates.put("CAD", 30.46);   // 1 CAD = 30.46 UAH
        exchangeRates.put("EUR", 45.54);   // 1 EUR = 45.54 UAH
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть запит на конвертацію (наприклад, '100 UAH into USD'): ");
        String input = scanner.nextLine().trim();

        try {
            double result = convertCurrency(input);
            System.out.println("Результат конвертації: " + result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static double convertCurrency(String input) throws Exception {

        String[] parts = input.split("\\s+");

        if (parts.length != 4 || !parts[2].equalsIgnoreCase("into")) {
            throw new Exception("Неправильний формат введення. Спробуйте формат '100 UAH into USD'.");
        }

        double amount = Double.parseDouble(parts[0]);
        String fromCurrency = parts[1].toUpperCase();
        String toCurrency = parts[3].toUpperCase();

        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new Exception("Невідома валюта.");
        }

        double rateFrom = exchangeRates.get(fromCurrency);
        double rateTo = exchangeRates.get(toCurrency);

        double amountInUAH = amount * rateFrom;
        return amountInUAH / rateTo;
    }
}
