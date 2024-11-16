package OOPProject;

public interface AppointmentOutcomeService {
	void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status);
}
