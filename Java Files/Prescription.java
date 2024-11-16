package OOPProject;
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
/**
 * The Prescription class represents a prescription order made by a healthcare provider.
 * It contains information about the prescription's ID, the medication prescribed, the dosage, 
 * the frequency of administration, and the duration of treatment. 
 * The class provides methods to get and set these properties and display the prescription details.
 */
public class Prescription {
	private String prescriptionID;
	private Medication medication;
	private int dosage;
	private int frequency;
	private String duration;

	/**
     * Constructs a Prescription object with the given details.
     *
     * @param prescriptionID The unique ID of the prescription.
     * @param medication The medication being prescribed.
     * @param dosage The dosage of the medication in mg.
     * @param frequency The number of times per day the medication is to be taken.
     * @param duration The duration for which the medication is prescribed.
     */
	public Prescription(String prescriptionID, Medication medication, int dosage, int frequency, String duration) {
		this.prescriptionID = prescriptionID;
		this.medication = medication;
		this.dosage = dosage;
		this.frequency = frequency;
		this.duration = duration;
	}

	/**
     * Gets the unique ID of the prescription.
     *
     * @return The prescription ID.
     */
	public String getPrescriptionID() {
	    return prescriptionID;
	}

	// Getter and Setter for medication
	/**
     * Gets the medication prescribed.
     *
     * @return The medication object.
     */
	public Medication getMedication() {
		return medication;
	}

	/**
     * Sets the medication for the prescription.
     *
     * @param medication The medication to be prescribed.
     */
	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	// Getter and Setter for dosage
	/**
     * Gets the dosage of the medication in mg.
     *
     * @return The dosage in mg.
     */
	public int getDosage() {
		return dosage;
	}

	/**
     * Sets the dosage of the medication.
     *
     * @param dosage The dosage in mg.
     */
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	// Getter and Setter for frequency
	/**
     * Gets the frequency of medication administration (number of times per day).
     *
     * @return The frequency of dosage per day.
     */
	public int getFrequency() {
		return frequency;
	}

	/**
     * Sets the frequency of medication administration.
     *
     * @param frequency The number of times per day the medication should be taken.
     */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	// Getter and Setter for duration
	/**
     * Gets the duration for which the medication is prescribed.
     *
     * @return The duration of the prescription (e.g., "7 days").
     */
	public String getDuration() {
		return duration;
	}

	/**
     * Sets the duration of the medication prescription.
     *
     * @param duration The duration for which the medication is prescribed.
     */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	  // toString() method to display the prescription
/**
 * Returns a string representation of the prescription's details.
 * 
 * @return A formatted string displaying the prescription's ID, medication, dosage, frequency, and duration.
 */
  @Override
  public String toString() {
      return "Prescription ID: " + prescriptionID + "\n" +
             "Medication: " + medication.getMedicineName() + "\n" +
             "Dosage: " + dosage + " mg\n" +
             "Frequency: " + frequency + " times per day\n" +
             "Duration: " + duration + "\n";
  }
}
