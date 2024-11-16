package OOPProject;

/**
 * The PatientMedRecords interface defines the methods related to managing and updating
 * the medical records of patients. It provides functionality to view and update a
 * patient's medical records.
 */
public interface PatientMedRecords {
	
	/**
     * Retrieves the medical records of a patient given their patient ID.
     * 
     * @param PatientID The unique identifier of the patient whose medical records are to be viewed.
     * @return The medical records of the specified patient.
     */
	MedicalRecord viewMedicalRecords(String PatientID);
	
	/**
     * Updates the medical records of a patient with new information.
     * 
     * @param PatientID The unique identifier of the patient whose records are to be updated.
     * @param records The new medical record information to be updated for the patient.
     */
	void updateMedicalRecords(String PatientID, MedicalRecord records);
}
