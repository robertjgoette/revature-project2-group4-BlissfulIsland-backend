
package com.group4.daos;
import com.group4.entities.Type;
import java.util.List;

public interface UnitTypeDAO {
    //READ

    // returns a list of all types
    List<Type> getAllUnitTypes();

    // returns a list of types by id
    Type getUnitTypeById(int type_id);


}
