package ua.edu.ontu.cryptobanking.model.base;

import java.util.Date;

public abstract class FinancialOperation extends BankEntity {
    protected double amount;
    protected String currencyCode;
    protected Date operationDate;
    protected String status;
    protected String userId;

    public FinancialOperation(String id, String name, double amount, String currencyCode, String userId) {
        super(id, name);
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.userId = userId;
        this.operationDate = new Date();
        this.status = "PENDING";
    }

    public abstract boolean execute();
    public abstract boolean validate();
    public abstract String getOperationDetails();
    public abstract String getOperationType();

    public double getAmount() { return amount; }
    public String getCurrencyCode() { return currencyCode; }
    public Date getOperationDate() { return operationDate; }
    public String getStatus() { return status; }
    public String getUserId() { return userId; }

    protected void setStatus(String status) {
        this.status = status;
    }
}