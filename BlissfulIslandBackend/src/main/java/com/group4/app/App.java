package com.group4.app;

import com.group4.controllers.AccountController;
import com.group4.controllers.UnitTypeController;
import com.group4.daos.AccountDAO;
import com.group4.daos.AccountDAOPostgres;
import com.group4.daos.UnitTypeDAO;
import com.group4.daos.UnitTypeDaoPostgres;
import com.group4.services.AccountService;
import com.group4.services.AccountServiceImpl;
import com.group4.services.UnitTypeService;
import com.group4.services.UnitTypeServiceImpl;
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

        UnitTypeDAO unitTypeDAO = new UnitTypeDaoPostgres();
        UnitTypeService unitTypeService = new UnitTypeServiceImpl(unitTypeDAO);
        UnitTypeController unitTypeController = new UnitTypeController(unitTypeService);

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
        app.get("/types",unitTypeController.getAllUnitTypes );
        app.get("/types/:id", unitTypeController.getUnitTypeById);
        app.get("/typesAvailability", unitTypeController.getAvailableUnitTypes);

        app.start();

    }
}
