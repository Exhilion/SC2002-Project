package OOPProject;

public class Appointment {
	private AppointmentSlot appointmentSlot;
    private String status;
    private Patient patient;

    public Appointment(AppointmentSlot appointmentSlot, String status, Patient patient) {
        this.appointmentSlot = appointmentSlot;
        this.status = status;
        this.patient = patient;
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

	
}
