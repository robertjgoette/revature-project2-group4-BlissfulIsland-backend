package com.group4.services;

import com.group4.entities.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UnitTypeService {
    List<Type> getAllUnitTypes();
    Type getUnitTypeById(int type_id);
    ArrayList<HashMap<String, Object>> getAvailableUnitTypes();

}
