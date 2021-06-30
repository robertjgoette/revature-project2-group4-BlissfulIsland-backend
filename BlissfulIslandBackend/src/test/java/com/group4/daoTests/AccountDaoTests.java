package com.group4.daoTests;

import com.group4.daos.AccountDAO;
import com.group4.daos.AccountDAOPostgres;
import com.group4.entities.Account;
import com.group4.exceptions.ResourceNotFound;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AccountDaoTests {

    static AccountDAO accountDAO = new AccountDAOPostgres();
    static Account testTenant = new Account(0, "tenant@gmail.com", "password", "Test", "Tenant", 1, 0);
    static Account testManager = new Account(0, "manager@blissfulisland.com", "password", "Test", "Manager", 1, 1);
    static Account testAdmin = new Account(0, "admin@blissfulisland.com", "password", "Test", "Admin", 1, 2);
    static int knownWorkingId = -999;

    @Test
    void createAccount(){
        Account testCreateTenant = accountDAO.createAccount(testTenant);
        Account testCreateManager = accountDAO.createAccount(testManager);
        Account testCreateAdmin = accountDAO.createAccount(testAdmin);

        knownWorkingId = testCreateTenant.getAccountID();
        System.out.println(knownWorkingId);

        Assert.assertEquals(testAdmin.getFirstName(), testCreateAdmin.getFirstName());
    }

    @Test
    void getAccountById(){
        Account testGetAccount = accountDAO.getAccountById(knownWorkingId + 1);
        Assert.assertEquals(testGetAccount.getAccountID(), knownWorkingId + 1);
    }

    @Test
    void getAccountByIdNegativeTest(){
        try {
            accountDAO.getAccountById(-10);
        }catch (ResourceNotFound e){
            Assert.assertTrue(true);
        }
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
        Account testAccount = new Account(knownWorkingId + 1, "updated@gmail.com", "password", "Updated", "Manager", 1, 0);
        Account test = accountDAO.updateAccount(testAccount);
        Assert.assertEquals(testAccount.getFirstName(), test.getFirstName());
    }

    @Test(dependsOnMethods = {"createAccount"})
    void deleteAccountById(){
        boolean deleteResult = accountDAO.deleteAccountById(knownWorkingId);
        Assert.assertTrue(deleteResult);
    }

    @Test
    void deleteAccountByIdNegativeTest(){
        boolean testBoolean = accountDAO.deleteAccountById(-10);
        Assert.assertFalse(testBoolean);
    }


}
