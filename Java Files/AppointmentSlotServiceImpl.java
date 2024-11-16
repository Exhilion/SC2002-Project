package OOPProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSlotServiceImpl implements AppointmentSlotService {
    private List<AppointmentSlot> appointmentSlots = new ArrayList<>();

    @Override
    public List<AppointmentSlot> filterByDoctorID(String doctorID) {
        List<AppointmentSlot> filteredSlots = new ArrayList<>();
        for (AppointmentSlot slot : appointmentSlots) {
            if (slot.getDoctor().getHospitalId().equals(doctorID)) {
                filteredSlots.add(slot);
            }
        }
        return filteredSlots;
    }


    @Override
    public void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked) {
        for (AppointmentSlot slot : appointmentSlots) {
            if (slot.getAppointmentSlotID().equals(appointmentSlotID)) {
                slot.setBooked(isBooked);
            }
        }
    }
}
