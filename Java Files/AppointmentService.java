package OOPProject;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date);
    List<Appointment> getPendingAppointments(String doctorID);
    void updateAppointmentStatus(String appointmentID, String newStatus);
    List<Appointment> getConfirmedAppointments(String doctorID);
    void addAppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord,
			String consultationNotes, PrescriptionStatus status);
    void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked);
}
