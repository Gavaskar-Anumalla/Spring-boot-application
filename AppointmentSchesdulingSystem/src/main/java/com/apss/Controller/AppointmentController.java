package com.apss.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apss.Common.ApiResponse;
import com.apss.Exception.RecordNotFoundException;
import com.apss.Model.Appointment;
import com.apss.Service.AppointmentService;

@RestController
@RequestMapping("/appointmentApi")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

	@Autowired
	public AppointmentService appointmentService;

	/*
	 * this method is going save the details of appointment
	 * 
	 * @param Appointment
	 * 
	 * @return Appointment_Id
	 */
	@PostMapping("/bookAppointment")
	public ResponseEntity<Appointment> registerAppointment(@RequestBody Appointment appointment) {
		Appointment appointment2 = appointmentService.registerAppointment(appointment);

		return new ResponseEntity<Appointment>(appointment2, HttpStatus.OK);
	}

	/*
	 * this method is going save the details of list of appointments
	 * 
	 * @param List<Appointments>
	 * 
	 * @return List<Appointments>
	 */
	@PostMapping("/bookAppointments")
	public ResponseEntity<List<Appointment>> registerAppointments(@RequestBody List<Appointment> appointments) {
		List<Appointment> appointment2 = appointmentService.registerAppointments(appointments);

		return new ResponseEntity<List<Appointment>>(appointment2, HttpStatus.OK);
	}

	/*
	 * this method is used fetch the details all of the appointments available
	 * 
	 * @return List<Appointments>
	 */
	@GetMapping("/getAllAppointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAllAppointments();
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	/*
	 * this method is used fetch the details of the appointment corresponding to
	 * particular id available
	 * 
	 * @param appointmentId
	 * 
	 * @return Appointment
	 */

	@GetMapping("/getAppointmentById/{id}")
	public ResponseEntity<Appointment> getAppointmentDetails(@PathVariable("id") int appointmentId) {
		Appointment appointment = appointmentService.getAppointmentDetails(appointmentId);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	/*
	 * this method is used to reschedule already booked appointments
	 * 
	 * @param Appointment
	 * 
	 * @return Appointment
	 */

	@PutMapping("/rescheduleAppointment")
	public ResponseEntity<ApiResponse> reschduleAppointment(@RequestBody Appointment appointment) {
		ApiResponse apiResponse = appointmentService.rescheduleAppointment(appointment);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	/*
	 * this method is used to cancel the already available appointments
	 * 
	 * @param appointmentId
	 * 
	 * @return Appointment
	 */
	@PutMapping("/cancelAppointment")
	public ResponseEntity<ApiResponse> cancelAppointment(@RequestBody Appointment appointment) {
		ApiResponse apiResponse = appointmentService.cancelAppointment(appointment);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	/*
	 * this method is used to fetch all the appointments corresponding UserId
	 * 
	 * @param UserId
	 * 
	 * @return List<Appointment>
	 */

	@GetMapping("/findAllAppoByUserId/{userId}")
	public ResponseEntity<List<Appointment>> findAllAppoByUserId(@PathVariable("userId") int uid) {
		List<Appointment> appobj = appointmentService.findAllAppoByUserId(uid);
		if (!appobj.isEmpty()) {
			return new ResponseEntity<List<Appointment>>(appobj, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no appointments for you");
		}

	}
}
