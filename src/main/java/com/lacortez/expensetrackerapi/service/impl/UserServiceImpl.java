package com.lacortez.expensetrackerapi.service.impl;

import com.lacortez.expensetrackerapi.model.User;
import com.lacortez.expensetrackerapi.repository.UserRepository;
import com.lacortez.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUses() {
        return userRepository.findAll();
    }
}
