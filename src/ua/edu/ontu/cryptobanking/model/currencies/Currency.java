package ua.edu.ontu.cryptobanking.model.currencies;

import ua.edu.ontu.cryptobanking.model.base.BankEntity;
import java.util.Locale;

public abstract class Currency extends BankEntity {
    protected String code;
    protected String symbol;
    protected double exchangeRate;

    public Currency(String id, String name, String code, String symbol, double exchangeRate) {
        super(id, name);
        this.code = code;
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
    }

    public abstract double convertTo(double amount, Currency target);
    public abstract boolean validateAmount(double amount);
    public abstract String getCurrencyType();
    public abstract String getSecurityInfo();

    public String getCode() { return code; }
    public String getSymbol() { return symbol; }
    public double getExchangeRate() { return exchangeRate; }

    public void setExchangeRate(double rate) {
        this.exchangeRate = rate;
    }

    public String formatAmount(double amount) {
        return String.format(Locale.US, "%s %.2f", symbol, amount);
    }
}