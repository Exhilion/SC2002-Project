package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medical treatment prescribed by a doctor for a patient.
 * This class encapsulates the details of the treatment including its ID, name, and description.
 */
public class Treatment {

    private String treatmentID;
    private String treatment;
    private String description;

    /**
     * Constructs a new {@code Treatment} object with the specified details.
     *
     * @param treatmentID  the unique identifier for the treatment.
     * @param treatment    the name or type of the treatment.
     * @param description  a detailed description of the treatment.
     */
    public Treatment(String treatmentID, String treatment, String description) {
        this.treatmentID = treatmentID;
        this.treatment = treatment;
        this.description = description;
    }

    /**
     * Gets the unique identifier for the treatment.
     *
     * @return the treatment ID.
     */
    public String getTreatmentID() {
        return treatmentID;
    }

    /**
     * Gets the name or type of the treatment.
     *
     * @return the treatment name or type.
     */
    public String gettreatment() {
        return treatment;
    }

    /**
     * Sets the name or type of the treatment.
     *
     * @param treatment the new treatment name or type.
     */
    public void settreatment(String treatment) {
        this.treatment = treatment;
    }

    /**
     * Gets the description of the treatment.
     *
     * @return the treatment description.
     */
    public String getdescription() {
        return description;
    }

    /**
     * Sets the description of the treatment.
     *
     * @param description the new description of the treatment.
     */
    public void setdescription(String description) {
        this.description = description;
    }
}