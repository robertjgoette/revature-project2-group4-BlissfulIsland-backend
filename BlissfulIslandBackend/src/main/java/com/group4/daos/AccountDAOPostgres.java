package com.group4.daos;

import com.group4.entities.Account;
import com.group4.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAOPostgres implements AccountDAO{
    @Override
    public Account createAccount(Account account) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "";
            PreparedStatement ps = connection.prepareStatement(sql);
            // ps.setType() for all entered values

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            account.setAccountID(key);
            return account;
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Account getAccountById(int id) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public List<Account> getAllManagerAccounts() {
        return null;
    }

    @Override
    public List<Account> getAllTenantAccounts() {
        return null;
    }

    @Override
    public Account updateAccountById(int id, Account account) {
        return null;
    }

    @Override
    public boolean deleteAccountById(int id) {
        return false;
    }
}
