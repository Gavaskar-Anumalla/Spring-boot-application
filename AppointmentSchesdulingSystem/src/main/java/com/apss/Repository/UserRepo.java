package com.apss.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apss.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<List<User>> findByUserFirstName(String userName);

	public User findByUserIdAndUserPassword(int userId, String userPassword);

}
