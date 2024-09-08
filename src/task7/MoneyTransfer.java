package task7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyTransfer {

    static Bank bank1 = new Bank("Bank1");
    static Bank bank2 = new Bank("Bank2");

    static User user1 = new User("Alice");
    static User user2 = new User("Bob");

    static BankAccount account1 = new BankAccount("123",
            "UAH", 10000);
    static BankAccount account2 = new BankAccount("456",
            "USD", 5000);

    public static void main(String[] args) {
        user1.addAccount(account1);
        user2.addAccount(account2);

        bank1.addAccount(account1);
        bank2.addAccount(account2);

        bank1.transfer(account1, bank2, account2, 1000, user1, user2); //6%

        System.out.println("Alice's UAH balance: " + account1.getBalance());
        System.out.println("Bob's USD balance: " + account2.getBalance());
    }

    public static class CurrencyConverter {
        private static final Map<String, Double> exchangeRates = new HashMap<>();

        static {
            exchangeRates.put("UAH", 1.0);     // Base currency
            exchangeRates.put("USD", 41.26);
            exchangeRates.put("CAD", 30.46);
            exchangeRates.put("EUR", 45.54);
        }

        public static double convert(double amount, String fromCurrency,
                                     String toCurrency) {
            if (fromCurrency.equals(toCurrency)) {
                return amount;
            }

            final double rateFrom = exchangeRates.get(fromCurrency);
            final double rateTo = exchangeRates.get(toCurrency);

            final double amountInUAH = amount * rateFrom;
            return amountInUAH / rateTo;
        }
    }

    public static class Bank {
        private final String name;
        private final Map<String, BankAccount> accounts = new HashMap<>();

        public Bank(String name) {
            this.name = name;
        }

        public void addAccount(BankAccount account) {
            accounts.put(account.getAccountNumber(), account);
        }

        public BankAccount getAccount(String accountNumber) {
            return accounts.get(accountNumber);
        }

        public void transfer(final BankAccount fromAccount, final Bank toBank,
                             final BankAccount toAccount, final double amount,
                             final User fromUser, final User toUser) {
            final boolean isSameBank = this.name.equals(toBank.name);
            final boolean isSameUser = fromUser == toUser;

            double feePercentage = calculateFee(isSameBank, isSameUser);

            double convertedAmount = CurrencyConverter.convert(amount,
                    fromAccount.getCurrency(), toAccount.getCurrency());

            double totalAmountAfterFee = convertedAmount * (1 - feePercentage);

            fromAccount.withdraw(amount);

            toAccount.deposit(totalAmountAfterFee);
        }

        private double calculateFee(boolean isSameBank, boolean isSameUser) {
            if (isSameUser && isSameBank) {
                return 0.0;
            } else if (isSameUser) {
                return 0.02;
            } else if (isSameBank) {
                return 0.03;
            } else {
                return 0.06;
            }
        }
    }

    public static class BankAccount {
        private final String accountNumber;
        private final String currency;
        private double balance;

        public BankAccount(String accountNumber, String currency, double initialBalance) {
            this.accountNumber = accountNumber;
            this.currency = currency;
            this.balance = initialBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getCurrency() {
            return currency;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient balance.");
            }
            balance -= amount;
        }
    }

    public static class User {
        private final String name;
        private final List<BankAccount> accounts = new ArrayList<>();

        public User(String name) {
            this.name = name;
        }

        public void addAccount(BankAccount account) {
            accounts.add(account);
        }

        public List<BankAccount> getAccounts() {
            return accounts;
        }

        public BankAccount findAccount(String accountNumber) {
            for (BankAccount account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }
}
