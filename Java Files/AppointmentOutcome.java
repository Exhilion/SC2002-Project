package OOPProject;

public class AppointmentOutcome {
    private Appointment appointment;
    private String typeOfService;
    private MedicalRecord medicalRecord;
    private String consultationNotes;
    private PrescriptionStatus status;

    public AppointmentOutcome(Appointment appointment, String typeOfService, MedicalRecord medicalRecord, String consultationNotes, PrescriptionStatus status) {
        this.appointment = appointment;
        this.typeOfService = typeOfService;
        this.medicalRecord = medicalRecord;
        this.consultationNotes = consultationNotes;
        this.status = status;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

}
