package OOPProject;

import java.util.List;

/**
 * The MedicalRecordService interface defines the requirements for services related to managing medical records.
 * Any class that implements this interface will need to provide the implementation of adding a medical record.
 */
public interface MedicalRecordService {
	
	/**
     * Adds a new medical record with the given details.
     * 
     * @param recordID The unique identifier for the medical record.
     * @param patient The patient associated with the medical record.
     * @param diagnosis The diagnosis associated with the medical record.
     * @param treatment The treatment associated with the medical record.
     * @param prescriptions The list of prescriptions associated with the medical record.
     */
    void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
                          List<Prescription> prescriptions);
}
