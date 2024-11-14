package OOPProject;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Appointment {
    private String appointmentID;
    private AppointmentSlot appointmentSlot;
    private String status;
    private Patient patient;

    // Constructor
    public Appointment(String appointmentID, AppointmentSlot appointmentSlot, String status, Patient patient) {
        this.appointmentID = appointmentID;
        this.appointmentSlot = appointmentSlot;
        this.status = status;
        this.patient = patient;
    }

    // Generate a unique AppointmentID using UUID
    private String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getAppointmentID() {
        return appointmentID;
    }

    public AppointmentSlot getAppointmentSlot() {
        return appointmentSlot;
    }

    public void setAppointmentSlot(AppointmentSlot appointmentSlot) {
        this.appointmentSlot = appointmentSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public void printAppointmentDetails() {
        System.out.println("Patient: " + patient.getName());
        System.out.println("Appointment Slot:");
        System.out.println("   Doctor: " + appointmentSlot.getDoctor().getDoctorName());
        System.out.println("   Start Time: " + appointmentSlot.getStartTime());
        System.out.println("   End Time: " + appointmentSlot.getEndTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(appointmentSlot.getDate());
        System.out.println("   Date: " + formattedDate);
        System.out.println("   Is Booked: " + appointmentSlot.isBooked());

    }


}
