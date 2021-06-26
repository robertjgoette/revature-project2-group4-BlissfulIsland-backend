package com.group4.daos;
import com.group4.entities.Type;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UnitTypeDAO {
    //READ

    // returns a list of all types
    List<Type> getAllUnitTypes();

    // returns a list of types by id
    Type getUnitTypeById(int type_id);

    // return units type based on their availability
    ArrayList<HashMap<String, Object>> getAvailableUnitTypes();
}
