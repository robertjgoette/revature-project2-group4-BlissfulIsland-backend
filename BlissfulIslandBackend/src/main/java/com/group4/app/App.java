package com.group4.app;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        // DAO/service/controller variables here

        app.get("/accounts/manager", null);
        app.get("/accounts/tenant", null);
        app.get("/accounts/:id", null);
        app.get("/accounts", null);
        app.post("/accounts", null);
        app.patch("/accounts/:id", null);
        app.delete("/account/:id", null);

        app.post("/login", null);

        app.get("/messages", null);
        app.get("/messages/:id", null);
        app.post("/messages", null);

        app.get("/units", null);
        app.get("/units/:id", null);

        app.get("/types", null);
        app.get("/types/:id", null);
        app.get("/types/availability", null);

        app.start();

    }
}
