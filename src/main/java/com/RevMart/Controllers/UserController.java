package com.RevMart.Controllers;

import com.RevMart.Exception.ServicesException;
import com.RevMart.Models.User;
import com.RevMart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a new user in the system.
     *
     * @param user the user object to be registered
     * @return the registered user object, with an assigned unique identifier
     * @throws ServicesException if a user with the same username already exist in the system
     */
    @PostMapping("register")
    public User registerUser(@RequestBody User user) throws ServicesException {
        return userService.registerUser(user);
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param user the user object containing the login credentials
     * @return a logged in user object
     * @throws ServicesException if the username or password is incorrect
     */
    @PostMapping("login")
    public User loginUser(@RequestBody User user) throws ServicesException {
        return userService.loginUser(user);
    }

    /**
     * Adds a specific product to the cart of the specified user.
     *
     * @param uid is the unique identifier for the user
     * @param pid is the unique identifier for the product
     * @return a user object, with the updated cart information
     * @throws ServicesException if the product cannot be found
     */
    @PostMapping("{uid}/addProduct/{pid}")
    public User addProductToCart(@PathVariable long uid, @PathVariable long pid) throws ServicesException {
        return userService.addProductToCart(uid, pid);
    }

    /**
     * Removes a specific product from the cart of the specified user.
     *
     * @param uid is the unique identifier for the user
     * @param pid is the unique identifier for the product
     * @return a user object, with the updated cart information
     */
    @PostMapping("{uid}/removeProduct/{pid}")
    public User removeProductFromCart(@PathVariable long uid, @PathVariable long pid) {
        return userService.removeProductFromCart(uid, pid);
    }

    /**
     * Empties a specified user's cart.
     *
     * @param id is the unique identifier for the user
     * @return a user object, with the update cart information
     */
    @PatchMapping("{id}/emptyCart")
    public User emptyCart(@PathVariable long id) {
        return userService.emptyCart(id);
    }

    /**
     * Returns a specified user.
     *
     * @param id is the unique identifier for the user
     * @return a user object
     */
    @GetMapping("{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
}
