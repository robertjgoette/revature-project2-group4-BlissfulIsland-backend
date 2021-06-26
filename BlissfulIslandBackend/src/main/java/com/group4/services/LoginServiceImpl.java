package com.group4.services;

import com.group4.daos.LoginDAO;
import com.group4.entities.Account;
import com.group4.exceptions.ResourceNotFound;

public class LoginServiceImpl implements LoginService {

    private LoginDAO loginDAO;

    public LoginServiceImpl(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public Account login(String email, String attemptedPassword) {
        try {
            return loginDAO.login(email, attemptedPassword);
        }catch (ResourceNotFound resourceNotFound){
            String message = resourceNotFound.getMessage();
            Account account = new Account(-999,message,message,message,message,-999,-999);
            return account;
        }
    }
}
