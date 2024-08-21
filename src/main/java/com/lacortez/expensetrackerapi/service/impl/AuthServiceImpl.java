package com.lacortez.expensetrackerapi.service.impl;

import com.lacortez.expensetrackerapi.exception.UserNotFoundException;
import com.lacortez.expensetrackerapi.model.User;
import com.lacortez.expensetrackerapi.repository.AuthRepository;
import com.lacortez.expensetrackerapi.service.AuthService;
import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private Argon2Function argon2Function = Argon2Function.getInstance(
            2,
            12,
            2,
            10,
            Argon2.ID,
            19
    );

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User signIn(String userName, String password) throws UserNotFoundException {
        // get the user by userName
        List<User> user = authRepository.findByUserName(userName);
        // if the userName is not in the database
        if (user.isEmpty()) throw new UserNotFoundException("User not found");

        User currUser = user.get(0);
        // get the salt of the user for password verification
        String salt = currUser.getSalt();
        String hashedPassword = currUser.getPassword();
        boolean isMatch = Password.check(password, hashedPassword)
                .with(argon2Function);
        return isMatch ? currUser : null;
    }

    @Override
    public User signUp(String userName, String password) {
        // check if the userName already exist
        List<User> existingUser = authRepository.findByUserName(userName);
        if (!existingUser.isEmpty()) {
            return null;
        }
        // hash the password for security
        Hash hash = Password.hash(password)
                .addRandomSalt(12)
                .with(argon2Function);
        String hashedPassword = hash.getResult();
        String salt = hash.getSalt();

        // insert the user to database
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(hashedPassword);
        newUser.setSalt(salt);
        return authRepository.save(newUser);
    }
}
