package OOPProject;

import java.util.ArrayList;
import java.util.List;

public class AppointmentFilter {

    public static List<Appointment> filterAppointmentsByDoctorAndStatus(List<Appointment> appointments, String doctorID, String status) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            // Check if the appointment matches the doctor and the status
            if (appointment.getAppointmentSlot() != null && 
                appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID) && 
                appointment.getStatus().equalsIgnoreCase(status)) {
                filteredAppointments.add(appointment);
            }
        }

        return filteredAppointments;
    }
}
