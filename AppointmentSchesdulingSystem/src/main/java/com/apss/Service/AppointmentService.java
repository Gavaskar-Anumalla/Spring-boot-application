package com.apss.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apss.Common.ApiResponse;
import com.apss.Exception.RecordNotFoundException;
import com.apss.Model.Appointment;
import com.apss.Repository.AppointmentRepo;

@Service
public class AppointmentService {

	@Autowired
	public AppointmentRepo appointmentRepo;

	@Autowired
	public ApiResponse apiResponse;

	/*
	 * this method is used to save the appointment
	 * 
	 * @param Appointment
	 * 
	 * @return Appointment
	 */
	public Appointment registerAppointment(Appointment appointment) {
		return appointmentRepo.save(appointment);

	}

	/*
	 * this method is used to save the multiple appointments
	 * 
	 * @param List<Appointment>
	 * 
	 * @return List<Appointment>
	 */
	public List<Appointment> registerAppointments(List<Appointment> appointments) {

		return appointmentRepo.saveAll(appointments);
	}

	/*
	 * this method is used to fetch all the appointments available
	 * 
	 * @return List<Appointment>
	 */
	public List<Appointment> getAllAppointments() {
		return appointmentRepo.findAll();

	}

	public Appointment getAppointmentDetails(int appointmentId) {
		/*
		 * this method is used to fetch all the appointment available by id
		 * 
		 * @param appointmentId
		 * 
		 * @return Appointment
		 */
		Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);

		if (appointment != null) {
			return appointment;
		} else {
			throw new RecordNotFoundException("Given Id is not available");
		}

	}
	/*
	 * this method is used to reschedule the already booked appointments only once
	 * 
	 * @param Appointment
	 * 
	 * @return Appointment
	 */

	public ApiResponse rescheduleAppointment(Appointment appointment) {
		Appointment existingAppointment = appointmentRepo.findById(appointment.getAppointmentId()).orElse(null);
		if (existingAppointment == null) {
			throw new RecordNotFoundException("There are no records with given id");
		}

		else if (existingAppointment.getAppointmentStatus().equalsIgnoreCase("booked")
				&& !existingAppointment.getAppointmentStatus().equalsIgnoreCase("rescheduled")) {

			// !existingAppointment.getAppointmentStatus().equalsIgnoreCase("rescheduled")
			existingAppointment.setDateOfAppointment(appointment.getDateOfAppointment());
			existingAppointment.setTimeOfAppointment(appointment.getTimeOfAppointment());
			existingAppointment.setAppointmentStatus("rescheduled");

			Appointment appointment2 = appointmentRepo.save(existingAppointment);
			apiResponse.setData(appointment2);
			return apiResponse;
		} else {
			apiResponse.setData("you already rescheduled your session!!!!!!!");
			return apiResponse;

		}
	}

	/*
	 * this method is used cancel the already booked appointments
	 * 
	 * @param Appointment
	 * 
	 * @return Appointment
	 */
	public ApiResponse cancelAppointment(Appointment appointment) {
		Appointment existingAppointment = appointmentRepo.findById(appointment.getAppointmentId()).orElse(null);
		if (existingAppointment == null) {
			throw new RecordNotFoundException("There are no records with given id");
		}

		else if (existingAppointment.getAppointmentStatus().equalsIgnoreCase("booked")
				|| existingAppointment.getAppointmentStatus().equalsIgnoreCase("rescheduled")) {

			existingAppointment.setAppointmentStatus("Cancelled");

			Appointment appointment2 = appointmentRepo.save(existingAppointment);
			apiResponse.setData(appointment2);
			return apiResponse;
		} else {
			apiResponse.setData("you already Cancelled your session!!!!!!!");
			return apiResponse;

		}
	}
	/*
	 * this method is used to fetch all the appointments corresponding UserId
	 * 
	 * @param UserId
	 * 
	 * @return List<Appointment>
	 */

	public List<Appointment> findAllAppoByUserId(int uid) {

		return appointmentRepo.findByAllUserId(uid);
	}
}
