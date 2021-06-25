package com.group4.daos;

import com.group4.entities.Unit;

import java.util.List;

public interface UnitDao {
    //Read
    Unit getUnitById(int id);
    List<Unit> getAllUnits();
}
