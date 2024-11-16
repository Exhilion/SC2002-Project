package OOPProject;

public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    @Override
    public void updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean isBooked) {
        AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, isBooked);
        System.out.println("Appointment Slot " + (isBooked ? "Booked" : "Cancelled") + " Successfully!");
    }
}
