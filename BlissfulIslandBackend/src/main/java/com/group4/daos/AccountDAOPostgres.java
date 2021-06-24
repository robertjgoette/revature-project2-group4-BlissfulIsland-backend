package com.group4.daos;

import com.group4.entities.Account;

import java.util.List;

public class AccountDAOPostgres implements AccountDAO{
    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public Account getAccountById(int id) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public List<Account> getAllManagerAccounts() {
        return null;
    }

    @Override
    public List<Account> getAllTenantAccounts() {
        return null;
    }

    @Override
    public Account updateAccountById(int id, Account account) {
        return null;
    }

    @Override
    public boolean deleteAccountById(int id) {
        return false;
    }
}
