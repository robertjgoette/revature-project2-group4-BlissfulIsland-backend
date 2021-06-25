package com.group4.daos;

import com.group4.entities.Account;
import java.util.List;

public interface AccountDAO {

    Account createAccount(Account account);

    Account getAccountById(int id);
    List<Account> getAllAccounts();
    List<Account> getAllManagerAccounts();
    List<Account> getAllTenantAccounts();

    Account updateAccount(Account account);

    boolean deleteAccountById(int id);
}
