package com.group4.daos;

import com.group4.entities.Account;

public interface LoginDAO {
    Account login(String email, String attemptedPassword);
}
