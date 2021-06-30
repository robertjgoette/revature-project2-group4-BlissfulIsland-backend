package com.group4.daoTests;
import com.group4.daos.LoginDAO;
import com.group4.daos.LoginDAOPostgres;
import com.group4.entities.Account;
import com.group4.exceptions.ResourceNotFound;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDaoTests {

    LoginDAO loginDAO = new LoginDAOPostgres();

    @Test
    void login(){
        String email = "tenant@gmail.com";
        String password = "password";
        Account account = loginDAO.login(email, password);
        Assert.assertEquals(account.getEmail(), email);
    }

    @Test
    void loginNegative(){
        try {
            loginDAO.login("aaaaa", "aaaaaa");
        }
        catch (ResourceNotFound e){
            Assert.assertTrue(true);
        }
    }
}
