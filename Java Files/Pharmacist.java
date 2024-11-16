package OOPProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a pharmacist in the hospital system. Inherits from the {@link User} class
 * and adds specific details related to the pharmacist's profile, such as their name.
 */
public class Pharmacist extends User {
    private String pharmacistName;

    /**
     * Constructs a new {@code Pharmacist} object with the specified details.
     *
     * @param hospitalId      The unique identifier for the pharmacist in the hospital system.
     * @param password        The password for the pharmacist's account.
     * @param role            The role of the user (e.g., pharmacist). Must be of type {@link Role}.
     * @param gender          The gender of the pharmacist. Must be of type {@link Gender}.
     * @param pharmacistName  The full name of the pharmacist.
     * @param firstTimeLogin  A flag indicating if this is the pharmacist's first login to the system.
     */
    public Pharmacist(String hospitalId, String password, Role role, Gender gender, 
                      String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
    }

    /**
     * Default constructor for {@code Pharmacist}.
     */
    public Pharmacist() {
        // No-args constructor
    }

    /**
     * Retrieves the name of the pharmacist.
     *
     * @return The pharmacist's name.
     */
    public String getPharmacistName() {
        return pharmacistName;
    }
}