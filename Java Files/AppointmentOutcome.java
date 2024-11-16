package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the outcome of an appointment, including consultation notes,
 * medical record information, and prescription status.
 */
public class AppointmentOutcome {
	private String appointmentOutcomeID;
	private Appointment appointment;
	private MedicalRecord medicalRecord;
	private String consultationNotes;
	private PrescriptionStatus status;

	/**
	 * Constructor to initialize the AppointmentOutcome object with provided
	 * details.
	 * 
	 * @param appointmentOutcomeID Unique ID for the appointment outcome.
	 * @param appointment          The associated appointment.
	 * @param medicalRecord        The medical record associated with the
	 *                             appointment.
	 * @param consultationNotes    The consultation notes for the appointment.
	 * @param status               The prescription status.
	 */
	public AppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status) {
		this.appointmentOutcomeID = appointmentOutcomeID;
		this.appointment = appointment;
		this.medicalRecord = medicalRecord;
		this.consultationNotes = consultationNotes;
		this.status = status;
	}

	/**
	 * Gets the appointment outcome ID.
	 * 
	 * @return the appointment outcome ID.
	 */
	public String getAppointmentOutcomeID() {
		return appointmentOutcomeID;
	}

	/**
	 * Gets the associated appointment.
	 * 
	 * @return the appointment.
	 */
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	/**
	 * Gets the associated medical record.
	 * 
	 * @return the medical record.
	 */
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * Gets the consultation notes for the appointment.
	 * 
	 * @return the consultation notes.
	 */
	public String getConsultationNotes() {
		return consultationNotes;
	}

	public void setConsultationNotes(String consultationNotes) {
		this.consultationNotes = consultationNotes;
	}

	/**
	 * Gets the prescription status.
	 * 
	 * @return the prescription status.
	 */
	public PrescriptionStatus getStatus() {
		return status;
	}

	public void setStatus(PrescriptionStatus status) {
		this.status = status;
	}

	/**
	 * Prints the details of the appointment outcome, including the patient's name,
	 * medical record prescription details, consultation notes, and prescription
	 * status.
	 */
	public void printDetails() {
		System.out.println("Appointment Outcome Details:");
		System.out.println("Patient Name: "
				+ (appointment != null && appointment.getPatient() != null ? appointment.getPatient().getName()
						: "N/A"));
		medicalRecord.printPrescriptionDetails();
		System.out.println("Consultation Notes: " + consultationNotes);
		System.out.println("Prescription Status: " + (status != null ? status : "N/A"));
		System.out.println("--------------------------------");
	}
	 /**
     * Prints detailed information about the appointment and its outcome if the
     * appointment is completed.
     */
	public void printAppointmentDetails() {
		if (appointment != null && appointment.getStatus().equalsIgnoreCase("completed")) {
			System.out.println("Appointment Outcome Details:");
			System.out.println("Appointment ID: " + appointment.getAppointmentID());
			System.out.println("Patient ID: " + appointment.getPatient().getHospitalId());
			System.out.println("Patient Name: " + appointment.getPatient().getName());
			System.out.println("Appointment Status: " + appointment.getStatus());
			System.out.println("Appointment Slot:");
			System.out.println("   Doctor ID: " + appointment.getAppointmentSlot().getDoctor().getHospitalId());
			System.out.println("   Doctor Name: " + appointment.getAppointmentSlot().getDoctor().getDoctorName());
			System.out.println("   Start Time: " + appointment.getAppointmentSlot().getStartTime());
			System.out.println("   End Time: " + appointment.getAppointmentSlot().getEndTime());

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = dateFormat.format(appointment.getAppointmentSlot().getDate());
			System.out.println("   Date: " + formattedDate);
			System.out.println("   Is Booked: " + appointment.getAppointmentSlot().isBooked());


			medicalRecord.printPrescriptionDetails();
			System.out.println("Consultation Notes: " + consultationNotes);
			System.out.println("Prescription Status: " + (status != null ? status : "N/A"));
			System.out.println("--------------------------------");
		} else {
			System.out.println("Appointment is not completed, no details to display.");
		}
	}
	 /**
     * Views appointment outcome records for a given patient, grouped by prescription status.
     * 
     * @param slots The list of AppointmentOutcome objects.
     * @param patientID The patient ID to filter the records.
     */
	public static void viewAppointmentOutcomeRecords(List<AppointmentOutcome> slots, String patientID) {
		String normalizedPatientID = patientID.trim().toLowerCase();
		Map<PrescriptionStatus, List<AppointmentOutcome>> groupedOutcomes = new HashMap<>();

		for (AppointmentOutcome slot : slots) {
			if (slot.getAppointment().getPatient() != null) {
				String slotPatientID = slot.getAppointment().getPatient().getHospitalId().trim().toLowerCase();
				if (slotPatientID.equals(normalizedPatientID)) {

					groupedOutcomes.computeIfAbsent(slot.getStatus(), k -> new ArrayList<>()).add(slot);
				}
			} else {
				System.out.println(
						"Appointment with ID: " + slot.getAppointment().getAppointmentID() + " has no linked Patient.");
			}
		}

		for (Map.Entry<PrescriptionStatus, List<AppointmentOutcome>> entry : groupedOutcomes.entrySet()) {
			System.out.println("\nStatus: " + entry.getKey());
			for (AppointmentOutcome outcome : entry.getValue()) {
				System.out.println(outcome);
			}
		}
	}

	public static List<AppointmentOutcome> filterByPendingStatus(List<AppointmentOutcome> allOutcomes) {
		List<AppointmentOutcome> pendingOutcomes = new ArrayList<>();
		for (AppointmentOutcome outcome : allOutcomes) {
			if (outcome.getStatus() == PrescriptionStatus.Pending) {
				pendingOutcomes.add(outcome);
			}
		}
		return pendingOutcomes;
	}

	public static List<AppointmentOutcome> filterAppointmentOutcomesByDoctorIDs(List<AppointmentOutcome> allOutcomes,
			String doctorid) {
		List<AppointmentOutcome> filteredOutcomes = new ArrayList<>();
		for (AppointmentOutcome outcome : filteredOutcomes) {
			if (outcome.getAppointment().getAppointmentSlot().getDoctor().getHospitalId().equalsIgnoreCase(doctorid)) {
				filteredOutcomes.add(outcome);
			}
		}
		return filteredOutcomes;
	}

	@Override
	public String toString() {

		String appointmentID = (appointment != null) ? appointment.getAppointmentID() : "N/A";
		String patientID = (appointment != null && appointment.getPatient() != null)
				? appointment.getPatient().getHospitalId()
				: "N/A";

		String medicalRecordID = (medicalRecord != null) ? medicalRecord.getRecordID() : "N/A";

		return "AppointmentOutcomeID: " + appointmentOutcomeID + "\nAppointmentID: " + appointmentID
				+ "\nMedicalRecordID: " + medicalRecordID + "\nConsultationNotes: " + consultationNotes
				+ "\nPrescriptionStatus: " + status;
	}

}
