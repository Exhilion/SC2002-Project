package OOPProject;

import java.util.List;

public class loadCSVClass {
	private List<Patient> patients; 
    private List<Treatment> treatments; 
    private List<Medication> medications; 
    private List<Diagnosis> diagnoses; 
    private List<Prescription> prescriptions; 
    private List<MedicalRecord> medicalRecords; 
 
    public loadCSVClass() { 
        loadAllData(); 
    } 
 
    private void loadAllData() { 
        System.out.println("Loading patients..."); 
        PatientCSV patientCSV = new PatientCSV(); 
        this.patients = patientCSV.viewPatientRecords(); 
        System.out.println("Patients loaded: " + (patients != null ? patients.size() : 0)); 
 
        System.out.println("Loading treatments..."); 
        TreatmentCSV treatmentCSV = new TreatmentCSV(); 
        this.treatments = treatmentCSV.loadTreatmentsFromCSV(); 
        System.out.println("Treatments loaded: " + (treatments != null ? treatments.size() : 0)); 
 
        System.out.println("Loading medications..."); 
        MedicationCSV medicationCSV = new MedicationCSV(); 
        this.medications = medicationCSV.loadMedicationsFromCSV(); 
        System.out.println("Medications loaded: " + (medications != null ? medications.size() : 0)); 
 
        System.out.println("Loading diagnoses..."); 
        DiagnosisCSV diagnosisCSV = new DiagnosisCSV(); 
        this.diagnoses = diagnosisCSV.loadDiagnosisFromCSV(); 
        System.out.println("Diagnoses loaded: " + (diagnoses != null ? diagnoses.size() : 0)); 
 
        System.out.println("Loading prescriptions..."); 
        PrescriptionCSV prescriptionCSV = new PrescriptionCSV(); 
        this.prescriptions = prescriptionCSV.loadPrescriptionsFromCSV(this.medications); 
        System.out.println("Prescriptions loaded: " + (prescriptions != null ? prescriptions.size() : 0)); 
 
        System.out.println("Loading medical records..."); 
        MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV(); 
        this.medicalRecords = medicalRecordCSV.loadMedicalRecordsFromCSV( 
                this.patients, this.diagnoses, this.treatments, this.prescriptions); 
    
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
}
