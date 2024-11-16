package OOPProject;

/**
 * Represents a medical diagnosis with associated details such as a description and a doctor.
 */
public class Diagnosis {
	
	/** 
     * The name or type of the diagnosis. 
     */
	private String diagnosis; 
	
	/** 
     * A detailed description of the diagnosis. 
     */
	private String description; 
	
	/** 
     * The doctor associated with this diagnosis. 
     */
	private Doctor Doctor; 
	
	/** 
     * The unique identifier for the diagnosis. 
     */
	private String diagnosisID;
	
	/**
     * Constructs a new Diagnosis instance with the specified ID, name, and description.
     *
     * @param diagnosisID the unique identifier for the diagnosis
     * @param diagnosis the name or type of the diagnosis
     * @param description a detailed description of the diagnosis
     */
	public Diagnosis(String diagnosisID, String diagnosis, String description) {
		this.diagnosisID = diagnosisID;
		this.diagnosis = diagnosis;
		this.description = description;

	}

	/**
     * Gets the unique identifier for this diagnosis.
     *
     * @return the diagnosis ID
     */
	public String getDiagnosisID() {
	    return diagnosisID;
	}

	/**
     * Gets the name or type of the diagnosis.
     *
     * @return the diagnosis
     */
	public String getdiagnosis() {
		return diagnosis;
	}

	/**
     * Gets the description of the diagnosis.
     *
     * @return the description
     */
	public String getdescription() {
		return description;
	}

	/**
     * Sets the name or type of the diagnosis.
     *
     * @param diagnosis the new diagnosis name or type
     */
	public void setdiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
     * Sets the description of the diagnosis.
     *
     * @param description the new description
     */
	public void description(String description) {
		this.description = description;
	}

}
