package ua.edu.ontu.cryptobanking.model.currencies;

import java.util.Locale;

public class FiatCurrency extends Currency {
    private String country;
    private String centralBank;
    private boolean isDigital;

    public FiatCurrency(String id, String name, String code, String symbol,
                        double exchangeRate, String country, String centralBank) {
        super(id, name, code, symbol, exchangeRate);
        this.country = country;
        this.centralBank = centralBank;
        this.isDigital = false;
    }

    @Override
    public String getDescription() {
        return "Фіатна валюта: " + getName() + " (" + getCode() + ") - " + country;
    }

    @Override
    public double convertTo(double amount, Currency target) {
        double baseAmount = amount * this.getExchangeRate();
        return baseAmount / target.getExchangeRate();
    }

    @Override
    public boolean validateAmount(double amount) {
        return amount >= 0.01 && amount <= 1000000;
    }

    @Override
    public String getCurrencyType() {
        return "ФІАТНА";
    }

    @Override
    public String getSecurityInfo() {
        return "Захищено центральним банком: " + centralBank;
    }

    @Override
    public String formatAmount(double amount) {
        return String.format(Locale.US, "%s %.2f %s", getSymbol(), amount, getCode());
    }

    public String getCountryInfo() {
        return "Країна: " + country + ", Центробанк: " + centralBank;
    }

    public void setDigital(boolean digital) {
        this.isDigital = digital;
    }
}