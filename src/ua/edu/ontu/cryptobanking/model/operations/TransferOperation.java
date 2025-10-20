package ua.edu.ontu.cryptobanking.model.operations;

import ua.edu.ontu.cryptobanking.model.base.FinancialOperation;

public class TransferOperation extends FinancialOperation {
    private String senderId;
    private String receiverId;
    private String description;

    public TransferOperation(String id, double amount, String currencyCode,
                             String senderId, String receiverId) {
        super(id, "Transfer Operation", amount, currencyCode, senderId);
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.description = "Transfer between accounts";
    }

    @Override
    public String getDescription() {
        return "Переказ коштів: " + getAmount() + " " + getCurrencyCode() + " від " + senderId + " до " + receiverId;
    }

    @Override
    public boolean execute() {
        if (validate()) {
            System.out.println("Виконується переказ: " + getOperationDetails());
            setStatus("COMPLETED");
            return true;
        }
        setStatus("FAILED");
        return false;
    }

    @Override
    public boolean validate() {
        return getAmount() > 0 && senderId != null && receiverId != null && !senderId.equals(receiverId);
    }

    @Override
    public String getOperationDetails() {
        return String.format("Переказ %s %s з %s на %s", getAmount(), getCurrencyCode(), senderId, receiverId);
    }

    @Override
    public String getOperationType() {
        return "TRANSFER";
    }

    @Override
    public String toString() {
        return getOperationDetails() + " - Статус: " + getStatus();
    }

    public String getSenderInfo() {
        return "Відправник: " + senderId;
    }

    public String getReceiverInfo() {
        return "Отримувач: " + receiverId;
    }
}