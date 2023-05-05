package com.RevMart.Services;

import com.RevMart.Exception.ServicesException;
import com.RevMart.Models.Product;
import com.RevMart.Models.User;
import com.RevMart.Repositories.ProductRepository;
import com.RevMart.Repositories.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Registers a new user in the system.
     *
     * @param user the user object ot be registered
     * @return the registered user object with an assigned unique identifier
     * @throws ServicesException if a user with the same username already exist in the system
     */
    public User registerUser(User user) throws ServicesException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new ServicesException("User Already Exists");
        }
        return userRepository.save(user);
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param user the user object containing the login credentials
     * @return a logged in user object
     * @throws ServicesException if the username or password is incorrect
     */
    public User loginUser(User user) throws ServicesException {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            throw new ServicesException("Incorrect username or password");
        }
        return foundUser;
    }
    /**
     * Adds a specific product to the cart of the specified user.
     *
     * @param uid is the unique identifier for the user
     * @param pid is the unique identifier for the product
     * @return a user object, with the updated cart information
     * @throws ServicesException if the product cannot be found
     */
    public User addProductToCart(long uid, long pid) throws ServicesException {
        User user = userRepository.findById(uid).get();
        Product product = productRepository.findById(pid).orElseThrow(() -> new ServiceException("Product Not Found"));
        List<Product> cart = user.getCart();
        boolean productFound = false;
        for (Product item : cart) {
            if (item.getId() == product.getId()) {
                item.incrementQuantity();
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            product.setQuantity(1);
            cart.add(product);
        }
        user.setBalance((user.getBalance()) + product.getPrice());
        user.setCart(cart);
        return userRepository.save(user);
    }
    /**
     * Removes a specific product from the cart of the specified user.
     *
     * @param uid is the unique identifier for the user
     * @param pid is the unique identifier for the product
     * @return a user object, with the updated cart information
     */
    public User removeProductFromCart(long uid, long pid) {
        User user = userRepository.findById(uid).get();
        Product product = productRepository.findById(pid).get();
        List<Product> cart = user.getCart();
        for (Product item : cart) {
            if (item.getId() == product.getId()) {
                item.decrementQuantity();
                if (item.getQuantity() == 0) {
                    cart.remove(item);
                }
                break;
            }
        }
        user.setBalance((user.getBalance()) - product.getPrice());
        user.setCart(cart);
        return userRepository.save(user);
    }
    /**
     * Empties a specified user's cart.
     *
     * @param id is the unique identifier for the user
     * @return a user object, with the update cart information
     */
    public User emptyCart(long id) {
        User user = userRepository.findById(id).get();
        List<Product> cart = new ArrayList<>();
        user.setBalance(0);
        user.setCart(cart);
        return userRepository.save(user);
    }
    /**
     * Returns a specified user.
     *
     * @param id is the unique identifier for the user
     * @return a user object
     */
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }
}
