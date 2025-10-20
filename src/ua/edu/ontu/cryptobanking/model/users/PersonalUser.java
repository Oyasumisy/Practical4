package ua.edu.ontu.cryptobanking.model.users;

public class PersonalUser extends User {
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String address;

    public PersonalUser(String id, String firstName, String lastName, String email) {
        super(id, firstName + " " + lastName, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = "Не вказано";
        this.address = "Не вказано";
    }

    @Override
    public String getDescription() {
        return "Фізична особа: " + firstName + " " + lastName +
                " (Баланс: " + getBalance() + ", Email: " + getEmail() + ")";
    }

    @Override
    public String getAccountType() {
        return "ФІЗИЧНА_ОСОБА";
    }

    @Override
    public void showSpecialFeatures() {
        System.out.println("⚡ Особливості для фізичних осіб:");
        System.out.println("   • Ліміт зняття: 50,000 в день");
        System.out.println("   • Безкоштовні перекази між своїми рахунками");
        System.out.println("   • Мобільний банкінг");
        System.out.println("   • Кешбек 1% від покупок");
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 50000) {
            System.out.println("Перевищено денний ліміт для фізичної особи! Максимум 50,000");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Сума зняття має бути додатньою");
            return false;
        }

        boolean result = super.withdraw(amount);
        if (result) {
            System.out.println(getFullName() + " успішно зняв " + amount);

            if (amount > 10000) {
                System.out.println("Велика сума зняття! Рекомендуємо використовувати безготівковий розрахунок");
            }
        }
        return result;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Сума поповнення має бути додатньою");
            return;
        }

        super.deposit(amount);
        System.out.println("" + getFullName() + " успішно поповнив рахунок на " + amount);

        if (amount > 5000) {
            System.out.println("Велике поповнення! Дякуємо за довіру!");
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setPassport(String passportNumber) {
        if (passportNumber != null && passportNumber.matches("[A-Z]{2}\\d{6}")) {
            this.passportNumber = passportNumber;
            System.out.println("Паспортні дані оновлено: " + passportNumber);
        } else {
            System.out.println("Неправильний формат паспорту. Приклад: AB123456");
        }
    }

    public void setAddress(String address) {
        this.address = address;
        System.out.println("Адресу оновлено: " + address);
    }

    public String getPersonalInfo() {
        return "Ім'я: " + firstName +
                "\nПрізвище: " + lastName +
                "\nПаспорт: " + passportNumber +
                "\nАдреса: " + address +
                "\nEmail: " + getEmail() +
                "\nТелефон: " + (getPhone() != null ? getPhone() : "Не вказано");
    }

    public void requestCard() {
        System.out.println(getFullName() + " запросив банківську картку");
        System.out.println("   Картка буде доставлена за адресою: " + address);
    }

    @Override
    public String toString() {
        return "PersonalUser{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", balance=" + getBalance() +
                ", email='" + getEmail() + '\'' +
                ", passport='" + passportNumber + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getAddress() {
        return address;
    }
}