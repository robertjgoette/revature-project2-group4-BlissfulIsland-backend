package com.group4.daos;
import com.group4.entities.Type;
import com.group4.exceptions.ResourceNotFound;
import com.group4.utils.ConnectionUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Type getUnitTypeById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from unit_type where type_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

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
    public ArrayList<HashMap<String, Object>> getAvailableUnitTypes() {

        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select unit_type.type_name, unit_type.type_id, unit.unit_apartment_number,\n" +
                    " unit_type.type_beds, unit_type.type_baths, unit_type.type_cost,count(unit.type_id ) as available,\n" +
                    "(select count(unit.type_id) from unit where unit.type_id = unit_type.type_id) as total_unit\n" +
                    "from unit_type \n" +
                    "left join unit \n" +
                    "on unit.type_id = unit_type.type_id and unit.unit_is_available =true \n" +
                    "group by unit_type.type_name,unit_type.type_beds, unit_type.type_baths,\n" +
                    "unit_type.type_cost, unit_type.type_id , unit.unit_apartment_number ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            ArrayList<HashMap<String, Object>> reports = new ArrayList<HashMap<String, Object>>();
            while (rs.next()){
                HashMap<String, Object> availableUnits = new HashMap<>();

                availableUnits.put("typeName",rs.getString("type_name"));
                availableUnits.put("typeId", rs.getInt("type_id"));
                availableUnits.put("aptNumber", rs.getInt("unit_apartment_number"));
                availableUnits.put("typeBeds", rs.getInt("type_beds"));
                availableUnits.put("typeBaths", rs.getFloat("type_baths"));
                availableUnits.put("typeCost", rs.getFloat("type_cost"));
                availableUnits.put("available", rs.getInt("available"));
                availableUnits.put("totalUnitPerType", rs.getInt("total_unit"));

                reports.add(availableUnits);

            }
            return reports;
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }
}