package com.group4.entities;

public class Account {
    private int accountID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int unitID;
    // 0 is tenant, 1 is manager, 2 is admin
    private int accountType;

    public Account(int accountID, String email, String password, String firstName, String lastName, int unitID, int accountType) {
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public Account() {
    }

    public int getAccountID() {
        return accountID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", unitID=" + unitID +
                ", accountType=" + accountType +
                '}';
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
