package com.RevMart;

import com.RevMart.Controllers.UserController;
import com.RevMart.Models.Product;
import com.RevMart.Models.User;
import com.RevMart.Repositories.UserRepository;
import com.RevMart.Services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ControllersTest {

    @Mock
    UserRepository userRepository;
    @MockBean
    UserService userService;
    @MockBean
    UserController userController;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get User by Id")
    public void getUserByIdTest() throws Exception {
        List<Product> cart = new ArrayList<>();
        User user = new User(1, "mockUsername", "mockPassword", 0.00, cart);
        doReturn(user).when(userController).getUserById(1);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9000/user/{id}", user.getId())
                        .content("{\"id\": 1, \"username\": mockUsername, \"password\": mockPassword, \"balance\": 0.00, \"cart\":" + cart + "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("mockUsername"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("mockPassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cart").value(cart));
    }
    @Test
    @DisplayName("Add Product to Cart Test")
    public void emptyCartTest() throws Exception {
        Product p1 = new Product(1, "Organic Gala Apples", 2.99, 1);
        Product p2 = new Product(2, "Organic Red Grapes", 2.79, 1);
        List<Product> cart = new ArrayList<>();
        User user = new User(1, "mockUsername", "mockPassword", 0.00, cart);
        when(userController.addProductToCart(1,1)).thenReturn(user);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:9000/user/1/addProduct/1")
                        .content("{\"id\": 1, \"username\": mockUsername, \"password\": mockPassword, \"balance\": 0.00, \"cart\": " + cart + "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cart").value(cart));
    }
}