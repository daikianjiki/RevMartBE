package com.RevMart;

import com.RevMart.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.RevMart.Exception.ServicesException;
import com.RevMart.Models.User;
import com.RevMart.Repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class RevMartApplicationTests {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;

	@Test
	void contextLoads() {
	}
	@Test
	@DisplayName("User Registration Test")
	public void registerUser() throws ServicesException {
		User user = new User(1, "mockUsername", "mockPassword", 0.00, null);
		User mockUser = new User(1, "mockUsername", "mockPassword", 0.00, null);
		when(userRepository.save(user)).thenReturn(mockUser);

		User result = userService.registerUser(user);

		assertEquals("register user", mockUser, result);

		verify(userRepository, times(1)).save(user);
	}
	@Test
	@DisplayName("User Login Test")
	public void loginUser() throws ServicesException {
		User mockUser = new User(1, "mockUsername", "mockPassword", 0.00, null);
		when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(mockUser);

		User result = userService.loginUser(mockUser);

		assertEquals("login user", mockUser, result);

		verify(userRepository, times(1)).findByUsername(mockUser.getUsername());
	}
}
