package com.RevMart;

import com.RevMart.Controllers.UserController;
import com.RevMart.Models.User;
import com.RevMart.Repositories.UserRepository;
import com.RevMart.Services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UnitTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @InjectMocks
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get User by Id")
    public void getUserByIdTest() throws Exception {
        User user = new User(1, "mockUsername", "mockPassword", 0.00, null);
        User mockUser = new User(1, "mockUsername", "mockPassword", 0.00, null);
        when(userRepository.save(user)).thenReturn(mockUser);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9000/user/{id}", user.getId())
                        .content("{\"id\": 1, \"username\": mockUsername, \"password\": mockPassword, \"balance\": 0.00, \"cart\": null}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("mockUsername"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("mockPassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cart").value(null));
    }
}