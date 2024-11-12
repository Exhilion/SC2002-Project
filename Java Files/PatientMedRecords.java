package OOPProject;

public interface PatientMedRecords {
	MedicalRecord viewMedicalRecords(String PatientID);
	void updateMedicalRecords(String PatientID, MedicalRecord records);
}
