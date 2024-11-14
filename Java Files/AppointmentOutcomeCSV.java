package OOPProject;

import java.io.FileWriter;
import java.io.IOException;

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
}
