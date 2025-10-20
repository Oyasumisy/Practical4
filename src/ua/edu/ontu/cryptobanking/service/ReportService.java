package ua.edu.ontu.cryptobanking.service;

import ua.edu.ontu.cryptobanking.model.users.User;
import ua.edu.ontu.cryptobanking.model.currencies.Currency;
import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

import java.util.Comparator;
import java.util.List;

public class ReportService extends BankingService {

    public ReportService() {
        super();
    }

    public void generateUserReport() {
        System.out.println("\n=== ЗВІТ ПО КОРИСТУВАЧАХ ===");
        List<User> users = getUsers();
        for (User user : users) {
            System.out.println(user.getDescription());
            System.out.println("Тип акаунта: " + user.getAccountType());
            user.showSpecialFeatures();
            System.out.println("---");
        }
    }

    public void generateCurrencyReport() {
        System.out.println("\n=== ЗВІТ ПО ВАЛЮТАХ ===");
        List<Currency> currencies = getCurrencies();
        for (Currency currency : currencies) {
            System.out.println(currency.getDescription());
            System.out.println("Тип валюти: " + currency.getCurrencyType());
            System.out.println("Курс: " + currency.getExchangeRate());
            System.out.println("---");
        }
    }

    public void generateOperationsReport() {
        System.out.println("\n=== ЗВІТ ПО ОПЕРАЦІЯХ ===");
        List<FinancialOperation> operations = getOperations();
        if (operations.isEmpty()) {
            System.out.println("Операції відсутні");
            return;
        }

        for (FinancialOperation operation : operations) {
            System.out.println(operation.getOperationDetails());
            System.out.println("Тип операції: " + operation.getOperationType());
            System.out.println("Статус: " + operation.getStatus());
            System.out.println("---");
        }
    }

    public void generateFullReport() {
        generateSystemReport();
        generateUserReport();
        generateCurrencyReport();
        generateOperationsReport();
    }

    public void generateEnhancedReport() {
        System.out.println("\n=== РОЗШИРЕНИЙ ЗВІТ ===");

        List<User> users = getUsers();
        double totalBalance = users.stream().mapToDouble(User::getBalance).sum();
        User richestUser = users.stream().max(Comparator.comparing(User::getBalance)).orElse(null);

        System.out.println("Загальний баланс всіх користувачів: " + totalBalance);
        System.out.println("Користувач з найбільшим балансом: " +
                (richestUser != null ? richestUser.getName() + " (" + richestUser.getBalance() + ")" : "немає"));

        List<FinancialOperation> operations = getOperations();
        long completedOps = operations.stream().filter(op -> "COMPLETED".equals(op.getStatus())).count();
        System.out.println("Усього операцій: " + operations.size() + ", успішних: " + completedOps);

    }

    void calculateStatistics() {
        System.out.println("ReportService: розрахунок статистики...");
        processDailyFees();
    }
}