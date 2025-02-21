import java.util.List;

public class BankAccount {
    private int accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(int accountNumber, double balance, List<String> transactionHistory) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", transactionHistory=" + transactionHistory +
                '}';
    }

    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("The deposit amount must be positive.");
            }
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void withdraw(double amount) {
        try {
            if (balance < amount) {
                throw new IllegalArgumentException("Insufficient balance for the withdrawal.");
            }

            balance -= amount;
            transactionHistory.add("Taken: " + amount);
            System.out.println("Successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addTransaction(String operation) {
        if (operation != null && !operation.trim().isEmpty()) {
            transactionHistory.add(operation);
        } else {
            System.out.println("Invalid transaction operation.");
        }
    }

    public List<String> returnTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        }
        return transactionHistory;
    }


}
