package OOPProject;

import java.util.UUID;

public class Appointment {
    private String appointmentID;
    private AppointmentSlot appointmentSlot;
    private String status;
    private Patient patient;

    // Constructor
    public Appointment(AppointmentSlot appointmentSlot, String status, Patient patient) {
        this.appointmentID = generateUniqueID();
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

    // Override toString for better readability
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID='" + appointmentID + '\'' +
                ", appointmentSlot=" + appointmentSlot +
                ", status='" + status + '\'' +
                ", patient=" + patient +
                '}';
    }
}
