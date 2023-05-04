package com.RevMart.Controllers;

import com.RevMart.Models.Product;
import com.RevMart.Models.User;
import com.RevMart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }
    @PostMapping("{uid}/addProduct/{pid}")
    public User addProductToCart(@PathVariable long uid, @PathVariable long pid) {
        return userService.addProductToCart(uid, pid);
    }
    @PostMapping("{uid}/removeProduct/{pid}")
    public User removeProductFromCart(@PathVariable long uid, @PathVariable long pid) {
        return userService.removeProductFromCart(uid, pid);
    }
    @GetMapping("{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
}