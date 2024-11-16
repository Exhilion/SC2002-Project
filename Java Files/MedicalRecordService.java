package OOPProject;

import java.util.List;

/**
 * The {@code MedicalRecordService} interface defines the contract for managing
 * medical records within the hospital system. It provides methods for adding
 * and updating medical records associated with patients, diagnoses, treatments, and prescriptions.
 */
public interface MedicalRecordService {

    /**
     * Adds a new medical record to the system.
     *
     * @param recordID      The unique identifier for the medical record.
     * @param patient       The {@link Patient} associated with the medical record.
     * @param diagnosis     The {@link Diagnosis} associated with the medical record.
     * @param treatment     The {@link Treatment} associated with the medical record.
     * @param prescriptions The list of {@link Prescription} objects associated with the medical record.
     */
    void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
                          List<Prescription> prescriptions);

    /**
     * Updates an existing medical record in the system.
     *
     * @param recordID      The unique identifier for the medical record to update.
     * @param PatientID     The ID of the patient associated with the medical record.
     * @param diagnosis     The updated {@link Diagnosis} for the medical record.
     * @param treatment     The updated {@link Treatment} for the medical record.
     * @param prescriptions The updated list of {@link Prescription} objects for the medical record.
     */
    void updatePatientRecords(String recordID, String PatientID, Diagnosis diagnosis, Treatment treatment,
                               List<Prescription> prescriptions);
}