package com.group4.app;

import com.group4.controllers.AccountController;
import com.group4.daos.AccountDAO;
import com.group4.daos.AccountDAOPostgres;
import com.group4.services.AccountService;
import com.group4.services.AccountServiceImpl;
  
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        AccountDAO accountDAO = new AccountDAOPostgres();
        AccountService accountService = new AccountServiceImpl(accountDAO);
        AccountController accountController = new AccountController(accountService);

        app.post("/accounts", accountController.createAccount);
        app.get("/accounts/:id", accountController.getAccountById);
        app.get("/accounts", accountController.getAllAccounts);
        app.get("/accounts/manager", accountController.getAllManagerAccounts);
        app.get("/accounts/tenant", accountController.getAllTenantAccounts);
        app.patch("/accounts/:id", accountController.updateAccount);
        app.delete("/accounts/:id", accountController.deleteAccount);

//        app.post("/login", null);
//
//        app.get("/messages", null);
//        app.get("/messages/:id", null);
//        app.post("/messages", null);
//
//        app.get("/units", null);
//        app.get("/units/:id", null);
//
//        app.get("/types", null);
//        app.get("/types/:id", null);
//        app.get("/types/availability", null);

        app.start();

    }
}
