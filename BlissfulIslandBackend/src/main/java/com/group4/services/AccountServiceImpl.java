package com.group4.services;

import com.group4.daos.AccountDAO;
import com.group4.daos.AccountDAOPostgres;
import com.group4.entities.Account;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(Account account) {
        return accountDAO.createAccount(account);
    }

    @Override
    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    @Override
    public List<Account> getAllManagerAccounts() {
        return accountDAO.getAllManagerAccounts();
    }

    @Override
    public List<Account> getAllTenantAccounts() {
        return accountDAO.getAllTenantAccounts();
    }

    @Override
    public Account updateAccount(Account account) {
        return accountDAO.updateAccount(account);
    }

    @Override
    public boolean deleteAccountById(int id) {
        return accountDAO.deleteAccountById(id);
    }
}
