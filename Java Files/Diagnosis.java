package OOPProject;

public class Diagnosis {
	private String diagnosis; 
	private String description; 
	private Doctor Doctor; 
	private String diagnosisId;
	
	public Diagnosis(String diagnosisID, String diagnosis, String description) {
		this.diagnosisId = diagnosisID;
		this.diagnosis = diagnosis;
		this.description = description;

	}

	public String getdisgnosisID() {
		return diagnosisId;
	}

	public String getdiagnosis() {
		return diagnosis;
	}

	public String getdescription() {
		return description;
	}

	public void setdiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void description(String description) {
		this.description = description;
	}

}
