package OOPProject;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date);
    List<Appointment> getPendingAppointments(String doctorID);
    void updateAppointmentStatus(String appointmentID, String newStatus);
    List<Appointment> getConfirmedAppointments(String doctorID);
}
