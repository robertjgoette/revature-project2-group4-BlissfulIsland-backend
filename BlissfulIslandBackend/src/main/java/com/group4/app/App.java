package com.group4.app;

import com.group4.controllers.*;

import com.group4.daos.*;
import com.group4.services.*;

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

        MessageDao messageDao = new MessageDaoPostgres();
        MessageService messageService = new MessageServiceImpl(messageDao, accountDAO);
        MessageController messageController = new MessageController(messageService);

        UnitTypeDAO unitTypeDAO = new UnitTypeDaoPostgres();
        UnitTypeService unitTypeService = new UnitTypeServiceImpl(unitTypeDAO);
        UnitTypeController unitTypeController = new UnitTypeController(unitTypeService);
      
        LoginDAO loginDAO = new LoginDAOPostgres();
        LoginService loginService = new LoginServiceImpl(loginDAO);
        LoginController loginController = new LoginController(loginService);

        UnitDao unitDao = new UnitDaoPostgres();
        UnitService unitService = new UnitServiceImpl(unitDao);
        UnitController unitController = new UnitController(unitService);

        app.post("/accounts", accountController.createAccount);
        app.get("/accounts", accountController.getAllAccounts);
        app.get("/accounts/manager", accountController.getAllManagerAccounts);
        app.get("/accounts/tenant", accountController.getAllTenantAccounts);
        app.get("/accounts/:id", accountController.getAccountById);
        app.patch("/accounts/:id", accountController.updateAccount);
        app.delete("/accounts/:id", accountController.deleteAccount);

        app.post("/login", loginController.login);

        app.get("/messages", messageController.getAllMessages);
        app.get("/messages/:id", messageController.getMessageById);
        app.post("/messages", messageController.createMessage);

        app.get("/units", unitController.getAllUnits);
        app.get("/units/:id", unitController.getUnitById);

        app.get("/types",unitTypeController.getAllUnitTypes);
        app.get("/types/:id", unitTypeController.getUnitTypeById);
        app.get("/typesAvailability", unitTypeController.getAvailableUnitTypes);

        app.start();

    }
}
