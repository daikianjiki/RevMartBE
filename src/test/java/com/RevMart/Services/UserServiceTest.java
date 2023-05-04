package com.RevMart.Services;

import com.RevMart.Models.User;
import com.RevMart.Models.UserTest;
import com.RevMart.Repositories.ProductRepository;
import com.RevMart.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    UserService userService;
    ProductRepository productRepository;

    @BeforeEach void setUp() {
        this.userService = new UserService(this.userRepository, this.productRepository);
    }

    @Test void registerUser(User user) {
        userService.registerUser(user);
        verify(userRepository).flush();

    }
}
