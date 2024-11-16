package OOPProject;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

	// Appointment functions
	void scheduleAppointment(String patientID, String doctorID, String dateOfChoice, String startTime, String endTime);

	void cancelAppointment(String patientID, String appointmentID);

	List<Appointment> getPendingAppointments(String doctorID);

	void updateAppointmentStatus(String appointmentID, String newStatus);

	List<Appointment> getConfirmedAppointments(String doctorID);

	// Appointment Slot functions
	boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date);

	void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked);

	// Appointment Outcome functions
	void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status);

}
