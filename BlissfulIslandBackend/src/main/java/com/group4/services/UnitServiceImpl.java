package com.group4.services;

import com.group4.daos.UnitDao;
import com.group4.daos.UnitDaoPostgres;
import com.group4.entities.Unit;

import java.util.List;

public class UnitServiceImpl implements UnitService {
    private UnitDao unitDao;

    public UnitServiceImpl(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    @Override
    public Unit getUnitById(int id) {
        return this.unitDao.getUnitById(id);
    }

    @Override
    public List<Unit> getAllUnits() {
        return this.unitDao.getAllUnits();
    }
}
