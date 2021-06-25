package com.group4.services;

import com.group4.entities.Account;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    Account getAccountById(int id);
    List<Account> getAllAccounts();
    List<Account> getAllManagerAccounts();
    List<Account> getAllTenantAccounts();

    Account updateAccount(Account account);

    boolean deleteAccountById(int id);
}
