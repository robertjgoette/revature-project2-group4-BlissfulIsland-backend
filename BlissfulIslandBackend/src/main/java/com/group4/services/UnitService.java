package com.group4.services;

import  com.group4.entities.Unit;

import java.util.List;

public interface UnitService {
    Unit getUnitById(int id);
    List<Unit> getAllUnits();
}
