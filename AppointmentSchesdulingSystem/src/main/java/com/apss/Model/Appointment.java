package com.apss.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity

@Table(name = "Appointment_Details")
public class Appointment {

	@Id

	@GeneratedValue(generator = "sequence_generator")

	@GenericGenerator(name = "sequence_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {

			@Parameter(name = "sequence_name", value = "appointment_sequence"),

			@Parameter(name = "initial_value", value = "100"), @Parameter(name = "increment_size", value = "1") })

	private int appointmentId;

	private String appointmentType;
	private String appointmentStatus;

	private LocalDate dateOfAppointment;

	private LocalTime timeOfAppointment;

	@ManyToOne

	@JoinColumn(name = "userId")

	@JsonBackReference
	private User user;

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public LocalDate getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(LocalDate dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public LocalTime getTimeOfAppointment() {
		return timeOfAppointment;
	}

	public void setTimeOfAppointment(LocalTime timeOfAppointment) {
		this.timeOfAppointment = timeOfAppointment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Appointment(int appointmentId, @NotEmpty String appointmentType, String appointmentStatus,
			@NotEmpty LocalDate dateOfAppointment, @NotEmpty LocalTime timeOfAppointment, User user) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentType = appointmentType;
		this.appointmentStatus = appointmentStatus;
		this.dateOfAppointment = dateOfAppointment;
		this.timeOfAppointment = timeOfAppointment;
		this.user = user;
	}

	public Appointment() {
		super();
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appointmentType=" + appointmentType
				+ ", appointmentStatus=" + appointmentStatus + ", dateOfAppointment=" + dateOfAppointment
				+ ", timeOfAppointment=" + timeOfAppointment + "]";
	}

}
