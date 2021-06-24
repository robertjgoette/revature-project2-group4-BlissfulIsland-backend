package com.group4.daoTests;

import com.group4.daos.AccountDAO;
import com.group4.entities.Account;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AccountDaoTests {

    static AccountDAO accountDAO = null;
    static Account testTenant = new Account(0, "tenant@gmail.com", "password", "Test", "Tenant", 10, 0);
    static Account testManager = new Account(0, "manager@blissfulisland.com", "password", "Test", "Manager", 11, 1);
    static Account testAdmin = new Account(0, "admin@blissfulisland.com", "password", "Test", "Admin", 12, 2);

    @Test
    void createAccount(){
        Account testCreateTenant = accountDAO.createAccount(testTenant);
        Account testCreateManager = accountDAO.createAccount(testManager);
        Account testCreateAdmin = accountDAO.createAccount(testAdmin);

        Assert.assertEquals(testAdmin.getFirstName(), testCreateAdmin.getFirstName());
    }

    @Test
    void getAccountById(){
        Account testGetAccount = accountDAO.getAccountById(1);
        Assert.assertEquals(testGetAccount.getAccountID(), 1);
    }

    @Test
    void getAllAccounts(){
        List<Account> list = accountDAO.getAllAccounts();
        Assert.assertTrue(list.size() >= 3);
    }

    @Test
    void getAllManagerAccounts(){
        List<Account> list = accountDAO.getAllManagerAccounts();
        Assert.assertTrue(list.size() >= 1);
    }

    @Test
    void getAllTenantAccounts(){
        List<Account> list = accountDAO.getAllTenantAccounts();
        Assert.assertTrue(list.size() >= 1);
    }

    @Test
    void updateAccountById(){
        Account testAccount = new Account(0, "updated@gmail.com", "password", "Updated", "Manager", 15, 0);
        Account test = accountDAO.updateAccountById(1, testAccount);
        Assert.assertEquals(testAccount.getFirstName(), test.getFirstName());
    }

    @Test
    void deleteAccountById(){
        boolean deleteResult = accountDAO.deleteAccountById(1);
        Assert.assertTrue(deleteResult);
    }


}
