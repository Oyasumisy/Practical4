package ua.edu.ontu.cryptobanking.model.users;

public class BusinessUser extends User {
    private String companyName;
    private String taxNumber;
    private String companyType;

    public BusinessUser(String id, String companyName, String taxNumber, String email) {
        super(id, companyName, email);
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.companyType = "ТОВ";
    }

    @Override
    public String getDescription() {
        return "Юридична особа: " + companyName + " (ЄДРПОУ: " + taxNumber + ", Баланс: " + balance + ")";
    }

    @Override
    public String getAccountType() {
        return "ЮРИДИЧНА_ОСОБА";
    }

    @Override
    public void showSpecialFeatures() {
        System.out.println("Особливості для бізнесу: корпоративні звіти, оподаткування");
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("Кошти зараховані на корпоративний рахунок. Податковий номер: " + taxNumber);
    }

    public String getTaxInfo() {
        return "ЄДРПОУ: " + taxNumber + ", Тип: " + companyType;
    }

    public void setCompanyType(String type) {
        this.companyType = type;
    }
}