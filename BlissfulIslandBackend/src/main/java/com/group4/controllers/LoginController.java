package com.group4.controllers;

import com.google.gson.Gson;
import com.group4.entities.Account;
import com.group4.services.LoginService;
import io.javalin.http.Handler;

public class LoginController {

    private Gson gson = new Gson();
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public Handler login = (ctx) -> {
        LoginCredentials loginCredentials = this.gson.fromJson(ctx.body(), LoginCredentials.class);
        Account account = loginService.login(loginCredentials.getEmail(), loginCredentials.getPassword());
        String loginJSON = gson.toJson(account);

        if(account.getAccountID() == -999){
            ctx.result("{\"error\":\"" + account.getFirstName() + "\"}");
            ctx.status(404);
        }
        else {
            ctx.result(loginJSON);
            ctx.status(200);
        }
    };
}

class LoginCredentials {
    private String email;
    private String password;

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}