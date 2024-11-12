package OOPProject;

import java.util.List;

public interface PatientAppointmentManagement {
	List<AppointmentOutcome> viewAppointmentOutcome(Patient patient);
	void cancelAppointment(Patient patient, Appointment appointment);
	void scheduleAppointment(Patient patient, AppointmentSlot slot);
	Appointment rescheduleAppointment(Patient patient, AppointmentSlot appointment, AppointmentSlot newSlot);
	List<AppointmentSlot> viewAvailableAppointment();
}
