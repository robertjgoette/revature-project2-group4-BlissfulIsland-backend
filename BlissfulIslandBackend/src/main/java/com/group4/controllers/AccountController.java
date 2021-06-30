package com.group4.controllers;

import com.google.gson.Gson;
import com.group4.entities.Account;
import com.group4.services.AccountService;
import io.javalin.http.Handler;

import java.util.List;

public class AccountController {
    private Gson gson = new Gson();
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    public Handler createAccount = (ctx) -> {
        Account account = this.gson.fromJson(ctx.body(), Account.class);
        account = this.accountService.createAccount(account);
        String accountJSON = gson.toJson(account);
        if(account.getAccountID() == -999){
            ctx.result("{\"error\":\"" + account.getFirstName() + "\"}");
            ctx.status(404);
        }
        else {
            ctx.result(accountJSON);
            ctx.status(201);
        }
    };

    public Handler getAccountById = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = this.accountService.getAccountById(id);
        String accountJSON = this.gson.toJson(account);

        if(account.getAccountID() == -999){
            ctx.result("{\"error\":\"" + account.getFirstName() + "\"}");
            ctx.status(404);
        }
        else {
            ctx.result(accountJSON);
            ctx.status(200);
        }
    };

    public Handler getAllAccounts = (ctx) -> {
        List<Account> accounts = this.accountService.getAllAccounts();
        String accountsJSON = this.gson.toJson(accounts);
        ctx.result(accountsJSON);
    };

    public Handler getAllManagerAccounts = (ctx) -> {
        List<Account> accounts = this.accountService.getAllManagerAccounts();
        String accountsJSON = this.gson.toJson(accounts);
        ctx.result(accountsJSON);
    };

    public Handler getAllTenantAccounts = (ctx) -> {
        List<Account> accounts = this.accountService.getAllTenantAccounts();
        String accountsJSON = this.gson.toJson(accounts);
        ctx.result(accountsJSON);
    };

    public Handler updateAccount = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = this.gson.fromJson(ctx.body(), Account.class);
        account.setAccountID(id);
        this.accountService.updateAccount(account);
        String accountJSON = this.gson.toJson(account);
        ctx.result(accountJSON);
    };

    public Handler deleteAccount = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = this.accountService.deleteAccountById(id);
        if(result){
            ctx.status(200);
        }else{
            ctx.status(404);
        }
    };
}
