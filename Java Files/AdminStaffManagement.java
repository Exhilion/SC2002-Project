package OOPProject;

import java.util.List;

/**
 * The interface defines the methods for managing hospital staff members,
 * including administrators, doctors, and pharmacists. The interface provides 
 * methods for adding new staff members and writing their details to a CSV file.
 */
public interface AdminStaffManagement {
	// Method to add a new administrator
	/**
	 * Adds a new administrator to the hospital system with the parameters as its details.
	 * 
	 * @param hospitalId The unique ID of the administrator in the hospital system
	 * @param password The password for the new administrator
	 * @param role The role of the staff member, which in this case is ADMINISTRATOR
	 * @param gender The gender of the administrator
	 * @param administratorName The name of the new administrator
	 * @param firstTimeLogin A flag that indicates whether it is the first time logging in for the administrator
	 */
    void addAdmin(String hospitalId, String password, Role role, Gender gender, String administratorName, Boolean firstTimeLogin);

    /**
     * 
     * Adds a new doctor to the hospital system with the parameters as its details.
	 * 
	 * @param hospitalId The unique ID of the doctor in the hospital system
	 * @param password The password for the new doctor
	 * @param role The role of the staff member, which in this case is DOCTOR
	 * @param gender The gender of the doctor
	 * @param doctorName The name of the new doctor
	 * @param department The department where the doctor works
	 * @param specialisation The specialisation of the doctor
	 * @param firstTimeLogin A flag that indicates whether it is the first time logging in for the doctor
     */
    // Method to add a new doctor
    void addDoctor(String hospitalId, String password, Role role, Gender gender, String doctorName,
                   String department, String specialisation, Boolean firstTimeLogin);

    /**
     * Adds a new pharmacist to the hospital system with the parameters as its details.
     * 
     * @param hospitalId The unique ID of the pharmacist in the hospital system
     * @param password The password for the new pharmacist
     * @param role The role of the staff member, which in this case will be PHARMACIST
     * @param gender The gender of the pharmacist
     * @param pharmacistName The name of the new pharmacist
     * @param firstTimeLogin A flag indicating whether it is the first time the pharmacist is logging in
     */
    // Method to add a new pharmacist
    void addPharmacist(String hospitalId, String password, Role role, Gender gender, String pharmacistName, Boolean firstTimeLogin);

    /**
     * Writes staff member information to the CSV file for storage or future reference.
     * 
     * @param staffMember The staff member object containing the details to be written to the CSV file
     */
    // Method to write staff information to a CSV file
    void writeToCSV(Object staffMember);
}