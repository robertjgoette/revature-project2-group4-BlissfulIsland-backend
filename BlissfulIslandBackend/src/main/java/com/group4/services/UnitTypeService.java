package com.group4.services;

import com.group4.entities.Type;

import java.util.List;
import java.util.Map;

public interface UnitTypeService {
    List<Type> getAllUnitTypes();
    Type getUnitTypeById(int type_id);
    Map<Integer, String> getAvailableUnitTypes();

}
