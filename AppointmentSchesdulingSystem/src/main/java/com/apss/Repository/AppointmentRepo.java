package com.apss.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apss.Model.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	@Query("FROM Appointment as a join a.user as u where u.userId= ?1")
	List<Appointment> findByAllUserId(int userId);
}
