package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a prescription containing details about the medication, dosage, frequency, and duration.
 * 
 * <p>
 * Pharmacists can:
 * - View appointment outcome records to fulfill prescription orders (via {@link AppointmentOutcome}).
 * - Update the status of prescriptions from pending to dispensed.
 * - Monitor the inventory of medications (via {@link Medication}).
 * - Submit requests to administrators for stock replenishment (via {@link Medication}).
 * </p>
 */
public class Prescription {
    private String prescriptionID;
    private Medication medication;
    private int dosage;
    private int frequency;
    private String duration;

    /**
     * Constructs a {@code Prescription} with the specified details.
     *
     * @param prescriptionID the unique ID of the prescription.
     * @param medication the medication associated with the prescription.
     * @param dosage the dosage of the medication (in mg).
     * @param frequency the frequency of administration (times per day).
     * @param duration the duration of the prescription (e.g., "7 days").
     */
    public Prescription(String prescriptionID, Medication medication, int dosage, int frequency, String duration) {
        this.prescriptionID = prescriptionID;
        this.medication = medication;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
    }

    /**
     * Returns the unique ID of the prescription.
     *
     * @return the prescription ID.
     */
    public String getPrescriptionID() {
        return prescriptionID;
    }

    /**
     * Returns the medication associated with this prescription.
     *
     * @return the medication.
     */
    public Medication getMedication() {
        return medication;
    }

    /**
     * Sets the medication for this prescription.
     *
     * @param medication the medication to set.
     */
    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    /**
     * Returns the dosage of the medication (in mg).
     *
     * @return the dosage.
     */
    public int getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage of the medication (in mg).
     *
     * @param dosage the dosage to set.
     */
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    /**
     * Returns the frequency of administration (times per day).
     *
     * @return the frequency.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency of administration (times per day).
     *
     * @param frequency the frequency to set.
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Returns the duration of the prescription.
     *
     * @return the duration (e.g., "7 days").
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the prescription.
     *
     * @param duration the duration to set (e.g., "7 days").
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Returns a string representation of the prescription details.
     *
     * @return a string containing all prescription details.
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