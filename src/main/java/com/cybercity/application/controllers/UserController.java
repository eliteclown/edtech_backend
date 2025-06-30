package com.cybercity.application.controllers;

import com.cybercity.application.dtos.UserDTO;
import com.cybercity.application.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO inputDTO){
        return userService.createNewUser(inputDTO);
    }

    @GetMapping("/get/{userId}")
    public UserDTO getUser(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/update/{userId}")
    public UserDTO updateUser(@RequestBody UserDTO updateDTO, @PathVariable Long userId){
        return userService.updateUserById(updateDTO,userId);
    }

}
