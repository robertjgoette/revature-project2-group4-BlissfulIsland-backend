package com.group4.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionUtil {
    public static Connection createConnection(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:postgresql://project-2.cvy8vaqob2qj.us-east-2.rds.amazonaws.com:5432/postgres?user=postgres&password=password");
            return connection;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(createConnection());
    }
}