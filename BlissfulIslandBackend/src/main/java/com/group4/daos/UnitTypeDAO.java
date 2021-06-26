
package com.group4.daos;
import com.group4.entities.Type;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UnitTypeDAO {
    //READ

    // returns a list of all types
    List<Type> getAllUnitTypes();

    // returns a list of types by id
    Type getUnitTypeById(int type_id);

    // return units type based on their availability
    Map<String, Integer> getAvailableUnitTypes() throws SQLException;



}
