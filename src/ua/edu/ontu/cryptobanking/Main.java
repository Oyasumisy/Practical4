package ua.edu.ontu.cryptobanking;

import ua.edu.ontu.cryptobanking.model.users.*;
import ua.edu.ontu.cryptobanking.model.currencies.*;
import ua.edu.ontu.cryptobanking.model.operations.*;
import ua.edu.ontu.cryptobanking.service.*;
import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ПРАКТИЧНА РОБОТА №4: НАСЛІДУВАННЯ ===\n");

        BankingService bankingService = new BankingService();
        ReportService reportService = new ReportService();

        System.out.println("=== ДЕМОНСТРАЦІЯ ПОЛІМОРФІЗМУ ===");

        List<User> users = bankingService.getUsers();
        System.out.println("\n1. ПОЛІМОРФІЗМ КОРИСТУВАЧІВ:");
        for (User user : users) {
            System.out.println(user.getDescription());
            System.out.println("Тип: " + user.getAccountType());
            user.showSpecialFeatures();

            user.deposit(1000);
            user.withdraw(100);
            System.out.println("---");
        }

        List<ua.edu.ontu.cryptobanking.model.currencies.Currency> currencies = bankingService.getCurrencies();
        System.out.println("\n2. ПОЛІМОРФІЗМ ВАЛЮТ:");
        for (ua.edu.ontu.cryptobanking.model.currencies.Currency currency : currencies) {
            System.out.println(currency.getDescription());
            System.out.println("Тип: " + currency.getCurrencyType());

            if (!currencies.isEmpty()) {
                double converted = currency.convertTo(100, currencies.get(0));
                System.out.println("100 " + currency.getCode() + " = " + converted + " USD");
            }
            System.out.println("Валідація суми 50: " + currency.validateAmount(50));
            System.out.println("---");
        }

        System.out.println("\n3. ДЕМОНСТРАЦІЯ ОПЕРАЦІЙ ЧЕРЕЗ СЕРВІС:");

        User user1 = users.get(0);
        User user2 = users.get(1);

        bankingService.performTransfer(user1, user2, 500, "UAH");
        bankingService.performDeposit(user1, 2000, "UAH", "BANK_CARD");
        bankingService.performExchange(user1, 1000, "UAH", "USD", 0.026);
        bankingService.performWithdrawal(user2, 300, "UAH", "BANK_ACCOUNT", "UA123456789");

        System.out.println("\n4. ДЕМОНСТРАЦІЯ ПЕРЕВИЗНАЧЕНИХ МЕТОДІВ:");

        if (!users.isEmpty() && users.get(0) instanceof PersonalUser personalUser) {
            System.out.println("Спроба зняття 60000 у фізичної особи:");
            personalUser.withdraw(60000);
        }

        if (users.size() > 1 && users.get(1) instanceof BusinessUser businessUser) {
            System.out.println("\nСпроба зняття 60000 у бізнесу:");
            businessUser.withdraw(60000);

            System.out.println("\nПоповнення у бізнесу:");
            businessUser.deposit(5000);
        }

        if (currencies.size() > 1 && currencies.get(1) instanceof FiatCurrency uah) {
            System.out.println("\nФорматування сум:");
            System.out.println("Фіат: " + uah.formatAmount(1234.56));
        }

        if (currencies.size() > 2 && currencies.get(2) instanceof CryptoCurrency btc) {
            System.out.println("Крипта: " + btc.formatAmount(0.12345678));
        }

        System.out.println("\n5. ДЕМОНСТРАЦІЯ НАСЛІДУВАННЯ:");
        reportService.generateFullReport();

        System.out.println("\n6. ДЕМОНСТРАЦІЯ PACKAGE-PRIVATE ДОСТУПУ:");
        bankingService.demonstratePackagePrivate();

        System.out.println("\n7. ДЕМОНСТРАЦІЯ АБСТРАКТНИХ МЕТОДІВ:");
        for (Currency currency : currencies) {
            System.out.println(currency.getDescription());
            System.out.println("Інформація про безпеку: " + currency.getSecurityInfo());
            System.out.println("---");
        }

        System.out.println("\n8. РОЗШИРЕНИЙ ЗВІТ:");
        reportService.generateEnhancedReport();

        System.out.println("\n9. ПЕРЕВІРКА СТАНУ ОПЕРАЦІЙ:");
        List<FinancialOperation> completedOperations = bankingService.getOperations();
        if (completedOperations.isEmpty()) {
            System.out.println("УВАГА: Операції не додаються до списку! Потрібно виправити логіку.");
        } else {
            System.out.println("Кількість виконаних операцій: " + completedOperations.size());
            for (FinancialOperation op : completedOperations) {
                System.out.println(op.getOperationDetails() + " - Статус: " + op.getStatus());
            }
        }

        System.out.println("\n10. ФІНАЛЬНІ БАЛАНСИ:");
        for (User user : bankingService.getUsers()) {
            System.out.println(user.getName() + ": " + user.getBalance());
        }

        System.out.println("\n=== ПРОГРАМУ УСПІШНО ЗАВЕРШЕНО ===");
    }
}