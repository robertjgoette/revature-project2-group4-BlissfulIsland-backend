package com.group4.services;

import com.group4.daos.UnitTypeDAO;
import com.group4.entities.Type;
import com.group4.exceptions.ResourceNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitTypeServiceImpl implements UnitTypeService{

    private UnitTypeDAO unitTypeDAO = null;

    public UnitTypeServiceImpl(UnitTypeDAO unitTypeDAO){
        this.unitTypeDAO = unitTypeDAO;
    }
    @Override
    public List<Type> getAllUnitTypes() {
        return this.unitTypeDAO.getAllUnitTypes();
    }

    @Override
    public Type getUnitTypeById(int type_id) throws ResourceNotFound {
        return this.unitTypeDAO.getUnitTypeById(type_id);
    }

    @Override
    public ArrayList<HashMap<String, Object>> getAvailableUnitTypes() {

        return this.unitTypeDAO.getAvailableUnitTypes();
    }
}
