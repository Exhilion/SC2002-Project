package OOPProject;

import java.util.List;

public interface AppointmentSlotService {
    List<AppointmentSlot> filterByDoctorID(String doctorID);
    void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked);
}
