package com.group4.daoTests;
import com.group4.daos.UnitDao;
import com.group4.daos.UnitDaoPostgres;
import com.group4.entities.Unit;
import com.group4.exceptions.ResourceNotFound;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UnitDaoTests {
    static UnitDao unitDao = new UnitDaoPostgres();
    @Test(priority = 1)
    void getUnitById(){
        Unit unit = unitDao.getUnitById(2);
        Assert.assertEquals(unit.getApartmentNumber(), 101);
    }
    @Test
    void getUnitByIdNegative(){
        try {
            Unit unit = unitDao.getUnitById(999);
        }catch (ResourceNotFound e){
            Assert.assertTrue(true);
        }
    }
    @Test(priority = 2)
    void getAllUnits(){
        List<Unit> units = unitDao.getAllUnits();
        Assert.assertTrue(units.size()>=3);
    }

}
