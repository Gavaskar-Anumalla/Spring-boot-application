package com.apss;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.apss.Controller.UserController;
import com.apss.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = UserController.class)
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenUserId_whenDeleteUser_thenReturn200() throws Exception {
		// given-precondition or setup
		int userId = 200000;
		// given(userService).deleteByUserId(userId);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/User/deleteUser/{id}", userId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());
	}

}
