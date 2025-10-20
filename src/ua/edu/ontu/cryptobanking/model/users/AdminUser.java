package ua.edu.ontu.cryptobanking.model.users;

public class AdminUser extends User {
    private final String adminLevel;
    private final String department;

    public AdminUser(String id, String name, String email, String adminLevel) {
        super(id, name, email);
        this.adminLevel = adminLevel;
        this.department = "Адміністрування";
    }

    @Override
    public String getDescription() {
        return "Адміністратор: " + getName() + " (" + adminLevel + ") - " + department;
    }

    @Override
    public String getAccountType() {
        return "АДМІНІСТРАТОР";
    }

    @Override
    public void showSpecialFeatures() {
        System.out.println("Адміністративні функції: управління користувачами, перегляд логів");
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Адміністратор " + getName() + " виконує спеціальне поповнення");
        super.deposit(amount);
    }

    public void manageSystem() {
        System.out.println("Адміністратор " + getName() + " керує системою");
    }

    public String getAdminInfo() {
        return "Рівень: " + adminLevel + ", Відділ: " + department;
    }
}