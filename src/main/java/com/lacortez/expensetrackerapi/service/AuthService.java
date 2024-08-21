package com.lacortez.expensetrackerapi.service;

import com.lacortez.expensetrackerapi.exception.UserNotFoundException;
import com.lacortez.expensetrackerapi.model.User;

public interface AuthService {

    User signIn(String userName, String password) throws UserNotFoundException;

    User signUp(String userName, String password);

}
