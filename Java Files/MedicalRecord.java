package OOPProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

public class MedicalRecord {
	
	private String medicalRecordID;
	private Patient patient;
	private Diagnosis diagnosis;
	private Treatment treatment;
	private List<Prescription> prescriptions; // Changed from single Prescription to List

	public MedicalRecord(String MedicalRecordID, Patient Patient, Diagnosis Diagnosis, Treatment Treatment,
			List<Prescription> prescriptions) {
		this.medicalRecordID = MedicalRecordID;
		this.patient = Patient;
		this.diagnosis = Diagnosis;
		this.treatment = Treatment;
		this.prescriptions = prescriptions; // Updated constructor to accept a list of prescriptions
	}

	// Getter and Setter methods

	public String getRecordID() {
	    return medicalRecordID;
	}
	public Patient getPatient() {
	    return patient;
	}
	public Diagnosis getDiagnosis() {
	    return diagnosis;
	}
	public Treatment getTreatment() {
	    return treatment;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}


	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	// Filter medical records by hospital ID
	public static List<MedicalRecord> filterByHospitalId(List<MedicalRecord> records, String hospitalId) {
	    return records.stream()
	                  .filter(record -> record.getPatient().getHospitalId() != null
	                          && record.getPatient().getHospitalId().equalsIgnoreCase(hospitalId))
	                  .collect(Collectors.toList());
	}
	

    // Method to print prescription details
    public void printPrescriptionDetails() {
        System.out.println("Prescription Details:");
        
        if (prescriptions != null && !prescriptions.isEmpty()) {
            for (Prescription prescription : prescriptions) {
                System.out.println("--------------------------------");
                System.out.println("Medication Name: " + prescription.getMedication().getMedicineName());
                System.out.println("Dosage: " + prescription.getDosage());
                System.out.println("Frequency: " + prescription.getFrequency());
                System.out.println("Duration: " + prescription.getDuration());
            }
        } else {
            System.out.println("No prescriptions available.");
        }
        System.out.println("--------------------------------");
    }



}
