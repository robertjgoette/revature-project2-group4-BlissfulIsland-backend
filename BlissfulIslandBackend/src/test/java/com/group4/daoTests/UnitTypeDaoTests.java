package com.group4.daoTests;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.group4.daos.UnitTypeDAO;
import com.group4.daos.UnitTypeDaoPostgres;
import com.group4.entities.Type;
import com.group4.entities.Unit;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UnitTypeDaoTests {
    private UnitTypeDAO unitTypeDAO = new UnitTypeDaoPostgres();

    @Test(priority = 1)
    void  getAllUnitTypesPS(){

        List<Type> type = unitTypeDAO.getAllUnitTypes();
        Assert.assertTrue(type.isEmpty() == false);
    }

    @Test(priority = 2)
    void getAllUnitTypesFS(){
        List<Type> type = unitTypeDAO.getAllUnitTypes();
        int len = type.size()+1;
        System.out.println(" lent is "+len);
        Assert.assertFalse(type.isEmpty() == true);

    }

    @Test(priority = 3)
    void getUnitTypesByIdPS(){
        Type id = unitTypeDAO.getUnitTypeById(2);
        Assert.assertEquals(id.getName(), "normal");
    }

    @Test(priority = 4)
    void getUnitTypesByIdPShh(){
        ArrayList<HashMap<String, Object>> availableTypes;
        availableTypes = unitTypeDAO.getAvailableUnitTypes();


        for (HashMap arr: availableTypes){
            //System.out.println(arr.get("available"));
            if(arr.get("typeName").equals("normal")){

                System.out.println(arr.get("available"));
                Assert.assertEquals(arr.get("available"), 1);
                Assert.assertEquals(arr.get("totalUnitPerType"), 2);
            }

           Assert.assertTrue(arr.containsKey("typeName"));

        }

    }
}
