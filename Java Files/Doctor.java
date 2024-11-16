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
 * The Doctor class represents a doctor user in the system, extending the User class.
 * It holds information about the doctor's name, department, and specialisation,
 * and it inherits the properties and methods from the User class such as hospitalId, password, role, gender, and firstTimeLogin status.
 */
public class Doctor extends User {

    private String doctorName;
    private String department;
    private String specialisation;

    /**
     * Constructor for creating a new Doctor object.
     * 
     * @param hospitalId The unique identifier for the hospital associated with the doctor.
     * @param password The password for the doctor's account.
     * @param role The role of the user (Doctor).
     * @param gender The gender of the doctor.
     * @param doctorName The name of the doctor.
     * @param department The department where the doctor works.
     * @param specialisation The specialisation of the doctor.
     * @param firstTimeLogin Boolean flag indicating whether this is the doctor's first time logging in.
     */
    public Doctor(String hospitalId, String password, Role role, Gender gender, 
                  String doctorName, String department, String specialisation, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.doctorName = doctorName;
        this.department = department;
        this.specialisation = specialisation;
    }

    /**
     * Gets the name of the doctor.
     * 
     * @return A string representing the doctor's name.
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Gets the department where the doctor works.
     * 
     * @return A string representing the department.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Gets the specialisation of the doctor.
     * 
     * @return A string representing the doctor's specialisation.
     */
    public String getSpecialisation() {
        return specialisation;
    }
}
