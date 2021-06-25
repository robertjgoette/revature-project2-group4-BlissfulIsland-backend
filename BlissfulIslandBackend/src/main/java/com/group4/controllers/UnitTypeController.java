package com.group4.controllers;

import com.group4.services.UnitTypeService;

import java.util.logging.Handler;

public class UnitTypeController {

    private UnitTypeService unitTypeService;

    public UnitTypeController(UnitTypeService unitTypeService){
        this.unitTypeService = unitTypeService;
    }


}
