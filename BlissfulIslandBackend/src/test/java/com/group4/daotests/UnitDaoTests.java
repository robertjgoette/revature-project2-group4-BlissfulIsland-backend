package com.group4.daotests;
import com.group4.daos.UnitDao;
import com.group4.entities.Unit;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UnitDaoTests {
    static UnitDao unitDao = null;
    @Test(priority = 1)
    void getUnitById(){
        Unit unit = unitDao.getUnitById(1);
        Assert.assertEquals(unit.getApartmentNumber(), 101);
    }
    @Test(priority = 2)
    void getAllUnits(){
        List<Unit> units = unitDao.getAllUnits();
        Assert.assertTrue(units.size()>=3);
    }

}
