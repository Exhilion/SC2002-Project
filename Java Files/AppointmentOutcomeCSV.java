package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle the operations related to AppointmentOutcome, including
 * adding, writing to CSV, loading from CSV, updating the status, and saving
 * changes back to CSV.
 */
public class AppointmentOutcomeCSV {
	/**
	 * Adds a new AppointmentOutcome and writes it to the CSV file.
	 * 
	 * @param appointmentOutcomeID the unique ID for the AppointmentOutcome.
	 * @param appointment          the Appointment associated with the outcome.
	 * @param medicalRecord        the MedicalRecord associated with the outcome.
	 * @param consultationNotes    the consultation notes for the appointment.
	 * @param status               the prescription status of the outcome.
	 */
	public void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status) {

		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointmentOutcomeID, appointment, medicalRecord,
				consultationNotes, status);

		writeAppointmentOutcomeToCSV(appointmentOutcome);
	}

	/**
	 * Writes an AppointmentOutcome to the CSV file.
	 * 
	 * @param appointmentOutcome the AppointmentOutcome object to be written to the
	 *                           CSV file.
	 */
	private void writeAppointmentOutcomeToCSV(AppointmentOutcome appointmentOutcome) {
		try (FileWriter writer = new FileWriter(AppConfig.APPOINTMENT_OUTCOME_FILE_PATH, true)) {

			String csvLine = appointmentOutcome.getAppointmentOutcomeID() + ","
					+ appointmentOutcome.getAppointment().getAppointmentID() + ","
					+ appointmentOutcome.getMedicalRecord().getRecordID() + ","
					+ appointmentOutcome.getConsultationNotes() + "," + appointmentOutcome.getStatus().toString()
					+ "\n";

			writer.write(csvLine);
			System.out.println("Appointment Outcome added successfully.");
		} catch (IOException e) {
			System.err.println("Error writing AppointmentOutcome to CSV: " + e.getMessage());
		}
	}

	/**
	 * Loads all AppointmentOutcomes from the CSV file.
	 * 
	 * @param appointments   the list of appointments to match against Appointment
	 *                       IDs.
	 * @param medicalRecords the list of medical records to match against Record
	 *                       IDs.
	 * @return a list of AppointmentOutcome objects loaded from the CSV file.
	 */
	public List<AppointmentOutcome> loadAppointmentOutcomesFromCSV(List<Appointment> appointments,
			List<MedicalRecord> medicalRecords) {
		List<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_OUTCOME_FILE_PATH))) {
			String line;
			reader.readLine();

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");

				if (parts.length != 5) {
					System.err.println("Invalid line format: " + line);
					continue;
				}

				try {
					String appointmentOutcomeID = parts[0].trim();
					String appointmentID = parts[1].trim();
					String medicalRecordID = parts[2].trim();
					String consultationNotes = parts[3].trim();
					PrescriptionStatus status = PrescriptionStatus.valueOf(parts[4].trim());

					Appointment appointment = findAppointmentByID(appointmentID, appointments);
					if (appointment == null) {
						System.err.println("Appointment with ID " + appointmentID + " not found.");
						continue;
					}

					MedicalRecord medicalRecord = findMedicalRecordByID(medicalRecordID, medicalRecords);
					if (medicalRecord == null) {
						System.err.println("Medical Record with ID " + medicalRecordID + " not found.");
						continue;
					}

					AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointmentOutcomeID, appointment,
							medicalRecord, consultationNotes, status);

					appointmentOutcomes.add(appointmentOutcome);
				} catch (IllegalArgumentException e) {
					System.err.println("Error parsing line: " + line + " - " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading AppointmentOutcomes from CSV: " + e.getMessage());
		}

		return appointmentOutcomes;
	}

	/**
	 * Updates the status of a specific AppointmentOutcome based on its ID.
	 * 
	 * @param appointmentOutcomes  the list of AppointmentOutcome objects to update.
	 * @param appointmentOutcomeID the ID of the AppointmentOutcome to update.
	 * @param newStatus            the new status to set for the AppointmentOutcome.
	 */
	public void updateAppointmentOutcomeStatus(List<AppointmentOutcome> appointmentOutcomes,
			String appointmentOutcomeID, PrescriptionStatus newStatus) {

		AppointmentOutcome outcomeToUpdate = appointmentOutcomes.stream()
				.filter(appointmentOutcome -> appointmentOutcome.getAppointmentOutcomeID().equals(appointmentOutcomeID))
				.findFirst().orElse(null);

		if (outcomeToUpdate != null) {

			outcomeToUpdate.setStatus(newStatus);
			System.out.println("Appointment Outcome status updated successfully.");

			saveAppointmentOutcomeToCSV(outcomeToUpdate);
		} else {
			System.err.println("Appointment Outcome with ID " + appointmentOutcomeID + " not found.");
		}
	}

	/**
	 * Saves the updated AppointmentOutcome back to the CSV file.
	 * 
	 * @param updatedOutcome the AppointmentOutcome object with the updated status.
	 */
	private void saveAppointmentOutcomeToCSV(AppointmentOutcome updatedOutcome) {
		try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_OUTCOME_FILE_PATH))) {

			List<String> lines = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			for (int i = 1; i < lines.size(); i++) {
				String[] parts = lines.get(i).split(",");
				if (parts[0].equals(updatedOutcome.getAppointmentOutcomeID())) {
					lines.set(i, updatedOutcome.getAppointmentOutcomeID() + ","
							+ updatedOutcome.getAppointment().getAppointmentID() + ","
							+ updatedOutcome.getMedicalRecord().getRecordID() + ","
							+ updatedOutcome.getConsultationNotes() + "," + updatedOutcome.getStatus().toString());
					break;
				}
			}

			try (FileWriter writer = new FileWriter(AppConfig.APPOINTMENT_OUTCOME_FILE_PATH)) {
				for (String updatedLine : lines) {
					writer.write(updatedLine + "\n");
				}
			}

			System.out.println("Appointment Outcome updated in CSV successfully.");
		} catch (IOException e) {
			System.err.println("Error updating AppointmentOutcome in CSV: " + e.getMessage());
		}
	}

	/**
	 * Finds an Appointment by its ID from a list of appointments.
	 * 
	 * @param appointmentID the ID of the Appointment to search for.
	 * @param appointments  the list of Appointment objects to search.
	 * @return the Appointment object if found, or null if not found.
	 */
	private Appointment findAppointmentByID(String appointmentID, List<Appointment> appointments) {
		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentID().equalsIgnoreCase(appointmentID)) {
				return appointment;
			}
		}
		return null;
	}

	/**
	 * Finds a MedicalRecord by its ID from a list of medical records.
	 * 
	 * @param recordID       the ID of the MedicalRecord to search for.
	 * @param medicalRecords the list of MedicalRecord objects to search.
	 * @return the MedicalRecord object if found, or null if not found.
	 */
	private MedicalRecord findMedicalRecordByID(String recordID, List<MedicalRecord> medicalRecords) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getRecordID().equalsIgnoreCase(recordID)) {
				return medicalRecord;
			}
		}
		return null;
	}
}
