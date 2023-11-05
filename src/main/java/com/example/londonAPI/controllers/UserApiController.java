package com.example.londonAPI.controllers;

import com.example.londonAPI.models.User;
import com.example.londonAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/usersFromLondon")
    public List<User> getUsersFromLondonWithinRange(){
        return userService.getUsersFromLondonWithinRange();

    }
}