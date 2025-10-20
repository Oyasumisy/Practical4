package ua.edu.ontu.cryptobanking.model.base;

import java.util.Date;

public abstract class BankEntity {
    protected String id;
    protected String name;
    protected Date createdDate;

    public BankEntity(String id, String name) {
        this.id = id;
        this.name = name;
        this.createdDate = new Date();
    }

    public abstract String getDescription();

    public String getId() { return id; }
    public String getName() { return name; }
    public Date getCreatedDate() { return createdDate; }

    @Override
    public String toString() {
        return getDescription();
    }
}