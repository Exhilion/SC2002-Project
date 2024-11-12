package OOPProject;

import java.util.List;

public interface DoctorAppointmentManagement {
    void setAvailableDates(String doctorID, List<AppointmentSlot> schedule);
    void acceptAppointmentRequest(String appointmentID);
    void declineAppointmentRequest(String appointmentID);
    List<Appointment> viewAppointmentSchedule(String doctorID);
}
