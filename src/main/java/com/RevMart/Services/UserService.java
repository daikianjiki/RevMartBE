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

    public User registerUser(User user) throws ServicesException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new ServicesException("User Already Exists");
        }
        return userRepository.save(user);
    }

    public User loginUser(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

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
    public User emptyCart(long id) {
        User user = userRepository.findById(id).get();
        List<Product> cart = new ArrayList<>();
        user.setBalance(0);
        user.setCart(cart);
        return userRepository.save(user);
    }
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }
}
