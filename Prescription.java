package secondpart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Pharmacist prescription management
//Pharmacist can view Appointment outcome record to fufill prescription orders ( Appointment outcome class)
//Status can be updated from pending to dispensed
//Monitors inventory of medication ( in Medication class)
//Submit requests to admin to replenish stock ( in Medication class )
public class Prescription {
	private String prescriptionID;
	private Medication medication;
	private int dosage;
	private int frequency;
	private String duration;

	public Prescription(String prescriptionID, Medication medication, int dosage, int frequency, String duration) {
		this.prescriptionID = prescriptionID;
		this.medication = medication;
		this.dosage = dosage;
		this.frequency = frequency;
		this.duration = duration;
	}

	public String getprescriptionID() {
		return prescriptionID;
	}

	// Getter and Setter for medication
	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	// Getter and Setter for dosage
	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	// Getter and Setter for frequency
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	// Getter and Setter for duration
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	  // toString() method to display the prescription
    @Override
    public String toString() {
        return "Prescription ID: " + prescriptionID + "\n" +
               "Medication: " + medication.getmedicineName() + "\n" +
               "Dosage: " + dosage + " mg\n" +
               "Frequency: " + frequency + " times per day\n" +
               "Duration: " + duration + "\n";
    }





}
