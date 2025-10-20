package ua.edu.ontu.cryptobanking.model.operations;

import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

public class WithdrawalOperation extends FinancialOperation {
    private String withdrawalMethod;
    private String destinationAddress;
    private double fee;

    public WithdrawalOperation(String id, double amount, String currencyCode,
                               String userId, String withdrawalMethod, String destinationAddress) {
        super(id, "Withdrawal Operation", amount, currencyCode, userId);
        this.withdrawalMethod = withdrawalMethod;
        this.destinationAddress = destinationAddress;
        this.fee = calculateFee(amount);
    }

    @Override
    public String getDescription() {
        return "Виведення коштів: " + amount + " " + currencyCode + " через " + withdrawalMethod;
    }

    @Override
    public boolean execute() {
        if (validate()) {
            System.out.println("Виконується виведення: " + getOperationDetails());
            setStatus("COMPLETED");
            return true;
        }
        setStatus("FAILED");
        return false;
    }

    @Override
    public boolean validate() {
        boolean validAmount = amount > fee;
        boolean validDestination = destinationAddress != null && !destinationAddress.isEmpty();
        return validAmount && validDestination;
    }

    @Override
    public String getOperationDetails() {
        return String.format("Виведення %s %s на %s (комісія: %s)",
                amount, currencyCode, destinationAddress, fee);
    }

    @Override
    public String getOperationType() {
        return "WITHDRAWAL";
    }

    @Override
    public double getAmount() {
        return amount - fee;
    }

    private double calculateFee(double amount) {
        return Math.max(amount * 0.02, 1.0);
    }

    public double getFee() {
        return fee;
    }

    public String getDestinationInfo() {
        return "Адреса: " + destinationAddress + ", Метод: " + withdrawalMethod;
    }
}