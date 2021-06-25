package com.group4.daos;

import com.group4.entities.Account;
import com.group4.exceptions.ResourceNotFound;
import com.group4.utils.ConnectionUtil;
import java.sql.*;

public class LoginDAOPostgres implements LoginDAO{
    @Override
    public Account login(String email, String attemptedPassword) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account where account_email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            //if the email address exists in the database
            if(rs.next()){
                String correctPassword = rs.getString(3);

                // if the password is correct
                if(attemptedPassword.equals(correctPassword)){
                    int accountId = rs.getInt(1);
                    String returnedEmail = rs.getString(2);
                    String firstName = rs.getString(4);
                    String lastName = rs.getString(5);
                    int accountType = rs.getInt(6);
                    int unitId = rs.getInt(7);

                    return new Account(accountId, returnedEmail, "", firstName, lastName, unitId, accountType);
                }
                else{
                    throw new ResourceNotFound("Incorrect password");
                }
            }
            else {
                throw new ResourceNotFound("Invalid email address");
            }

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }
}
