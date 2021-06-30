package com.group4.daos;

import com.group4.entities.Account;
import com.group4.exceptions.InvalidInputException;
import com.group4.exceptions.ResourceNotFound;
import com.group4.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOPostgres implements AccountDAO{
    @Override
    public Account createAccount(Account account) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into account values (default, ?, ?, ?, ?, ?, ?) returning account_id";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassword());
                ps.setString(3, account.getFirstName());
                ps.setString(4, account.getLastName());
                ps.setInt(5, account.getAccountType());
                ps.setInt(6, account.getUnitID());
            }
            catch (NullPointerException e){
                throw new InvalidInputException("Invalid input for account", 404);
            }
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int newId = rs.getInt(1);
                account.setAccountID(newId);
            }
            else{
                throw new RuntimeException("An error occurred while attempting to insert the account into the table");
            }
            return account;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Account getAccountById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from account where account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int returnedId = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                int accountType = rs.getInt(6);
                int unitId = rs.getInt(7);
                return new Account(returnedId, email, password, firstName, lastName, unitId, accountType);
            }
            else{
                throw new ResourceNotFound("The account with ID of " + id + " could not be found.");
            }

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Account> getAllAccounts() {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account order by account_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Account> accountList = new ArrayList<>();
            while(rs.next()){
                int accountID = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                int accountType = rs.getInt(6);
                int unitID = rs.getInt(7);

                Account account = new Account(accountID, email, password, firstName, lastName, unitID, accountType);
                accountList.add(account);
            }
            return accountList;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Account> getAllManagerAccounts() {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account where account_type = 1 order by account_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Account> accountList = new ArrayList<>();
            while(rs.next()){
                int accountID = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                int accountType = rs.getInt(6);
                int unitID = rs.getInt(7);

                Account account = new Account(accountID, email, password, firstName, lastName, unitID, accountType);
                accountList.add(account);
            }
            return accountList;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Account> getAllTenantAccounts() {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account where account_type = 0 order by account_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Account> accountList = new ArrayList<>();
            while(rs.next()){
                int accountID = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                int accountType = rs.getInt(6);
                int unitID = rs.getInt(7);

                Account account = new Account(accountID, email, password, firstName, lastName, unitID, accountType);
                accountList.add(account);
            }
            return accountList;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Account updateAccount(Account account) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update account set account_email = ?, account_password = ?, account_first_name = ?, account_last_name = ?, account_type = ?, unit_id = ? where account_id = ? returning *";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getFirstName());
            ps.setString(4, account.getLastName());
            ps.setInt(5, account.getAccountType());
            ps.setInt(6, account.getUnitID());
            ps.setInt(7, account.getAccountID());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            Account returnedAccount;
            if(rs.next()){
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                int accountType = rs.getInt(6);
                int unitId = rs.getInt(7);
                returnedAccount = new Account(id, email, password, firstName, lastName, unitId, accountType);
            }
            else{
                throw new RuntimeException("Could not set account ID because it was not returned");
            }
            return returnedAccount;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAccountById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            try{
                this.getAccountById(id);
            }
            catch (ResourceNotFound resourceNotFound){
                resourceNotFound.printStackTrace();
                return false;
            }

            String sql = "delete from account where account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

            try{
                this.getAccountById(id);
            }
            catch (ResourceNotFound resourceNotFound){
                resourceNotFound.printStackTrace();
                return true;
            }

            return false;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }
}
