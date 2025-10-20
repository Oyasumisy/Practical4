package ua.edu.ontu.cryptobanking.model.currencies;

public class StableCoin extends CryptoCurrency {
    private String backedBy;
    private double reserveRatio;

    public StableCoin(String id, String name, String code, String symbol,
                      double exchangeRate, String blockchain, double miningFee,
                      String backedBy, double reserveRatio) {
        super(id, name, code, symbol, exchangeRate, blockchain, miningFee);
        this.backedBy = backedBy;
        this.reserveRatio = reserveRatio;
    }

    @Override
    public String getDescription() {
        return "Стейблкоїн: " + getName() + " забезпечений " + backedBy + " (резерви: " + (reserveRatio * 100) + "%)";
    }

    @Override
    public String getSecurityInfo() {
        return "Забезпечення: " + backedBy + ", резерв: " + (reserveRatio * 100) + "%";
    }

    @Override
    public String getCurrencyType() {
        return "СТЕЙБЛКОЇН";
    }

    @Override
    public double convertTo(double amount, Currency target) {
        return amount * this.getExchangeRate() / target.getExchangeRate();
    }

    public String getBackingInfo() {
        return "Забезпечення: " + backedBy + ", Коефіцієнт резерву: " + (reserveRatio * 100) + "%";
    }

    public boolean isFullyBacked() {
        return reserveRatio >= 1.0;
    }
}