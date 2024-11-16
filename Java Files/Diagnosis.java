package OOPProject;

/**
 * Represents a medical diagnosis in the system.
 * Stores details such as the diagnosis ID, name, and description.
 */
public class Diagnosis {
    private String diagnosis; 
    private String description; 
    private Doctor Doctor; 
    private String diagnosisID;

    /**
     * Constructs a new Diagnosis object with the given diagnosis ID, diagnosis name, and description.
     * 
     * @param diagnosisID the unique identifier for the diagnosis
     * @param diagnosis the name of the diagnosis
     * @param description a detailed description of the diagnosis
     */
    public Diagnosis(String diagnosisID, String diagnosis, String description) {
        this.diagnosisID = diagnosisID;
        this.diagnosis = diagnosis;
        this.description = description;
    }

    /**
     * Returns the unique identifier of the diagnosis.
     * 
     * @return the diagnosis ID
     */
    public String getDiagnosisID() {
        return diagnosisID;
    }

    /**
     * Returns the name of the diagnosis.
     * 
     * @return the diagnosis name
     */
    public String getdiagnosis() {
        return diagnosis;
    }

    /**
     * Returns the description of the diagnosis.
     * 
     * @return the description of the diagnosis
     */
    public String getdescription() {
        return description;
    }

    /**
     * Sets the name of the diagnosis.
     * 
     * @param diagnosis the name to set for the diagnosis
     */
    public void setdiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Sets the description of the diagnosis.
     * 
     * @param description the description to set for the diagnosis
     */
    public void description(String description) {
        this.description = description;
    }
}
