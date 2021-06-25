package com.group4.daos;
import com.group4.entities.Type;
import com.group4.exceptions.ResourceNotFound;
import com.group4.utils.ConnectionUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            ResultSet rs = ps.executeQuery();
            rs.next();

            Type unitType = new Type();
            unitType.setTypeID(rs.getInt("type_id"));
            unitType.setName(rs.getString("type_name"));
            unitType.setCost(rs.getFloat("type_cost"));
            unitType.setBeds(rs.getInt("type_beds"));
            unitType.setBaths(rs.getFloat("type_baths"));

            return unitType;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw  new ResourceNotFound("Resource not found");
        }

    }

    @Override
    public Map<String, Integer> getAvailableUnitTypes() throws SQLException {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select unit_type.type_name, unit_type.type_id, count(unit.type_id ) as available,\n" +
                    "(select count(unit.type_id) from unit where unit.type_id = unit_type.type_id) as total_unit\n" +
                    "from unit_type \n" +
                    "left join unit \n" +
                    "on unit.type_id = unit_type.type_id and unit.unit_is_available =true \n" +
                    "group by unit_type.type_name, unit_type.type_id  ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData data= rs.getMetaData();
            for(int i =0;i<data.getColumnCount();i++){
                System.out.println(data.getColumnName(i));
            }

           // Map<String, Integer> availableUnits = new HashMap<>();
            while (rs.next()){
               // availableUnits.put(rs.getString("unit"), 1);


            }



        }
        return null;
    }
}
