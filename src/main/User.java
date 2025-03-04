package src.main;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<BankAccount> accounts;

    public User(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    public void addAccount(BankAccount account) {
        if (account == null) {
            System.out.println("Invalid account.");
            return;
        }

        for (BankAccount bankAccount : accounts) {
            if (bankAccount.getAccountNumber() == account.getAccountNumber()) {
                System.out.println("Account already exists.");
                return;
            }
        }

        accounts.add(account);
    }

    public List<BankAccount> returnAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        }
        return accounts;
    }
}
