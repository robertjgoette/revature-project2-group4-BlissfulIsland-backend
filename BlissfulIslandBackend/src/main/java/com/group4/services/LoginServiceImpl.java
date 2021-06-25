package com.group4.services;

import com.group4.daos.LoginDAO;
import com.group4.daos.LoginDAOPostgres;
import com.group4.entities.Account;

public class LoginServiceImpl implements LoginService {

    LoginDAO loginDAO = new LoginDAOPostgres();

    @Override
    public Account login(String email, String attemptedPassword) {
        return loginDAO.login(email, attemptedPassword);
    }
}
