package com.group4.daos;

import com.group4.entities.Unit;
import com.group4.exceptions.ResourceNotFound;
import com.group4.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDaoPostgres implements UnitDao {
    @Override
    public Unit getUnitById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from unit where unit_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Unit unit = new Unit();
            unit.setUnitID(rs.getInt("unit_id"));
            unit.setTypeID(rs.getInt("type_id"));
            unit.setAvailable(rs.getBoolean("unit_is_available"));
            unit.setApartmentNumber(rs.getInt("unit_apartment_number"));
            unit.setUnitBalance(rs.getInt("unit_balance"));

            return unit;

        } catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            throw new ResourceNotFound("Resource of " + id + " not found");
        }
    }

    @Override
    public List<Unit> getAllUnits() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from unit order by unit_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Unit> units = new ArrayList<>();

            while(rs.next()) {
                Unit unit = new Unit();
                unit.setUnitID(rs.getInt("unit_id"));
                unit.setTypeID(rs.getInt("type_id"));
                unit.setAvailable(rs.getBoolean("unit_is_available"));
                unit.setApartmentNumber(rs.getInt("unit_apartment_number"));
                unit.setUnitBalance(rs.getInt("unit_balance"));
                units.add(unit);
            }
            return units;

        } catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            return null;
        }
    }
}
