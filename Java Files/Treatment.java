package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Doctor treatment for patient
/**
 * The Treatment class represents a medical treatment prescribed by a doctor for a patient.
 * It holds information about the treatment ID, the treatment name, and a description of the treatment.
 */
public class Treatment {
	private String treatmentID;
	private String treatment;
	private String description;

	/**
     * Constructor to initialize the Treatment object with a treatment ID, name, and description.
     *
     * @param treatmentID The unique identifier for the treatment.
     * @param treatment The name of the treatment.
     * @param description A detailed description of the treatment.
     */
	public Treatment(String treatmentID, String treatment, String description) {
		this.treatmentID = treatmentID;
		this.treatment = treatment;
		this.description = description;
	}

	/**
     * Gets the treatment ID.
     * 
     * @return The treatment ID.
     */
	public String getTreatmentID() {
	    return treatmentID;
	}
	
	/**
     * Gets the name of the treatment.
     * 
     * @return The name of the treatment.
     */
	public String gettreatment() {
		return treatment;
	}

	/**
     * Sets the name of the treatment.
     * 
     * @param treatment The name of the treatment.
     */
	public void settreatment(String treatment) {
		this.treatment = treatment;
	}

	/**
     * Gets the description of the treatment.
     * 
     * @return The description of the treatment.
     */
	public String getdescription() {
		return description;
	}

	/**
     * Sets the description of the treatment.
     * 
     * @param description A detailed description of the treatment.
     */
	public void setdescription(String description) {
		this.description = description;
	}



}
