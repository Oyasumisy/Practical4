package ua.edu.ontu.cryptobanking.util;

class InternalValidator {

    static boolean validateTransactionAmount(double amount) {
        System.out.println("InternalValidator: перевірка суми " + amount);
        return amount > 0 && amount <= 1000000;
    }

    static boolean validateUserAge(int age) {
        return age >= 18;
    }

    static void logSecurityCheck(String operation) {
        System.out.println("SECURITY: " + operation + " - перевірка пройдена");
    }
}