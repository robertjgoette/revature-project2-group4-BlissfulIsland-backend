package com.group4.daos;
import com.group4.entities.Type;
import com.group4.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitTypeDaoPostgres implements UnitTypeDAO{

    @Override
    public List<Type> getAllUnitTypes() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from unit_type";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Type> unitTypes = new ArrayList<>();

            while (rs.next()){
                Type unitType = new Type();
                unitType.setTypeID(rs.getInt("type_id"));
                unitType.setName(rs.getString("type_name"));
                unitType.setCost(rs.getFloat("type_cost"));
                unitType.setBeds(rs.getInt("type_beds"));
                unitType.setBaths(rs.getFloat("type_baths"));
                unitTypes.add(unitType);
            }
            return unitTypes;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Type getUnitTypeById(int type_id) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from unit_type where type_id =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,type_id);
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
