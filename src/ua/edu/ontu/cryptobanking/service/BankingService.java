package ua.edu.ontu.cryptobanking.service;

import ua.edu.ontu.cryptobanking.model.users.*;
import ua.edu.ontu.cryptobanking.model.currencies.*;
import ua.edu.ontu.cryptobanking.model.operations.*;
import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

import java.util.*;

public class BankingService {
    private final List<User> users;
    private final List<ua.edu.ontu.cryptobanking.model.currencies.Currency> currencies;
    private final List<FinancialOperation> operations;

    public BankingService() {
        this.users = new ArrayList<>();
        this.currencies = new ArrayList<>();
        this.operations = new ArrayList<>();
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        currencies.add(new FiatCurrency("USD", "US Dollar", "USD", "$", 1.0, "USA", "Federal Reserve"));
        currencies.add(new FiatCurrency("UAH", "Ukrainian Hryvnia", "UAH", "₴", 0.026, "Ukraine", "National Bank of Ukraine"));
        currencies.add(new CryptoCurrency("BTC", "Bitcoin", "BTC", "₿", 45000.0, "Bitcoin", 0.0005));
        currencies.add(new StableCoin("USDT", "Tether", "USDT", "₮", 1.0, "Ethereum", 0.001, "USD", 0.95));

        users.add(new PersonalUser("U1", "Артем", "Ракітенко", "oyasumi2819@email.com"));
        users.add(new BusinessUser("U2", "ТОВ 'ООП'", "12345678", "tech@company.ua"));
        users.add(new AdminUser("A1", "Адмін Системи", "admin@bank.ua", "Головний адміністратор"));

        users.get(0).deposit(10000);
        users.get(1).deposit(50000);
        users.get(2).deposit(1000);
    }

    public User createPersonalUser(String firstName, String lastName, String email) {
        String userId = "U" + (users.size() + 1);
        User user = new PersonalUser(userId, firstName, lastName, email);
        users.add(user);
        return user;
    }

    public ua.edu.ontu.cryptobanking.model.currencies.Currency findCurrencyByCode(String code) {
        for (ua.edu.ontu.cryptobanking.model.currencies.Currency currency : currencies) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }

    void processDailyFees() {
        System.out.println("Обробка щоденних комісій...");
        for (User user : users) {
            if (user.getBalance() > 0) {
                double fee = user.getBalance() * 0.0001;
                if (user.withdraw(fee)) {
                    System.out.println("Стягнуто комісію " + fee + " з " + user.getName());
                }
            }
        }
    }

    protected void generateSystemReport() {
        System.out.println("=== ЗВІТ СИСТЕМИ ===");
        System.out.println("Користувачі: " + users.size());
        System.out.println("Валюти: " + currencies.size());
        System.out.println("Операції: " + operations.size());

        double totalBalance = users.stream()
                .mapToDouble(User::getBalance)
                .sum();
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void performTransfer(User sender, User receiver, double amount, String currencyCode) {
        System.out.println("\n--- Виконання переказу ---");
        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            String opId = "T" + (operations.size() + 1);
            TransferOperation transfer = new TransferOperation(opId, amount, currencyCode,
                    sender.getId(), receiver.getId());
            if (transfer.execute()) {
                operations.add(transfer);
                System.out.println("Переказ успішно виконано та додано до операцій");
            }
        } else {
            System.out.println("Переказ не вдалося виконати");
        }
    }

    public void performDeposit(User user, double amount, String currencyCode, String method) {
        System.out.println("\n--- Виконання поповнення ---");
        String opId = "D" + (operations.size() + 1);
        DepositOperation deposit = new DepositOperation(opId, amount, currencyCode, user.getId(), method);
        if (deposit.execute()) {
            operations.add(deposit);
            user.deposit(amount);
            System.out.println("Поповнення успішно виконано та додано до операцій");
        } else {
            System.out.println("Поповнення не вдалося виконати");
        }
    }

    public void performExchange(User user, double amount, String fromCurrency, String toCurrency, double rate) {
        System.out.println("\n--- Виконання обміну ---");
        String opId = "E" + (operations.size() + 1);
        ExchangeOperation exchange = new ExchangeOperation(opId, amount, fromCurrency, toCurrency, rate, user.getId());
        if (exchange.execute()) {
            operations.add(exchange);
            System.out.println("Обмін успішно виконано та додано до операцій");
        } else {
            System.out.println("Обмін не вдалося виконати");
        }
    }

    public void performWithdrawal(User user, double amount, String currencyCode, String method, String destination) {
        System.out.println("\n--- Виконання виведення ---");
        String opId = "W" + (operations.size() + 1);
        WithdrawalOperation withdrawal = new WithdrawalOperation(opId, amount, currencyCode, user.getId(), method, destination);
        if (withdrawal.execute() && user.withdraw(amount)) {
            operations.add(withdrawal);
            System.out.println("Виведення успішно виконано та додано до операцій");
        } else {
            System.out.println("Виведення не вдалося виконати");
        }
    }

    public void demonstratePackagePrivate() {
        System.out.println("\n=== ДЕМОНСТРАЦІЯ PACKAGE-PRIVATE ===");

        System.out.println("1. Виклик processDailyFees() з того ж пакету:");
        processDailyFees();

        System.out.println("2. Виклик ServiceUtils з того ж пакету:");
        ServiceUtils.performInternalServiceCheck();

        System.out.println("3. Демонстрація через наслідування:");
        ReportService reportService = new ReportService();
        reportService.calculateStatistics();

        System.out.println("4. InternalValidator доступний тільки в пакеті util");
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<ua.edu.ontu.cryptobanking.model.currencies.Currency> getCurrencies() {
        return new ArrayList<>(currencies);
    }

    public List<FinancialOperation> getOperations() {
        return new ArrayList<>(operations);
    }
}
