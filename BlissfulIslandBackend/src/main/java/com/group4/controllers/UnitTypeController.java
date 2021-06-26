package com.group4.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group4.entities.Type;

import com.group4.services.UnitTypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import io.javalin.http.Handler;

public class UnitTypeController {

    private UnitTypeService unitTypesService;
    private Gson gson = new Gson();
    public UnitTypeController(UnitTypeService unitTypeService){
        this.unitTypesService = unitTypeService;
    }
    public Handler getAllUnitTypes = (ctx)->{

        List<Type> unitTypes;
        unitTypes = this.unitTypesService.getAllUnitTypes();
        String unitTypeJSON = this.gson.toJson(unitTypes);
        ctx.result(unitTypeJSON);
        ctx.status(200);
        ctx.contentType("application/json");

    };
    public Handler getUnitTypeById = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Type unitType = this.unitTypesService.getUnitTypeById(id);
        String unitTypeJSON = this.gson.toJson(unitType);
        ctx.result(unitTypeJSON);
        ctx.status(200);
        ctx.contentType("application/json");

    };
    public Handler getAvailableUnitTypes = (ctx) -> {

        ArrayList<HashMap<String,Object>> unitTypeAvailable = this.unitTypesService.getAvailableUnitTypes();
        String unitTypeAvailableJSON = this.gson.toJson(unitTypeAvailable);
        ctx.result(unitTypeAvailableJSON);
        ctx.status(200);
        ctx.contentType("application/json");

    };
}
