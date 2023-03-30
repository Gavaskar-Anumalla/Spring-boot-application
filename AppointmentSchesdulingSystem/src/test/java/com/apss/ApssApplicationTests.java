package com.apss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.apss.Model.User;
import com.apss.Repository.UserRepo;
import com.apss.Service.UserService;

@SpringBootTest
class ApssApplicationTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepo repo;

	@Test
	public void getUserTest() {

		when(repo.findAll()).thenReturn(Stream.of(
				new User(200000, "gavaskar", "anumalla", "457jkl", "gava@gmail.com", 889478596, LocalDate.now(), null),
				new User(200001, "gavaskar", "anumalla", "457jkl", "gava@gmail.com", 979478596, LocalDate.now(), null))
				.collect(Collectors.toList()));
		assertEquals(2, service.fetchUser().size());

	}

	@Test
	public void saveUserTest() {
		User user = new User(200000, "gavaskar", "anumalla", "457jkl", "gava@gmail.com", 889478596, LocalDate.now(),
				null);

		when(repo.save(user)).thenReturn(user);

		assertEquals(user, service.registerUser(user));
	}

	/*
	 * @Test public void getByUserIdTest() { User user = new User(200000,
	 * "gavaskar", "anumalla", "457jkl", "gava@gmail.com", 889478596,
	 * LocalDate.now(), null);
	 * 
	 * when(repo.findById(200000)).thenReturn(user);
	 * 
	 * assertEquals(user, service.getUserById(200000).isPresent());
	 * 
	 * }
	 */

}
