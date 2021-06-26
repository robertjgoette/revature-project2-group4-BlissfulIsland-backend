package com.group4.controllers;

import com.google.gson.Gson;
import com.group4.entities.Unit;
import com.group4.exceptions.ResourceNotFound;
import com.group4.services.UnitService;
import io.javalin.http.Handler;

import java.util.List;

public class UnitController {

    private UnitService unitService;

    public UnitController(UnitService unitService){
        this.unitService = unitService;
    }

    public Handler getAllUnits = ctx ->{
        List<Unit> units;
        units = this.unitService.getAllUnits();
        Gson gson = new Gson();
        String unitJSON = gson.toJson(units);
        ctx.result(unitJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };

    public Handler getUnitById = ctx ->{
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Unit unit = this.unitService.getUnitById(id);
            Gson gson = new Gson();
            String unitJson = gson.toJson(unit);
            ctx.result(unitJson);
            ctx.status(200);
            ctx.contentType("application/json");
        }catch (ResourceNotFound resourceNotFound){
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }
    };


}
