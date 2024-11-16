package OOPProject;

import java.util.List;

/**
 * The loadCSVClass is responsible for loading data from various CSV files
 * and storing them into appropriate lists. These lists can then be accessed
 * via getter methods to retrieve the data for further use in the application.
 */
public class loadCSVClass {
	
	/**
     * List of all patients loaded from the patient CSV file.
     */
    private List<Patient> patients;
    
    /**
     * List of all treatments loaded from the treatment CSV file.
     */
    private List<Treatment> treatments;
    
    /**
     * List of all medications loaded from the medication CSV file.
     */
    private List<Medication> medications;
    
    /**
     * List of all diagnoses loaded from the diagnosis CSV file.
     */
    private List<Diagnosis> diagnoses;
    
    /**
     * List of all prescriptions loaded from the prescription CSV file.
     */
    private List<Prescription> prescriptions;
    
    /**
     * List of all medical records loaded from the medical record CSV file.
     */
    private List<MedicalRecord> medicalRecords;
    
    /**
     * List of all appointment slots loaded from the appointment slot CSV file.
     */
    private List<AppointmentSlot> appointmentSlots;
    
    /**
     * List of all doctors loaded from the doctor CSV file.
     */
    private List<Doctor> doctors;
    
    /**
     * List of all appointments loaded from the appointment CSV file.
     */
    private List<Appointment> appointments;
    
    /**
     * List of all appointment outcomes loaded from the appointment outcome CSV file.
     */
    private List<AppointmentOutcome> appointmentOutcomes;

    // Constructor to load all CSV data
    /**
     * Constructor that initializes and loads all CSV data into appropriate lists.
     * Each CSV is loaded using a corresponding CSV handler class.
     */
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
        
        AppointmentOutcomeCSV appointmentOutcomeCSV = new AppointmentOutcomeCSV();
        this.appointmentOutcomes = appointmentOutcomeCSV.loadAppointmentOutcomesFromCSV(appointments, medicalRecords);
    }

    // Getter methods to access the loaded data
    /**
     * Returns the list of patients loaded from the CSV.
     * 
     * @return List of patients.
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Returns the list of treatments loaded from the CSV.
     * 
     * @return List of treatments.
     */
    public List<Treatment> getTreatments() {
        return treatments;
    }

    /**
     * Returns the list of medications loaded from the CSV.
     * 
     * @return List of medications.
     */
    public List<Medication> getMedications() {
        return medications;
    }

    /**
     * Returns the list of diagnoses loaded from the CSV.
     * 
     * @return List of diagnoses.
     */
    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    /**
     * Returns the list of prescriptions loaded from the CSV.
     * 
     * @return List of prescriptions.
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Returns the list of medical records loaded from the CSV.
     * 
     * @return List of medical records.
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
    
    /**
     * Returns the list of doctors loaded from the CSV.
     * 
     * @return List of doctors.
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }
    
    /**
     * Returns the list of appointment slots loaded from the CSV.
     * 
     * @return List of appointment slots.
     */
    public List<AppointmentSlot> getAppointmentSlots() {
        return appointmentSlots;
    }
    
    /**
     * Returns the list of appointments loaded from the CSV.
     * 
     * @return List of appointments.
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    /**
     * Returns the list of appointment outcomes loaded from the CSV.
     * 
     * @return List of appointment outcomes.
     */
    public List<AppointmentOutcome> getAppointmentOutcomes() {
        return appointmentOutcomes;
    }
}
