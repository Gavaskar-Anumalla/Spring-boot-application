package com.apss.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id

	// @GeneratedValue(strategy = GenerationType.IDENTITY)

	@GeneratedValue(generator = "sequence_generator")

	@GenericGenerator(name = "sequence_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {

			@Parameter(name = "sequence_name", value = "user_sequence"),

			@Parameter(name = "initial_value", value = "200000"), @Parameter(name = "increment_size", value = "1") })
	private int userId;

	@NotEmpty
	private String userFirstName;

	@NotEmpty
	private String userLastName;

	@NotNull
	@Size(min = 5, max = 14)
	private String userPassword;

	@Email
	private String email;
	private long contactNo;
	private LocalDate dob;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")

	@JsonManagedReference

	private List<Appointment> appointments = new ArrayList<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
		for (Appointment var : appointments) {
			var.setUser(this);
		}
	}

	public User() {
		super();
	}

	public User(int userId, @NotEmpty String userFirstName, @NotEmpty String userLastName,
			@NotNull @Size(min = 5, max = 14) String userPassword, @Email String email, long contactNo, LocalDate dob,
			List<Appointment> appointments) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", email=" + email + ", contactNo=" + contactNo + ", dob=" + dob + "]";
	}

}
