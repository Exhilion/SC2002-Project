package OOPProject;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Represents a doctor in the hospital system. Inherits from the {@link User} class and adds specific details
 * related to the doctor's profile, including their name, department, and specialization.
 */
public class Doctor extends User{
	private String doctorName;
    private String department;
    private String specialisation;

    /**
     * Constructs a new {@link Doctor} object with the specified details.
     *
     * @param hospitalId      The unique identifier for the doctor in the hospital system.
     * @param password        The password for the doctor's account.
     * @param role            The role of the user (e.g., doctor). Must be of type {@link Role}.
     * @param gender          The gender of the doctor. Must be of type {@link Gender}.
     * @param doctorName      The full name of the doctor.
     * @param department      The department to which the doctor belongs (e.g., Cardiology, Pediatrics).
     * @param specialisation  The specific area of expertise of the doctor (e.g., Neurologist).
     * @param firstTimeLogin  A flag indicating if this is the doctor's first login to the system.
     */
    public Doctor(String hospitalId, String password, Role role, Gender gender, 
                  String doctorName, String department, String specialisation, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.doctorName = doctorName;
        this.department = department;
        this.specialisation = specialisation;
    }

    /**
     * Gets the full name of the doctor.
     *
     * @return the name of the doctor.
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Gets the department to which the doctor belongs.
     *
     * @return the department name.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Gets the specialization of the doctor.
     *
     * @return the doctor's specialization.
     */
    public String getSpecialisation() {
        return specialisation;
    }

	
}
