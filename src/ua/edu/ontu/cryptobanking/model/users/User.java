package ua.edu.ontu.cryptobanking.model.users;

import ua.edu.ontu.cryptobanking.model.base.BankEntity;

public abstract class User extends BankEntity {
    protected String email;
    protected String phone;
    protected double balance;

    public User(String id, String name, String email) {
        super(id, name);
        this.email = email;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(getName() + " поповнив рахунок на: " + amount + " (новий баланс: " + balance + ")");
        } else {
            System.out.println("Помилка: сума поповнення має бути додатньою");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(getName() + " зняв: " + amount + " (новий баланс: " + balance + ")");
            return true;
        }
        System.out.println("Помилка: недостатньо коштів для зняття " + amount + " (баланс: " + balance + ")");
        return false;
    }

    public abstract String getAccountType();
    public abstract void showSpecialFeatures();

    public double getBalance() { return balance; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
}