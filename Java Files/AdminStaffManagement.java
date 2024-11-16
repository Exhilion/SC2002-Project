package OOPProject;

import java.util.List;

/**
 * Interface for managing the administration of hospital staff, including 
 * administrators, doctors, and pharmacists. It provides methods to add 
 * staff members and write their details to a CSV file.
 */
public interface AdminStaffManagement {

    /**
     * Adds a new administrator to the system.
     * 
     * @param hospitalId The unique identifier for the hospital.
     * @param password The password for the administrator.
     * @param role The role of the administrator (e.g., manager, supervisor).
     * @param gender The gender of the administrator.
     * @param administratorName The name of the administrator.
     * @param firstTimeLogin A flag indicating whether this is the first time 
     *                       the administrator is logging in.
     */
    void addAdmin(String hospitalId, String password, Role role, Gender gender, 
                  String administratorName, Boolean firstTimeLogin);

    /**
     * Adds a new doctor to the system.
     * 
     * @param hospitalId The unique identifier for the hospital.
     * @param password The password for the doctor.
     * @param role The role of the doctor (e.g., general physician, specialist).
     * @param gender The gender of the doctor.
     * @param doctorName The name of the doctor.
     * @param department The department where the doctor works (e.g., cardiology).
     * @param specialisation The specialisation of the doctor (e.g., surgery).
     * @param firstTimeLogin A flag indicating whether this is the first time 
     *                       the doctor is logging in.
     */
    void addDoctor(String hospitalId, String password, Role role, Gender gender, 
                  String doctorName, String department, String specialisation, 
                  Boolean firstTimeLogin);

    /**
     * Adds a new pharmacist to the system.
     * 
     * @param hospitalId The unique identifier for the hospital.
     * @param password The password for the pharmacist.
     * @param role The role of the pharmacist (e.g., dispensing, clinical).
     * @param gender The gender of the pharmacist.
     * @param pharmacistName The name of the pharmacist.
     * @param firstTimeLogin A flag indicating whether this is the first time 
     *                       the pharmacist is logging in.
     */
    void addPharmacist(String hospitalId, String password, Role role, Gender gender, 
                      String pharmacistName, Boolean firstTimeLogin);

    /**
     * Writes the details of a staff member to a CSV file.
     * 
     * @param staffMember The staff member whose information needs to be written.
     *                    This can be any staff type (administrator, doctor, pharmacist).
     */
    void writeToCSV(Object staffMember);
}
