package OOPProject;

import java.util.List;

public class loadCSVClass {
    private List<Patient> patients;
    private List<Treatment> treatments;
    private List<Medication> medications;
    private List<Diagnosis> diagnoses;
    private List<Prescription> prescriptions;
    private List<MedicalRecord> medicalRecords;
    private List<AppointmentSlot> appointmentSlots;
    private List<Doctor> doctors;
    private List<Appointment> appointments;


    // Constructor to load all CSV data
    public loadCSVClass() {
        // Load Patients
        PatientCSV patientCSV = new PatientCSV();
        this.patients = patientCSV.viewPatientRecords();

        // Load Treatments
        TreatmentCSV treatmentCSV = new TreatmentCSV();
        this.treatments = treatmentCSV.loadTreatmentsFromCSV();

        // Load Medications
        MedicationCSV medicationCSV = new MedicationCSV();
        this.medications = medicationCSV.loadMedicationsFromCSV();

        // Load Diagnoses
        DiagnosisCSV diagnosisCSV = new DiagnosisCSV();
        this.diagnoses = diagnosisCSV.loadDiagnosisFromCSV();

        // Load Prescriptions, passing in medications if needed
        PrescriptionCSV prescriptionCSV = new PrescriptionCSV();
        this.prescriptions = prescriptionCSV.loadPrescriptionsFromCSV(this.medications);

        // Load Medical Records, passing in other entities as dependencies
        MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
        this.medicalRecords = medicalRecordCSV.loadMedicalRecordsFromCSV(
                this.patients, this.diagnoses, this.treatments, this.prescriptions);
        
        DoctorCSV doctorCSV = new DoctorCSV();
        this.doctors = doctorCSV.loadDoctorsFromCSV();
        
        AppointmentSlotCSV appointmentSlotCSV = new AppointmentSlotCSV();
        this.appointmentSlots = appointmentSlotCSV.loadAppointmentSlotsFromCSV(doctors);
        
        AppointmentCSV appointmentCSV = new AppointmentCSV();
        this.appointments = appointmentCSV.loadAppointmentsFromCSV(appointmentSlots, patients);
    }

    // Getter methods to access the loaded data
    public List<Patient> getPatients() {
        return patients;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
    
    public List<Doctor> getDoctors() {
        return doctors;
    }
    
    public List<AppointmentSlot> getAppointmentSlots() {
        return appointmentSlots;
    }
    
    public List<Appointment> getAppointment() {
        return appointments;
    }
}
