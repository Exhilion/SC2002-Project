package OOPProject;

/**
 * The {@code PatientMedRecords} interface defines the methods for managing and interacting
 * with patient medical records in the system. It includes functionality to view and update
 * medical records for a specific patient.
 */
public interface PatientMedRecords {

    /**
     * Retrieves the medical record for a given patient based on their unique ID.
     *
     * @param PatientID The unique identifier of the patient whose medical records are to be viewed.
     * @return The {@link MedicalRecord} associated with the specified patient.
     */
    MedicalRecord viewMedicalRecords(String PatientID);

    /**
     * Updates the medical record for a given patient with the specified details.
     *
     * @param PatientID The unique identifier of the patient whose medical records are to be updated.
     * @param records   The updated {@link MedicalRecord} object containing the new details.
     */
    void updateMedicalRecords(String PatientID, MedicalRecord records);
}