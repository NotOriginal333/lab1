package task7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {

    @Test
    public void testTransferBetweenOwnAccountsSameBank() {
        //Test transfers money between accounts of the same user within the same bank.
        MoneyTransfer.Bank bank = new MoneyTransfer.Bank("Bank1");
        MoneyTransfer.User user = new MoneyTransfer.User("Alice");
        MoneyTransfer.BankAccount account1 = new MoneyTransfer.BankAccount("123",
                "UAH", 10000);

        MoneyTransfer.BankAccount account2 = new MoneyTransfer.BankAccount("456",
                "UAH", 5000);

        user.addAccount(account1);
        user.addAccount(account2);
        bank.addAccount(account1);
        bank.addAccount(account2);

        bank.transfer(account1, bank, account2, 1000, user, user);

        assertEquals(9000, account1.getBalance());
        assertEquals(6000, account2.getBalance());
    }

    @Test
    public void testTransferBetweenOwnAccountsDifferentBanks() {
        //Test transfers money between accounts of the same user in different banks.
        MoneyTransfer.Bank bank1 = new MoneyTransfer.Bank("Bank1");
        MoneyTransfer.Bank bank2 = new MoneyTransfer.Bank("Bank2");
        MoneyTransfer.User user = new MoneyTransfer.User("Alice");
        MoneyTransfer.BankAccount account1 = new MoneyTransfer.BankAccount("123",
                "UAH", 10000);

        MoneyTransfer.BankAccount account2 = new MoneyTransfer.BankAccount("456",
                "USD", 5000);

        user.addAccount(account1);
        user.addAccount(account2);
        bank1.addAccount(account1);
        bank2.addAccount(account2);

        bank1.transfer(account1, bank2, account2, 1000, user, user);

        double expectedDeposit = MoneyTransfer.CurrencyConverter.convert(1000,
                "UAH", "USD") * 0.98;

        assertEquals(9000, account1.getBalance());
        assertEquals(5000 + expectedDeposit, account2.getBalance());
    }

    @Test
    public void testTransferBetweenDifferentUsersSameBank() {
        //Test transfers money between different users within the same bank.
        MoneyTransfer.Bank bank = new MoneyTransfer.Bank("Bank1");
        MoneyTransfer.User user1 = new MoneyTransfer.User("Alice");
        MoneyTransfer.User user2 = new MoneyTransfer.User("Bob");
        MoneyTransfer.BankAccount account1 = new MoneyTransfer.BankAccount("123",
                "UAH", 10000);

        MoneyTransfer.BankAccount account2 = new MoneyTransfer.BankAccount("456",
                "UAH", 5000);

        user1.addAccount(account1);
        user2.addAccount(account2);
        bank.addAccount(account1);
        bank.addAccount(account2);

        bank.transfer(account1, bank, account2, 1000, user1, user2);

        assertEquals(9000, account1.getBalance());
        assertEquals(5000 + 1000 * 0.97, account2.getBalance()); // 3% комісії
    }

    @Test
    public void testTransferBetweenDifferentUsersDifferentBanks() {
        //Test transfers money between different users in different banks.
        MoneyTransfer.Bank bank1 = new MoneyTransfer.Bank("Bank1");
        MoneyTransfer.Bank bank2 = new MoneyTransfer.Bank("Bank2");
        MoneyTransfer.User user1 = new MoneyTransfer.User("Alice");
        MoneyTransfer.User user2 = new MoneyTransfer.User("Bob");
        MoneyTransfer.BankAccount account1 = new MoneyTransfer.BankAccount("123",
                "UAH", 10000);

        MoneyTransfer.BankAccount account2 = new MoneyTransfer.BankAccount("456",
                "USD", 5000);

        user1.addAccount(account1);
        user2.addAccount(account2);
        bank1.addAccount(account1);
        bank2.addAccount(account2);

        bank1.transfer(account1, bank2, account2, 1000, user1, user2);

        double expectedDeposit = MoneyTransfer.CurrencyConverter.convert(1000,
                "UAH", "USD") * 0.94;

        assertEquals(9000, account1.getBalance());
        assertEquals(5000 + expectedDeposit, account2.getBalance());
    }

    @Test
    public void testCurrencyConversion() {
        //Test correctness of currency conversion.
        double amountInUAH = 1000;
        double amountInUSD = MoneyTransfer.CurrencyConverter.convert(amountInUAH,
                "UAH", "USD");

        assertEquals(amountInUAH / 41.26, amountInUSD, 0.01);
    }

    @Test
    public void testInsufficientFunds() {
        //Test behavior with insufficient balance.
        MoneyTransfer.Bank bank = new MoneyTransfer.Bank("Bank1");
        MoneyTransfer.User user = new MoneyTransfer.User("Alice");
        MoneyTransfer.BankAccount account1 = new MoneyTransfer.BankAccount("123",
                "UAH", 500);
        MoneyTransfer.BankAccount account2 = new MoneyTransfer.BankAccount("456",
                "UAH", 1000);

        user.addAccount(account1);
        user.addAccount(account2);
        bank.addAccount(account1);
        bank.addAccount(account2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, bank, account2, 1000, user, user);
        });

        assertEquals("Insufficient balance.", exception.getMessage());
    }
}
