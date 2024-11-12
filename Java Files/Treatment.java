package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Doctor treatment for patient
public class Treatment {
	private String treatmentID;
	private String treatment;
	private String description;

	public Treatment(String treatmentID, String treatment, String description) {
		this.treatmentID = treatmentID;
		this.treatment = treatment;
		this.description = description;
	}

	public String getTreatmentID() {
	    return treatmentID;
	}
	public String gettreatment() {
		return treatment;
	}

	public void settreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}



}
