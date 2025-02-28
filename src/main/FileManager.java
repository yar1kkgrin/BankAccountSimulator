package src.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveTransaction(String fileName, String transactionData) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transactionData + "\n");
        } catch (IOException e) {
            System.out.println("File writing error: " + e.getMessage());
        }
    }

    public static List<String> loadTransactions(String fileName) {
        List<String> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }

        return transactions;
    }
}
