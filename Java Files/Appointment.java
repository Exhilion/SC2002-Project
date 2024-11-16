package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Appointment {
	private String appointmentID;
	private AppointmentSlot appointmentSlot;
	private String status;
	private Patient patient;

	// Constructor
	public Appointment(String appointmentID, AppointmentSlot appointmentSlot, String status, Patient patient) {
		this.appointmentID = appointmentID;
		this.appointmentSlot = appointmentSlot;
		this.status = status;
		this.patient = patient;
	}

	// Generate a unique AppointmentID using UUID
	private String generateUniqueID() {
		return UUID.randomUUID().toString();
	}

	// Getters and Setters
	public String getAppointmentID() {
		return appointmentID;
	}

	public AppointmentSlot getAppointmentSlot() {
		return appointmentSlot;
	}

	public void setAppointmentSlot(AppointmentSlot appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

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

	@Override
	public String toString() {
		return "AppointmentID: " + appointmentID + ", SlotID: "
				+ (appointmentSlot != null ? appointmentSlot.getAppointmentSlotID() : "null") + ", Status: " + status
				+ ", PatientID: " + (patient != null ? patient.getHospitalId() : "null");
	}
	
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
