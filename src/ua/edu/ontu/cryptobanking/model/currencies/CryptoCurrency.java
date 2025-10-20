package ua.edu.ontu.cryptobanking.model.currencies;

import java.util.Locale;

public class CryptoCurrency extends Currency {
    private String blockchain;
    private double miningFee;
    private boolean isMineable;

    public CryptoCurrency(String id, String name, String code, String symbol,
                          double exchangeRate, String blockchain, double miningFee) {
        super(id, name, code, symbol, exchangeRate);
        this.blockchain = blockchain;
        this.miningFee = miningFee;
        this.isMineable = true;
    }

    @Override
    public String getDescription() {
        return "Криптовалюта: " + getName() + " (" + getCode() + ") - " + blockchain;
    }

    @Override
    public String getSecurityInfo() {
        return "Захищено блокчейном: " + blockchain + ", комісія: " + (miningFee * 100) + "%";
    }

    @Override
    public double convertTo(double amount, Currency target) {
        double amountAfterFee = amount * (1 - miningFee);
        double baseAmount = amountAfterFee * this.getExchangeRate();
        return baseAmount / target.getExchangeRate();
    }

    @Override
    public boolean validateAmount(double amount) {
        return amount >= 0.00000001 && amount <= 1000;
    }

    @Override
    public String getCurrencyType() {
        return "КРИПТОВАЛЮТА";
    }

    @Override
    public String formatAmount(double amount) {
        return String.format(Locale.US, "%.8f %s", amount, getCode());
    }

    public String getBlockchainInfo() {
        return "Блокчейн: " + blockchain + ", Комісія: " + (miningFee * 100) + "%";
    }

    public double calculateNetworkFee(double amount) {
        return amount * miningFee;
    }
}