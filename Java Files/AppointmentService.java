package OOPProject;

import java.util.Date;
import java.util.List;

/**
 * This interface defines the operations available for managing appointments,
 * appointment slots, and appointment outcomes within the system.
 */
public interface AppointmentService {

	/**
	 * Schedules an appointment for a patient with a specific doctor.
	 *
	 * @param patientID    the unique identifier of the patient
	 * @param doctorID     the unique identifier of the doctor
	 * @param dateOfChoice the desired date for the appointment in string format
	 * @param startTime    the start time of the appointment
	 * @param endTime      the end time of the appointment
	 */
	void scheduleAppointment(String patientID, String doctorID, String dateOfChoice, String startTime, String endTime);

	/**
	 * Cancels a specific appointment for a patient.
	 *
	 * @param patientID     the unique identifier of the patient
	 * @param appointmentID the unique identifier of the appointment to be canceled
	 */
	void cancelAppointment(String patientID, String appointmentID);

	/**
	 * Retrieves a list of pending appointments for a given doctor.
	 *
	 * @param doctorID the unique identifier of the doctor
	 * @return a list of pending appointments for the specified doctor
	 */
	List<Appointment> getPendingAppointments(String doctorID);

	/**
	 * Updates the status of a specific appointment.
	 *
	 * @param appointmentID the unique identifier of the appointment
	 * @param newStatus     the new status to set for the appointment
	 */
	void updateAppointmentStatus(String appointmentID, String newStatus);

	/**
	 * Retrieves a list of confirmed appointments for a given doctor.
	 *
	 * @param doctorID the unique identifier of the doctor
	 * @return a list of confirmed appointments for the specified doctor
	 */
	List<Appointment> getConfirmedAppointments(String doctorID);

	/**
	 * Adds a new appointment slot for a doctor.
	 *
	 * @param doctorID  the unique identifier of the doctor
	 * @param startTime the start time of the appointment slot
	 * @param endTime   the end time of the appointment slot
	 * @param date      the date for which the appointment slot is being added
	 * @return true if the slot is successfully added, false otherwise
	 */
	boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date);

	/**
	 * Updates the booking status of an appointment slot.
	 *
	 * @param appointmentSlotID the unique identifier of the appointment slot
	 * @param isBooked          the new booking status (true if booked, false
	 *                          otherwise)
	 */
	void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked);

	/**
	 * Adds the outcome of a specific appointment.
	 *
	 * @param appointmentOutcomeID the unique identifier of the appointment outcome
	 * @param appointment          the appointment related to the outcome
	 * @param medicalRecord        the medical record associated with the
	 *                             appointment
	 * @param consultationNotes    the notes from the consultation
	 * @param status               the status of the prescription related to the
	 *                             appointment
	 */
	void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status);

}
