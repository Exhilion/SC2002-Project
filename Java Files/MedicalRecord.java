package OOPProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a medical record in the hospital system. 
 * Each record is associated with a patient, a diagnosis, a treatment plan, 
 * and a list of prescriptions.
 */
public class MedicalRecord {

    private String medicalRecordID;
    private Patient patient;
    private Diagnosis diagnosis;
    private Treatment treatment;
    private List<Prescription> prescriptions; // List of prescriptions associated with the medical record

    /**
     * Constructs a new {@code MedicalRecord} object with the specified details.
     *
     * @param MedicalRecordID The unique identifier for the medical record.
     * @param Patient         The patient associated with the medical record.
     * @param Diagnosis       The diagnosis associated with the medical record.
     * @param Treatment       The treatment associated with the medical record.
     * @param prescriptions   The list of prescriptions associated with the medical record.
     */
    public MedicalRecord(String MedicalRecordID, Patient Patient, Diagnosis Diagnosis, Treatment Treatment,
                         List<Prescription> prescriptions) {
        this.medicalRecordID = MedicalRecordID;
        this.patient = Patient;
        this.diagnosis = Diagnosis;
        this.treatment = Treatment;
        this.prescriptions = prescriptions;
    }

    /**
     * Gets the ID of the medical record.
     *
     * @return The medical record ID.
     */
    public String getRecordID() {
        return medicalRecordID;
    }

    /**
     * Gets the patient associated with the medical record.
     *
     * @return The patient object.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Gets the diagnosis associated with the medical record.
     *
     * @return The diagnosis object.
     */
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    /**
     * Gets the treatment plan associated with the medical record.
     *
     * @return The treatment object.
     */
    public Treatment getTreatment() {
        return treatment;
    }

    /**
     * Sets the patient associated with the medical record.
     *
     * @param patient The patient object to set.
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Sets the diagnosis associated with the medical record.
     *
     * @param diagnosis The diagnosis object to set.
     */
    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Sets the treatment plan associated with the medical record.
     *
     * @param treatment The treatment object to set.
     */
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    /**
     * Gets the list of prescriptions associated with the medical record.
     *
     * @return The list of prescriptions.
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Sets the list of prescriptions associated with the medical record.
     *
     * @param prescriptions The list of prescriptions to set.
     */
    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Filters the list of medical records by a specified hospital ID.
     *
     * @param records    The list of medical records to filter.
     * @param hospitalId The hospital ID to filter by.
     * @return A list of filtered medical records that match the hospital ID.
     */
    public static List<MedicalRecord> filterByHospitalId(List<MedicalRecord> records, String hospitalId) {
        return records.stream()
                .filter(record -> record.getPatient().getHospitalId() != null
                        && record.getPatient().getHospitalId().equalsIgnoreCase(hospitalId))
                .collect(Collectors.toList());
    }

    /**
     * Prints the details of all prescriptions associated with the medical record.
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
     * Displays a list of filtered medical records.
     *
     * @param filteredRecords The list of medical records to display.
     */
    public static void displayMedicalRecords(List<MedicalRecord> filteredRecords) {
        for (MedicalRecord record : filteredRecords) {
            System.out.println("Medical Record ID: " + record.getRecordID());
            System.out.println("Patient Name: " + record.getPatient().getName());
            System.out.println("Diagnosis: " + record.getDiagnosis().getdiagnosis());
            System.out.println("Treatment: " + record.getTreatment().gettreatment());
            System.out.println("Prescriptions: ");
            record.getPrescriptions().forEach(
                    prescription -> System.out.println("  - " + prescription.getMedication().getMedicineName()));
            System.out.println("----------------------------------------------------");
        }
    }
}