package ua.edu.ontu.cryptobanking.util;

public class SecurityUtils {

    public static boolean validateUser(String userId) {
        System.out.println("Перевірка користувача: " + userId);
        boolean internalCheck = performInternalValidation(userId);
        InternalValidator.logSecurityCheck("USER_VALIDATION");
        return internalCheck;
    }

    static boolean performInternalValidation(String userId) {
        return userId != null && userId.startsWith("U");
    }

    static String generateInternalToken() {
        return "TOKEN_" + System.currentTimeMillis();
    }
}