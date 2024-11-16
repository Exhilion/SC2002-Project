package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Appointment class represents a scheduled appointment for a patient with a
 * specific doctor at a certain time slot.
 */
public class Appointment {
	private String appointmentID;
	private AppointmentSlot appointmentSlot;
	private String status;
	private Patient patient;

	/**
	 * Constructor for the Appointment class.
	 * 
	 * @param appointmentID   the unique identifier for the appointment
	 * @param appointmentSlot the appointment slot for the appointment
	 * @param status          the status of the appointment (e.g., Pending,
	 *                        Confirmed)
	 * @param patient         the patient associated with the appointment
	 */
	public Appointment(String appointmentID, AppointmentSlot appointmentSlot, String status, Patient patient) {
		this.appointmentID = appointmentID;
		this.appointmentSlot = appointmentSlot;
		this.status = status;
		this.patient = patient;
	}

	/**
	 * Generates a unique appointment ID using UUID.
	 * 
	 * @return a unique appointment ID
	 */
	private String generateUniqueID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Gets the appointment ID.
	 * 
	 * @return the appointment ID
	 */
	public String getAppointmentID() {
		return appointmentID;
	}

	/**
	 * Gets the appointment slot associated with the appointment.
	 * 
	 * @return the appointment slot
	 */
	public AppointmentSlot getAppointmentSlot() {
		return appointmentSlot;
	}

	/**
	 * Sets the appointment slot for the appointment.
	 * 
	 * @param appointmentSlot the appointment slot to set
	 */
	public void setAppointmentSlot(AppointmentSlot appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}

	/**
	 * Gets the status of the appointment.
	 * 
	 * @return the status of the appointment
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the appointment.
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the patient associated with the appointment.
	 * 
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the patient for the appointment.
	 * 
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Prints the details of the appointment.
	 */
	public void printAppointmentDetails() {
		System.out.println("Patient: " + patient.getName());
		System.out.println("Appointment Slot:");
		System.out.println("   Doctor: " + appointmentSlot.getDoctor().getDoctorName());
		System.out.println("   Start Time: " + appointmentSlot.getStartTime());
		System.out.println("   End Time: " + appointmentSlot.getEndTime());

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = dateFormat.format(appointmentSlot.getDate());
		System.out.println("   Date: " + formattedDate);
		System.out.println("   Is Booked: " + appointmentSlot.isBooked());

	}

	/**
	 * Filters and returns a list of appointments that are scheduled (pending or
	 * confirmed) for the given patient ID.
	 * 
	 * @param slots     the list of appointments to filter
	 * @param patientID the patient ID to filter by
	 * @return a list of scheduled appointments for the given patient
	 */

	public static List<Appointment> filterScheduledAppointment(List<Appointment> slots, String patientID) {
		List<Appointment> scheduledAppointments = new ArrayList<>();
		String normalizedPatientID = patientID.trim().toLowerCase(); // Normalize the patientID passed in

		for (Appointment slot : slots) {
			if (slot.getPatient() != null) {
				String slotPatientID = slot.getPatient().getHospitalId().trim().toLowerCase();
				if (slotPatientID.equals(normalizedPatientID) && (slot.getStatus().equalsIgnoreCase("Pending")
						|| slot.getStatus().equalsIgnoreCase("Confirmed"))) {
					System.out
							.println("Match found for Patient ID: " + patientID + " with Status: " + slot.getStatus());
					scheduledAppointments.add(slot);
				}
			} else {
				System.out.println("Appointment with ID: " + slot.getAppointmentID() + " has no linked Patient.");
			}
		}
		return scheduledAppointments;
	}

	/**
	 * Filters appointments by the given doctor ID.
	 * 
	 * @param appointments the list of appointments to filter
	 * @param doctorID     the doctor ID to filter by
	 * @return a list of appointments for the given doctor
	 */
	public static List<Appointment> filterAppointmentsByDoctor(List<Appointment> appointments, String doctorID) {
		List<Appointment> filteredAppointments = new ArrayList<>();

		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentSlot() != null
					&& appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)) {
				filteredAppointments.add(appointment);
			}
		}

		return filteredAppointments;
	}

	/**
	 * Provides a string representation of the appointment object.
	 * 
	 * @return a string representing the appointment details
	 */
	@Override
	public String toString() {
		return "AppointmentID: " + appointmentID + ", SlotID: "
				+ (appointmentSlot != null ? appointmentSlot.getAppointmentSlotID() : "null") + ", Status: " + status
				+ ", PatientID: " + (patient != null ? patient.getHospitalId() : "null");
	}

	/**
	 * Filters appointments by the given doctor ID and status.
	 * 
	 * @param appointments the list of appointments to filter
	 * @param doctorID     the doctor ID to filter by
	 * @param status       the status to filter by
	 * @return a list of appointments for the given doctor and status
	 */
	public static List<Appointment> filterAppointmentsByDoctorAndStatus(List<Appointment> appointments, String doctorID,
			String status) {
		List<Appointment> filteredAppointments = new ArrayList<>();

		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentSlot() != null
					&& appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)
					&& appointment.getStatus().equalsIgnoreCase(status)) {
				filteredAppointments.add(appointment);
			}
		}

		return filteredAppointments;
	}

}
