package com.apss.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apss.Common.ApiResponse;
import com.apss.Dto.LoginRequest;
import com.apss.Exception.DataNotFoundException;
import com.apss.Exception.RecordNotFoundException;
import com.apss.Model.User;
import com.apss.Service.UserService;
import com.apss.Util.JwtUtil;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	public UserService service;

	@Autowired
	public ApiResponse apiResponse;

	@Autowired
	public JwtUtil jwtUtil;

	@PostMapping("/addUser")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {

		/* System.out.println(user); */
		User userobj = service.registerUser(user);
		return new ResponseEntity<String>("Account created Succesfully..  your account id is " + userobj.getUserId(),
				HttpStatus.OK);
	}

	@GetMapping("/fetchUser")
	public ResponseEntity<List<User>> getUser(@RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws Exception {
		jwtUtil.verify(auth);

		List<User> list = service.fetchUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);

	}

	@GetMapping("/fetchByUserId/{id}")
	public ResponseEntity<User> findByUserId(@PathVariable("id") int userId) {
		Optional<User> userobj = service.getUserById(userId);

		if (userobj.isPresent()) {
			// System.out.println(userobj);
			return new ResponseEntity<User>(userobj.get(), HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Given Id is not available");
		}

	}

	@GetMapping("/fetchByUserName/{name}")
	public ResponseEntity<List<User>> findByUserName(@PathVariable("name") String userName) {
		Optional<List<User>> users = service.findByUserName(userName);

		if (users.isPresent()) {
			return new ResponseEntity<List<User>>(users.get(), HttpStatus.OK);
		} else {
			throw new DataNotFoundException("data is not found");
		}

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
		String string = service.deleteByUserId(userId);
		return new ResponseEntity<String>(string, HttpStatus.OK);
	}

	@PutMapping("/editUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		User user2 = service.updateUserById(user);
		return new ResponseEntity<User>(user2, HttpStatus.OK);

	}

	@PostMapping("/loginCheck")
	public ResponseEntity<ApiResponse> loginCheck(@RequestBody LoginRequest loginRequest) {

		ApiResponse apiResponse = service.loginCheck(loginRequest);
		/*
		 * if (user != null) { status = "sucessfully logged in"; } else { status =
		 * "Please check your userid and password"; }
		 */
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/privateApi") public ResponseEntity<ApiResponse> privateApi(
	 * 
	 * @RequestHeader(value = "authorization", defaultValue = "") String auth)
	 * throws Exception {
	 * 
	 * jwtUtil.verify(auth); apiResponse.setData("this is private api"); return new
	 * ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK); }
	 */

}
