package OOPProject;

public class AppointmentOutcome {
    private String appointmentOutcomeID;
    private Appointment appointment;
    private MedicalRecord medicalRecord;
    private String consultationNotes;
    private PrescriptionStatus status;

    public AppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord, String consultationNotes, PrescriptionStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    	this.appointment = appointment;
        this.medicalRecord = medicalRecord;
        this.consultationNotes = consultationNotes;
        this.status = status;
    }

    // Getter and Setter for appointmentOutcomeID
    public String getAppointmentOutcomeID() {
        return appointmentOutcomeID;
    }

    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    }

    // Getter and Setter for appointment
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    // Getter and Setter for medicalRecord
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    // Getter and Setter for consultationNotes
    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    // Getter and Setter for status
    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
    

    public void printDetails() {
        System.out.println("Appointment Outcome Details:");
        System.out.println("Patient Name: " + (appointment != null && appointment.getPatient() != null ? appointment.getPatient().getName() : "N/A"));
        medicalRecord.printPrescriptionDetails();
        System.out.println("Consultation Notes: " + consultationNotes);
        System.out.println("Prescription Status: " + (status != null ? status : "N/A"));
        System.out.println("--------------------------------");
    }
    
  
}
