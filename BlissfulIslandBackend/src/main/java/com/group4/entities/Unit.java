package com.group4.entities;

public class Unit {
    private int unitID;
    private int typeID;
    private boolean isAvailable;
    private int apartmentNumber;
    private int unitBalance;

    public Unit(int unitID, int typeID, boolean isAvailable, int apartmentNumber, int unitBalance) {
        this.unitID = unitID;
        this.typeID = typeID;
        this.isAvailable = isAvailable;
        this.apartmentNumber = apartmentNumber;
        this.unitBalance = unitBalance;
    }

    public Unit() {
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitID=" + unitID +
                ", typeID=" + typeID +
                ", isAvailable=" + isAvailable +
                ", apartmentNumber=" + apartmentNumber +
                ", unitBalance=" + unitBalance +
                '}';
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getUnitBalance() {
        return unitBalance;
    }

    public void setUnitBalance(int unitBalance) {
        this.unitBalance = unitBalance;
    }
}
