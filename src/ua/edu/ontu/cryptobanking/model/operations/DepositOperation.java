package ua.edu.ontu.cryptobanking.model.operations;

import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

public class DepositOperation extends FinancialOperation {
    private String depositMethod;
    private String transactionId;

    public DepositOperation(String id, double amount, String currencyCode,
                            String userId, String depositMethod) {
        super(id, "Deposit Operation", amount, currencyCode, userId);
        this.depositMethod = depositMethod;
        this.transactionId = "TX_" + System.currentTimeMillis();
    }

    @Override
    public String getDescription() {
        return "Поповнення рахунку: " + amount + " " + currencyCode + " через " + depositMethod;
    }

    @Override
    public boolean execute() {
        if (validate()) {
            System.out.println("Виконується поповнення: " + getOperationDetails());
            setStatus("COMPLETED");
            return true;
        }
        setStatus("FAILED");
        return false;
    }

    @Override
    public boolean validate() {
        return amount > 0 && depositMethod != null;
    }

    @Override
    public String getOperationDetails() {
        return String.format("Поповнення %s %s методом %s (ID: %s)",
                amount, currencyCode, depositMethod, transactionId);
    }

    @Override
    public String getOperationType() {
        return "DEPOSIT";
    }

    public String getDepositMethod() {
        return depositMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public boolean isInstant() {
        return "BANK_CARD".equals(depositMethod) || "CRYPTO".equals(depositMethod);
    }
}