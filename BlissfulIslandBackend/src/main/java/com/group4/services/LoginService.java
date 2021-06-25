package com.group4.services;

import com.group4.entities.Account;

public interface LoginService {
    Account login(String email, String attemptedPassword);
}
