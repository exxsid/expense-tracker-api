package com.lacortez.expensetrackerapi.controller;

import com.lacortez.expensetrackerapi.exception.UserNotFoundException;
import com.lacortez.expensetrackerapi.model.User;
import com.lacortez.expensetrackerapi.service.impl.AuthServiceImpl;
import com.lacortez.expensetrackerapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private UserServiceImpl userService;
    private AuthServiceImpl authService;


    public AuthController(UserServiceImpl userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/foo")
    public String foo(){
        return "foo";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @RequestParam String userName,
            @RequestParam String password
    ) {
        User newUser = authService.signUp(userName, password);
        // when newUser is null means that the userName already exist
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(
            @RequestParam String userName,
            @RequestParam String password
    ) {
        try {
            User user = authService.signIn(userName, password);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
