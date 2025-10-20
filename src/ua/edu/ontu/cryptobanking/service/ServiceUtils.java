package ua.edu.ontu.cryptobanking.service;

class ServiceUtils {
    static void performInternalServiceCheck() {
        System.out.println("ServiceUtils: виконується внутрішня перевірка сервісу (package-private)");
    }

    static String generateServiceToken() {
        String token = "SERVICE_TOKEN_" + System.currentTimeMillis();
        System.out.println("ServiceUtils: згенеровано токен: " + token);
        return token;
    }
}