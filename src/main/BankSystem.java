package src.main;

import java.util.*;

public class BankSystem {
    Map<String, Integer> users = new HashMap<>();
    private int accountCounter = 1;
    FileManager fileManager = new FileManager();

    User user;

    public void registerUser(String name) {
        for (String user : users.keySet()) {
            if (user.equals(name)) {
                System.out.println("User already exists.");
                return;
            }
        }
        users.put(name, 1);
        System.out.println("User registered successfully.");
    }

    public void createAccount(User user) {
        int newAccountNumber = accountCounter++;

        BankAccount newAccount = new BankAccount(newAccountNumber, 0, new ArrayList<>());

        user.addAccount(newAccount);

        System.out.println("Account created with number: " + newAccountNumber);
    }

    public Integer getUser(String name) {
        for (Map.Entry<String, Integer> entry : users.entrySet()) {
            if (entry.getKey().equals(name)) {
                System.out.println("User with name: " + name + " has ID:");
                return entry.getValue();
            }
        }
        System.out.println("User with name: " + name + " doesn't exist");
        return null;
    }

    public void processTransaction(User user, int accountNumber, double amount, boolean isDeposit) {
        BankAccount account = null;
        for (BankAccount acc : user.getAccounts()) {
            if (acc.getAccountNumber() == accountNumber) {
                account = acc;
                break;
            }
        }

        if (account == null) {
            System.out.println("Error: Account not found.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Error: Amount must be greater than 0.");
            return;
        }

        if (isDeposit) {
            account.deposit(amount);
            account.addTransaction("Replenishment: +" + amount);
            System.out.println("Replenishment successful. New balance: " + account.getBalance());
        } else {
            if (account.getBalance() < amount) {
                System.out.println("Error: Insufficient funds.");
                return;
            }

            account.withdraw(amount);
            account.addTransaction("Withdrawal: -" + amount);
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        }

        FileManager.saveTransaction("transactions", "Score " + accountNumber + ": " + (isDeposit ? "+" : "-") + amount);
    }


}
