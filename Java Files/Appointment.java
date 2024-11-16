package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The class is an appointment for a patient with details about the appointment slot,
 * status, and associated patient.
 * <p>
 * This class provides functionality for creating, filtering, and printing appointment details,
 * along with generating unique appointment IDs. It also includes methods for managing 
 * appointments by doctor and status.
 * </p>
 */
public class Appointment {
	private String appointmentID;
	private AppointmentSlot appointmentSlot;
	private String status;
	private Patient patient;

	// Constructor
	/**
	 * Constructs an Appointment object with the specified appointment ID, appointment slot,
     * status, and patient.
     * 
	 * @param appointmentID The unique ID for the appointment
	 * @param appointmentSlot The appointment slot associated with the appointment
	 * @param status The current status of the appointment (e.g. Pending, Confirmed)
	 * @param patient The patient associated with the appointment
	 */
	public Appointment(String appointmentID, AppointmentSlot appointmentSlot, String status, Patient patient) {
		this.appointmentID = appointmentID;
		this.appointmentSlot = appointmentSlot;
		this.status = status;
		this.patient = patient;
	}

	// Generate a unique AppointmentID using UUID
	/**
	 * Generates a unique appointment ID using UUID
	 * 
	 * @return the generated unique appointment ID
	 */
	private String generateUniqueID() {
		return UUID.randomUUID().toString();
	}

	// Getters and Setters
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
	 * Sets the appointment slot for the appointment
	 * 
	 * @param appointmentSlot The new appointment slot to be set
	 */
	public void setAppointmentSlot(AppointmentSlot appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}

	/**
	 * Gets the status of the appointment
	 * 
	 * @return the status of the appointment
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the appointment
	 * 
	 * @param status The new status to be set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the patient associated with the appointment.
	 * 
	 * @return the patient associated with the appointment
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the patient for the appointment.
	 * 
	 * @param patient The new patient to be set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Prints the details of the appointment, including the patient information,
	 * doctor, appointment slot times, and booking status.
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
	 * Filters and returns a list of scheduled appointments for a given patient ID that are either "Pending"
	 * or "Confirmed".
	 * 
	 * @param slots The list of appointments to filter
	 * @param patientID The patient ID to filter by
	 * @return a list of appointments for the specified patient ID with "Pending" or "Confirmed" status
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
	 * Returns a string representation of the appointment, including appointment ID, slot ID, status, and patient ID.
	 * 
	 * @return the string representation of the appointment
	 */
	@Override
	public String toString() {
		return "AppointmentID: " + appointmentID + ", SlotID: "
				+ (appointmentSlot != null ? appointmentSlot.getAppointmentSlotID() : "null") + ", Status: " + status
				+ ", PatientID: " + (patient != null ? patient.getHospitalId() : "null");
	}
	
	/**
	 * Filters and returns a list of appointments by doctor ID and appointment status.
	 * 
	 * @param appointments The list of appointments to filter
	 * @param doctorID The doctor ID to filter by
	 * @param status The status to filter by
	 * @return a list of appointments matching the doctor ID and status
	 */
	   public static List<Appointment> filterAppointmentsByDoctorAndStatus(List<Appointment> appointments, String doctorID, String status) {
	        List<Appointment> filteredAppointments = new ArrayList<>();

	        for (Appointment appointment : appointments) {
	            // Check if the appointment matches the doctor and the status
	            if (appointment.getAppointmentSlot() != null && 
	                appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID) && 
	                appointment.getStatus().equalsIgnoreCase(status)) {
	                filteredAppointments.add(appointment);
	            }
	        }

	        return filteredAppointments;
	    }

}
