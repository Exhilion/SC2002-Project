package OOPProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AppointmentServiceImpl implements AppointmentService {
    private loadCSVClass load = new loadCSVClass();

    @Override
    public boolean addAppointmentSlot(String doctorID, String startTime, String endTime, Date date) {
        AppointmentSlotCSV newSlotCSV = new AppointmentSlotCSV();
        String appointmentSlotID = "AS" + UUID.randomUUID().toString();
        AppointmentSlot newSlot = new AppointmentSlot(appointmentSlotID, startTime, endTime, date, false);
        return newSlotCSV.addAppointmentSlotToCSV(newSlot, doctorID);
    }

    @Override
    public List<Appointment> getPendingAppointments(String doctorID) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : load.getAppointments()) {
            if (appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)
                    && "pending".equalsIgnoreCase(appointment.getStatus())) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    @Override
    public void updateAppointmentStatus(String appointmentID, String newStatus) {
        new AppointmentCSV().updateAppointmentStatus(appointmentID, newStatus);
    }

    @Override
    public List<Appointment> getConfirmedAppointments(String doctorID) {
        String status = "confirmed";
        return Appointment.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID, status);
    }
}
