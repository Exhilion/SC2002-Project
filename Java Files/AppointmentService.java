package OOPProject;

import java.util.Date;
import java.util.List;

/**
 * The {@code AppointmentService} interface defines the contract for managing
 * appointments, appointment slots, and appointment outcomes. It includes 
 * methods for scheduling, canceling, retrieving appointments, and handling
 * appointment slots and outcomes.
 */
public interface AppointmentService {

	// Appointment functions
	/**
     * Schedules a new appointment for a patient with a specified doctor.
     *
     * @param patientID the ID of the patient scheduling the appointment
     * @param doctorID the ID of the doctor for the appointment
     * @param dateOfChoice the preferred date for the appointment (format: yyyy-MM-dd)
     * @param startTime the start time of the appointment (format: HH:mm)
     * @param endTime the end time of the appointment (format: HH:mm)
     */
	void scheduleAppointment(String patientID, String doctorID, String dateOfChoice, String startTime, String endTime);

	/**
     * Cancels an existing appointment for a patient.
     *
     * @param patientID the ID of the patient canceling the appointment
     * @param appointmentID the ID of the appointment to cancel
     */
	void cancelAppointment(String patientID, String appointmentID);

	/**
     * Retrieves a list of pending appointments for a specified doctor.
     *
     * @param doctorID the ID of the doctor
     * @return a list of pending {@link Appointment} objects
     */
	List<Appointment> getPendingAppointments(String doctorID);

	/**
     * Updates the status of a specified appointment.
     *
     * @param appointmentID the ID of the appointment to update
     * @param newStatus the new status to set for the appointment
     */
	void updateAppointmentStatus(String appointmentID, String newStatus);

	/**
     * Retrieves a list of confirmed appointments for a specified doctor.
     *
     * @param doctorID the ID of the doctor
     * @return a list of confirmed {@link Appointment} objects
     */
	List<Appointment> getConfirmedAppointments(String doctorID);

	// Appointment Slot functions
	/**
     * Adds a new appointment slot for a doctor.
     *
     * @param doctorID the ID of the doctor
     * @param startTime the start time of the slot (format: HH:mm)
     * @param endTime the end time of the slot (format: HH:mm)
     * @param date the date of the slot
     * @return {@code true} if the slot is added successfully, {@code false} otherwise
     */
	boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date);

	/**
     * Updates the booking status of a specific appointment slot.
     *
     * @param appointmentSlotID the ID of the appointment slot
     * @param isBooked {@code true} if the slot is booked, {@code false} otherwise
     */
	void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked);

	// Appointment Outcome functions
	/**
     * Adds a new appointment outcome for a completed appointment.
     *
     * @param appointmentOutcomeID the unique ID for the appointment outcome
     * @param appointment the associated {@link Appointment} object
     * @param medicalRecord the associated {@link MedicalRecord} object
     * @param consultationNotes the notes recorded during the consultation
     * @param status the {@link PrescriptionStatus} of the appointment outcome
     */
	void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status);

}