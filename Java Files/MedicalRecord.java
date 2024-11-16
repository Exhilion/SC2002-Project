package OOPProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

/**
 * The MedicalRecord class represents a medical record for a patient,
 * containing details about the patient's diagnosis, treatment, and prescriptions.
 */
public class MedicalRecord {
	
	/**
     * Unique ID for the medical record.
     */
	private String medicalRecordID;
	
	/**
     * The patient associated with the medical record.
     */
	private Patient patient;
	
	/**
     * The diagnosis associated with the medical record.
     */
	private Diagnosis diagnosis;
	
	/**
     * The treatment associated with the medical record.
     */
	private Treatment treatment;
	
	/**
     * A list of prescriptions associated with the medical record.
     */
	private List<Prescription> prescriptions; // Changed from single Prescription to List

	/**
     * Constructor to create a new MedicalRecord object.
     * 
     * @param MedicalRecordID  The ID of the medical record.
     * @param Patient          The patient associated with the medical record.
     * @param Diagnosis        The diagnosis associated with the medical record.
     * @param Treatment        The treatment associated with the medical record.
     * @param prescriptions    A list of prescriptions associated with the medical record.
     */
	public MedicalRecord(String MedicalRecordID, Patient Patient, Diagnosis Diagnosis, Treatment Treatment,
			List<Prescription> prescriptions) {
		this.medicalRecordID = MedicalRecordID;
		this.patient = Patient;
		this.diagnosis = Diagnosis;
		this.treatment = Treatment;
		this.prescriptions = prescriptions; // Updated constructor to accept a list of prescriptions
	}

	// Getter and Setter methods

	/**
     * Returns the ID of the medical record.
     * 
     * @return The medical record ID.
     */
	public String getRecordID() {
	    return medicalRecordID;
	}
	
	/**
     * Returns the patient associated with the medical record.
     * 
     * @return The patient.
     */
	public Patient getPatient() {
	    return patient;
	}
	
	/**
     * Returns the diagnosis associated with the medical record.
     * 
     * @return The diagnosis.
     */
	public Diagnosis getDiagnosis() {
	    return diagnosis;
	}
	
	/**
     * Returns the treatment associated with the medical record.
     * 
     * @return The treatment.
     */
	public Treatment getTreatment() {
	    return treatment;
	}

	/**
     * Sets the patient associated with the medical record.
     * 
     * @param patient The patient to associate with the medical record.
     */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
     * Sets the diagnosis associated with the medical record.
     * 
     * @param diagnosis The diagnosis to associate with the medical record.
     */
	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
     * Sets the treatment associated with the medical record.
     * 
     * @param treatment The treatment to associate with the medical record.
     */
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	/**
     * Returns the list of prescriptions associated with the medical record.
     * 
     * @return The list of prescriptions.
     */
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	/**
     * Sets the list of prescriptions associated with the medical record.
     * 
     * @param prescriptions The list of prescriptions to associate with the medical record.
     */
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	// Filter medical records by hospital ID
	/**
     * Filters medical records based on the hospital ID.
     * 
     * @param records    The list of medical records to filter.
     * @param hospitalId The hospital ID to filter by.
     * @return A list of filtered medical records matching the hospital ID.
     */
	public static List<MedicalRecord> filterByHospitalId(List<MedicalRecord> records, String hospitalId) {
	    return records.stream()
	                  .filter(record -> record.getPatient().getHospitalId() != null
	                          && record.getPatient().getHospitalId().equalsIgnoreCase(hospitalId))
	                  .collect(Collectors.toList());
	}
	

    // Method to print prescription details
	/**
     * Prints the details of all prescriptions associated with the medical record.
     * If no prescriptions are available, it indicates so.
     */
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
    
    /**
     * Displays a list of filtered medical records for a specific hospital ID.
     * 
     * @param filteredRecords The list of filtered medical records to display.
     * @param hospitalID      The hospital ID to display the records for.
     */
    public static void displayMedicalRecords(List<MedicalRecord> filteredRecords, String hospitalID) {
        System.out.println("\nAvailable Medical Records for Hospital ID: " + hospitalID);
        if (filteredRecords == null || filteredRecords.isEmpty()) {
            System.out.println("No medical records found for the given hospital ID.");
        } else {
            for (MedicalRecord record : filteredRecords) {
                System.out.println("Medical Record ID: " + record.getRecordID());
                System.out.println("Patient Name: " + record.getPatient().getName());
                System.out.println("Diagnosis: " + record.getDiagnosis().getdiagnosis());
                System.out.println("Treatment: " + record.getTreatment().gettreatment());
                System.out.println("Prescriptions: ");
                record.getPrescriptions().forEach(prescription ->
                        System.out.println("  - " + prescription.getMedication().getMedicineName()));
                System.out.println("----------------------------------------------------");
            }
        }
    }



}
