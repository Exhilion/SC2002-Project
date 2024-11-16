package OOPProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the AppointmentService interface, providing methods for
 * scheduling, canceling, and managing appointments, appointment slots, and
 * appointment outcomes.
 */
public class AppointmentServiceImpl implements AppointmentService {
	private loadCSVClass load = new loadCSVClass();

	/**
	 * Schedules an appointment for a patient with a specific doctor.
	 *
	 * @param patientID    the ID of the patient
	 * @param doctorID     the ID of the doctor
	 * @param dateOfChoice the desired date for the appointment
	 * @param startTime    the starting time of the appointment
	 * @param endTime      the ending time of the appointment
	 */
	@Override
	public void scheduleAppointment(String patientID, String doctorID, String dateOfChoice, String startTime,
			String endTime) {
		AppointmentCSV.scheduleAppointment(load.getAppointmentSlots(), patientID, doctorID, dateOfChoice, startTime,
				endTime);
	}

	/**
	 * Cancels an existing appointment for a specific patient.
	 *
	 * @param patientID     the ID of the patient
	 * @param appointmentID the ID of the appointment to be canceled
	 */
	@Override
	public void cancelAppointment(String patientID, String appointmentID) {
		AppointmentCSV.cancelAppointment(patientID, appointmentID);
	}

	/**
	 * Retrieves a list of pending appointments for a specific doctor.
	 *
	 * @param doctorID the ID of the doctor
	 * @return a list of pending appointments for the specified doctor
	 */
	@Override
	public List<Appointment> getPendingAppointments(String doctorID) {
		List<Appointment> pendingAppointments = new ArrayList<>();
		for (Appointment appointment : load.getAppointments()) {
			if (appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)
					&& "pending".equalsIgnoreCase(appointment.getStatus())) {
				pendingAppointments.add(appointment);
			}
		}
		return pendingAppointments;
	}

	/**
	 * Updates the status of an existing appointment.
	 *
	 * @param appointmentID the ID of the appointment to update
	 * @param newStatus     the new status for the appointment
	 */
	@Override
	public void updateAppointmentStatus(String appointmentID, String newStatus) {
		new AppointmentCSV().updateAppointmentStatus(appointmentID, newStatus);
	}

	/**
	 * Retrieves a list of confirmed appointments for a specific doctor.
	 *
	 * @param doctorID the ID of the doctor
	 * @return a list of confirmed appointments for the specified doctor
	 */
	@Override
	public List<Appointment> getConfirmedAppointments(String doctorID) {
		String status = "confirmed";
		return Appointment.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID, status);
	}

	/**
	 * Updates the booking status of a specific appointment slot.
	 *
	 * @param appointmentSlotID the ID of the appointment slot
	 * @param isBooked          the new booking status of the appointment slot
	 */
	@Override
	public void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked) {
		AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, isBooked);
		System.out.println("Appointment Slot " + (isBooked ? "Booked" : "Cancelled") + " Successfully!");
	}

	/**
	 * Adds a new appointment slot for a specific doctor.
	 *
	 * @param doctorID  the ID of the doctor
	 * @param startTime the starting time of the appointment slot
	 * @param endTime   the ending time of the appointment slot
	 * @param date      the date of the appointment slot
	 * @return true if the appointment slot was successfully added, false otherwise
	 */
	@Override
	public boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date) {
		AppointmentSlotCSV newSlotCSV = new AppointmentSlotCSV();
		String appointmentSlotID = "AS" + UUID.randomUUID().toString();
		AppointmentSlot newSlot = new AppointmentSlot(appointmentSlotID, startTime, endTime, date, false);
		return newSlotCSV.addAppointmentSlotToCSV(newSlot, doctorID);
	}

	/**
	 * Adds an outcome for a completed appointment.
	 *
	 * @param appointmentOutcomeID the ID of the appointment outcome
	 * @param appointment          the completed appointment
	 * @param medicalRecord        the associated medical record
	 * @param consultationNotes    any consultation notes from the appointment
	 * @param status               the prescription status of the appointment
	 *                             outcome
	 */
	@Override
	public void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status) {
		String appointmentOutcome = "AC" + UUID.randomUUID().toString();
		AppointmentOutcome newOutcome = new AppointmentOutcome(appointmentOutcome, appointment, medicalRecord,
				consultationNotes, status);
		AppointmentOutcomeCSV newOutcomeCSV = new AppointmentOutcomeCSV();
		newOutcomeCSV.addAppointmentOutcome(appointmentOutcome, appointment, medicalRecord, consultationNotes, status);
	}

}
