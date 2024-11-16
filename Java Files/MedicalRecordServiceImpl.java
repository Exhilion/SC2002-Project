package OOPProject;

import java.util.List;

/**
 * The {@code MedicalRecordServiceImpl} class provides the implementation for the
 * {@link MedicalRecordService} interface. It handles the business logic for
 * adding and updating medical records in the hospital system.
 */
public class MedicalRecordServiceImpl implements MedicalRecordService {

    /**
     * Adds a new medical record to the system and stores it in the associated CSV file.
     *
     * @param recordID      The unique identifier for the medical record.
     * @param patient       The {@link Patient} associated with the medical record.
     * @param diagnosis     The {@link Diagnosis} associated with the medical record.
     * @param treatment     The {@link Treatment} associated with the medical record.
     * @param prescriptions The list of {@link Prescription} objects associated with the medical record.
     */
    @Override
    public void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
                                 List<Prescription> prescriptions) {
        MedicalRecord newMedicalRecord = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
        MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
        medicalRecordCSV.addMedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
        System.out.println("Medical record added for Patient ID " + patient.getHospitalId());
    }

    /**
     * Updates an existing medical record in the system and reflects the changes in the CSV file.
     *
     * @param recordID      The unique identifier for the medical record to update.
     * @param patientID     The ID of the patient associated with the medical record.
     * @param diagnosis     The updated {@link Diagnosis} for the medical record.
     * @param treatment     The updated {@link Treatment} for the medical record.
     * @param prescriptions The updated list of {@link Prescription} objects for the medical record.
     */
    @Override
    public void updatePatientRecords(String recordID, String patientID, Diagnosis diagnosis, Treatment treatment,
                                     List<Prescription> prescriptions) {
        MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
        medicalRecordCSV.updateMedicalRecord(recordID, patientID, diagnosis, treatment, prescriptions);
    }
}