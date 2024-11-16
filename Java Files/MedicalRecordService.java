package OOPProject;

import java.util.List;

public interface MedicalRecordService {
    void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
                          List<Prescription> prescriptions);
}
