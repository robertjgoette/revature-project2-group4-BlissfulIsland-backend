package com.group4.services;

import com.group4.daos.AccountDAO;
import com.group4.daos.AccountDAOPostgres;
import com.group4.entities.Account;
import com.group4.exceptions.InvalidInputException;
import com.group4.exceptions.ResourceNotFound;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(Account account) {
        try {
            return accountDAO.createAccount(account);
        }
        catch (InvalidInputException e){
            String message = e.getMessage();
            return new Account(-999, message, message, message, message, -999, -999);
        }
    }

    @Override
    public Account getAccountById(int id) {
        try {
            return accountDAO.getAccountById(id);
        }
        catch(ResourceNotFound resourceNotFound){
            String message = resourceNotFound.getMessage();
            return new Account(-999, message, message, message, message,-999,-999);
        }
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
