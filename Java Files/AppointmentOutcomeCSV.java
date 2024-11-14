package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentOutcomeCSV {

	// Add AppointmentOutcome
	public void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status) {
		// Create a new AppointmentOutcome object
		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointmentOutcomeID, appointment, medicalRecord,
				consultationNotes, status);

		// Write the AppointmentOutcome to CSV
		writeAppointmentOutcomeToCSV(appointmentOutcome);
	}

	// Write AppointmentOutcome to CSV
	private void writeAppointmentOutcomeToCSV(AppointmentOutcome appointmentOutcome) {
		try (FileWriter writer = new FileWriter(AppConfig.APPOINTMENT_OUTCOME_FILE_PATH, true)) {

			// Prepare CSV line by extracting details from the AppointmentOutcome object
			String csvLine = appointmentOutcome.getAppointmentOutcomeID() + ","
					+ appointmentOutcome.getAppointment().getAppointmentID() + ","
					+ appointmentOutcome.getMedicalRecord().getRecordID() + ","
					+ appointmentOutcome.getConsultationNotes() + "," + appointmentOutcome.getStatus().toString()
					+ "\n";

			// Write the data to CSV
			writer.write(csvLine);
			System.out.println("Appointment Outcome added successfully.");
		} catch (IOException e) {
			System.err.println("Error writing AppointmentOutcome to CSV: " + e.getMessage());
		}
	}

	// Load AppointmentOutcomes from CSV
	public List<AppointmentOutcome> loadAppointmentOutcomesFromCSV(List<Appointment> appointments,
			List<MedicalRecord> medicalRecords) {
		List<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_OUTCOME_FILE_PATH))) {
			String line;
			reader.readLine(); // Skip the header line if there is one

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");

				// Ensure correct number of columns
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

					// Find Appointment by ID
					Appointment appointment = findAppointmentByID(appointmentID, appointments);
					if (appointment == null) {
						System.err.println("Appointment with ID " + appointmentID + " not found.");
						continue;
					}

					// Find Medical Record by ID
					MedicalRecord medicalRecord = findMedicalRecordByID(medicalRecordID, medicalRecords);
					if (medicalRecord == null) {
						System.err.println("Medical Record with ID " + medicalRecordID + " not found.");
						continue;
					}

					// Create a new AppointmentOutcome instance
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

	// Update the status of a specific AppointmentOutcome by its
	// appointmentOutcomeID
	public void updateAppointmentOutcomeStatus(List<AppointmentOutcome> appointmentOutcomes,
			String appointmentOutcomeID, PrescriptionStatus newStatus) {
		// Find the AppointmentOutcome to update
		AppointmentOutcome outcomeToUpdate = appointmentOutcomes.stream()
				.filter(appointmentOutcome -> appointmentOutcome.getAppointmentOutcomeID().equals(appointmentOutcomeID))
				.findFirst().orElse(null);

		if (outcomeToUpdate != null) {
			// Update the status
			outcomeToUpdate.setStatus(newStatus);
			System.out.println("Appointment Outcome status updated successfully.");

			saveAppointmentOutcomeToCSV(outcomeToUpdate);
		} else {
			System.err.println("Appointment Outcome with ID " + appointmentOutcomeID + " not found.");
		}
	}

	// save the updated AppointmentOutcome back to the CSV
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

	private Appointment findAppointmentByID(String appointmentID, List<Appointment> appointments) {
		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentID().equalsIgnoreCase(appointmentID)) {
				return appointment;
			}
		}
		return null;
	}

	private MedicalRecord findMedicalRecordByID(String recordID, List<MedicalRecord> medicalRecords) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getRecordID().equalsIgnoreCase(recordID)) {
				return medicalRecord;
			}
		}
		return null;
	}
}
