package OOPProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link AppointmentService} interface.
 * This class provides methods for managing appointments, appointment slots, and appointment outcomes.
 */
public class AppointmentServiceImpl implements AppointmentService {
	private loadCSVClass load = new loadCSVClass();

	// Appointment functions
	/**
     * Schedules a new appointment for a patient with a doctor at a specified time slot.
     *
     * @param patientID   the ID of the patient
     * @param doctorID    the ID of the doctor
     * @param dateOfChoice the date of the appointment in the format "yyyy-MM-dd"
     * @param startTime   the start time of the appointment in HH:mm format
     * @param endTime     the end time of the appointment in HH:mm format
     */
	@Override
	public void scheduleAppointment(String patientID, String doctorID, String dateOfChoice, String startTime,
			String endTime) {
		AppointmentCSV.scheduleAppointment(load.getAppointmentSlots(), patientID, doctorID, dateOfChoice, startTime,
				endTime);
	}

	/**
     * Cancels an appointment for a patient based on the appointment ID.
     *
     * @param patientID      the ID of the patient
     * @param appointmentID  the ID of the appointment to cancel
     */
	@Override
	public void cancelAppointment(String patientID, String appointmentID) {
		AppointmentCSV.cancelAppointment(patientID, appointmentID);
	}

	/**
     * Retrieves all pending appointments for a specific doctor.
     *
     * @param doctorID  the ID of the doctor
     * @return a list of {@link Appointment} objects with "pending" status
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
     * Updates the status of an appointment.
     *
     * @param appointmentID  the ID of the appointment to update
     * @param newStatus      the new status of the appointment (e.g., "confirmed", "cancelled")
     */
	@Override
	public void updateAppointmentStatus(String appointmentID, String newStatus) {
		new AppointmentCSV().updateAppointmentStatus(appointmentID, newStatus);
	}

	/**
     * Retrieves all confirmed appointments for a specific doctor.
     *
     * @param doctorID  the ID of the doctor
     * @return a list of {@link Appointment} objects with "confirmed" status
     */
	@Override
	public List<Appointment> getConfirmedAppointments(String doctorID) {
		String status = "confirmed";
		return Appointment.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID, status);
	}

	// Appointment Slot functions
	/**
     * Updates the booking status of an appointment slot.
     *
     * @param appointmentSlotID  the ID of the appointment slot to update
     * @param isBooked           {@code true} to mark the slot as booked, {@code false} to mark it as available
     */
	@Override
	public void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked) {
		AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, isBooked);
		System.out.println("Appointment Slot " + (isBooked ? "Booked" : "Cancelled") + " Successfully!");
	}

	/**
     * Adds a new appointment slot for a specific doctor.
     *
     * @param doctorID    the ID of the doctor
     * @param startTime   the start time of the slot in HH:mm format
     * @param endTime     the end time of the slot in HH:mm format
     * @param date        the date of the slot
     * @return {@code true} if the slot was added successfully, {@code false} otherwise
     */
	@Override
	public boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date) {
		AppointmentSlotCSV newSlotCSV = new AppointmentSlotCSV();
		String appointmentSlotID = "AS" + UUID.randomUUID().toString();
		AppointmentSlot newSlot = new AppointmentSlot(appointmentSlotID, startTime, endTime, date, false);
		return newSlotCSV.addAppointmentSlotToCSV(newSlot, doctorID);
	}

	// Appointment outcome functions
	/**
     * Adds a new appointment outcome after a consultation.
     *
     * @param appointmentOutcomeID  the ID of the appointment outcome
     * @param appointment           the associated {@link Appointment}
     * @param medicalRecord         the {@link MedicalRecord} associated with the appointment
     * @param consultationNotes     the consultation notes
     * @param status                the {@link PrescriptionStatus} of the outcome
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
