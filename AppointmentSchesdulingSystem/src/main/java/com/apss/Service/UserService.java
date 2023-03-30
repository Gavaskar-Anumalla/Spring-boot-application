package com.apss.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apss.Common.ApiResponse;
import com.apss.Dto.LoginRequest;
import com.apss.Model.User;
import com.apss.Repository.UserRepo;
import com.apss.Util.JwtUtil;

@Service
public class UserService {

	@Autowired
	UserRepo repo;

	@Autowired
	ApiResponse apiResponse;

	@Autowired
	JwtUtil jwtUtil;
	/*
	 * This method is used to register the new user
	 * 
	 * @param user
	 * 
	 * @return user
	 */

	public User registerUser(User user) {

		return repo.save(user);

	}
	/*
	 * This method is used to fetch the details of the all users
	 * 
	 * @return users
	 */

	public List<User> fetchUser() {
		return repo.findAll();
	}
	/*
	 * This method is used to get the details of the user by id if id is present
	 * 
	 * @param userid
	 * 
	 * @return user
	 */

	public Optional<User> getUserById(int userId) {

		return repo.findById(userId);
	}

	/*
	 * This method is used to get the details of the user by Name if userName is
	 * present
	 * 
	 * @param userName
	 * 
	 * @return List<user>
	 */
	public Optional<List<User>> findByUserName(String userName) {

		// User user = usersByName.get(name);

		// User user= usersByName.get(name);
		// Optional<User> opt = Optional.ofNullable(user);

		// Optional<List<User>> user =
		// Optional.ofNullable(repo.findByUserFirstName(userName));
		// System.out.println(user);

		return repo.findByUserFirstName(userName);

	}

	/*
	 * This method is used to update the details of the user
	 * 
	 * @param user
	 * 
	 * @return user
	 */

	public User updateUserById(User user) {
		User existingUser = repo.findById(user.getUserId()).orElse(null);
		existingUser.setUserFirstName(user.getUserFirstName());
		existingUser.setUserLastName(user.getUserLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setUserPassword(user.getUserPassword());
		existingUser.setDob(user.getDob());
		existingUser.setContactNo(user.getContactNo());

		return repo.save(existingUser);

	}

	/*
	 * This method is used to remove the details of the user
	 * 
	 * @param userId
	 * 
	 * @return user
	 */
	public String deleteByUserId(int userId) {
		repo.deleteById(userId);
		return "user is deleted succesfully" + userId;

	}
	/*
	 * This method is used to remove the details of the user
	 * 
	 * @param LoginRequest
	 * 
	 * @return apiResponse
	 */

	public ApiResponse loginCheck(LoginRequest loginRequest) {
		// validation
		// verify given user is valid or not
		User user = repo.findByUserIdAndUserPassword(loginRequest.getUserId(), loginRequest.getUserPassword());
		if (user == null) {
			apiResponse.setData("It is not logged in ");

		} else {
			// generating jwt token
			String token = jwtUtil.generateJwt(user);
			// apiResponse.setData(token);

			Map<String, Object> data = new HashMap<>();
			data.put("accessToken", token);
			apiResponse.setData(data);
			return apiResponse;
		}
		return apiResponse;
	}
}
