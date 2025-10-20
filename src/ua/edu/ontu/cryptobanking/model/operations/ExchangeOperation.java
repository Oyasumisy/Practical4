package ua.edu.ontu.cryptobanking.model.operations;

import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

public class ExchangeOperation extends FinancialOperation {
    private String sourceCurrency;
    private String targetCurrency;
    private double exchangeRate;
    private double receivedAmount;

    public ExchangeOperation(String id, double amount, String sourceCurrency,
                             String targetCurrency, double exchangeRate, String userId) {
        super(id, "Exchange Operation", amount, sourceCurrency, userId);
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.receivedAmount = amount * exchangeRate;
    }

    @Override
    public String getDescription() {
        return "Обмін валюти: " + amount + " " + sourceCurrency + " → " + receivedAmount + " " + targetCurrency;
    }

    @Override
    public boolean execute() {
        if (validate()) {
            System.out.println("Виконується обмін: " + getOperationDetails());
            setStatus("COMPLETED");
            return true;
        }
        setStatus("FAILED");
        return false;
    }

    @Override
    public boolean validate() {
        return amount > 0 && exchangeRate > 0 && !sourceCurrency.equals(targetCurrency);
    }

    @Override
    public String getOperationDetails() {
        return String.format("Обмін %s %s на %s %s за курсом %.4f",
                amount, sourceCurrency, receivedAmount, targetCurrency, exchangeRate);
    }

    @Override
    public String getOperationType() {
        return "EXCHANGE";
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public String getExchangePair() {
        return sourceCurrency + "/" + targetCurrency;
    }

    public double calculateFee() {
        return amount * 0.01;
    }
}