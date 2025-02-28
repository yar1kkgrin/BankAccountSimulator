package src.main;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankSystem bankSystem = new BankSystem();
        String fileName = "transactions";

        System.out.println("Welcome to the banking system!");

        while (true) {
            System.out.println("Main menu:\n" +
                    "1. Register a new user\n" +
                    "2. Open an account\n" +
                    "3. Top up your account\n" +
                    "4. Withdraw money\n" +
                    "5. View your balance\n" +
                    "6. View your transaction history\n" +
                    "7. Log out");
            System.out.println("Select the option that suits you (1-7):");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();
                    bankSystem.registerUser(name);
                }

                case 2 -> {
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    User user = bankSystem.getUserByName(name);

                    if (user == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    bankSystem.createAccount(user);
                    Integer accountNum = user.getAccounts().get(0).getAccountNumber();
                    System.out.println("Account number: " + accountNum);
                }

                case 3 -> {
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    User user = bankSystem.getUserByName(name);

                    if (user == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    System.out.println("Enter the replenishment amount:");
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    bankSystem.processTransaction(user, user.getAccounts().get(0).getAccountNumber(), amount, true);  // Пополнение первого аккаунта
                    FileManager.saveTransaction(fileName, "Deposit: " + amount);
                }

                case 4 -> {
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    User user = bankSystem.getUserByName(name);

                    if (user == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    System.out.println("Enter the withdrawal amount:");
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    bankSystem.processTransaction(user, user.getAccounts().get(0).getAccountNumber(), amount, false);
                    FileManager.saveTransaction(fileName, "Withdrawal: " + amount);
                }

                case 5 -> {
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    User user = bankSystem.getUserByName(name);

                    if (user == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    List<BankAccount> accounts = user.getAccounts();

                    if (accounts.isEmpty()) {
                        System.out.println("No accounts found.");
                    } else {
                        System.out.println("Account balances:");
                        for (BankAccount account : accounts) {
                            System.out.println("Account Number: " + account.getAccountNumber() + " Balance: " + account.getBalance());
                        }
                    }
                }

                case 6 -> {
                    List<String> transactions = FileManager.loadTransactions(fileName);

                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        System.out.println("Transaction history:");
                        for (String transaction : transactions) {
                            System.out.println(transaction);
                        }
                    }
                }

                case 7 -> {
                    System.out.println("Exiting the banking system...");
                    scanner.close();
                    break;
                }
            }
        }
    }
}
