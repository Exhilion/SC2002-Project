package OOPProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code StaffCSV} class manages the creation and storage of hospital staff
 * records in CSV files. This includes administrators, doctors, and pharmacists.
 * It implements the {@link AdminStaffManagement} interface.
 */
public class StaffCSV implements AdminStaffManagement {
    private Map<Role, String> roleFilePathMap;

    /**
     * Constructs a {@code StaffCSV} object and initializes the file path mappings
     * for different staff roles.
     */
    public StaffCSV() {
        roleFilePathMap = new HashMap<>();
        roleFilePathMap.put(Role.ADMINISTRATOR, AppConfig.ADMIN_FILE_PATH);
        roleFilePathMap.put(Role.DOCTOR, AppConfig.DOCTOR_FILE_PATH);
        roleFilePathMap.put(Role.PHARMACIST, AppConfig.PHARMACIST_FILE_PATH);
    }

    /**
     * Generates a unique hospital staff ID based on the given role.
     *
     * @param role the role of the staff member (e.g., DOCTOR, PHARMACIST, ADMINISTRATOR).
     * @return a unique hospital ID with a prefix indicating the role.
     */
    public String generateStaffId(Role role) {
        String prefix = switch (role) {
            case DOCTOR -> "DR";
            case PHARMACIST -> "PH";
            case ADMINISTRATOR -> "AD";
            default -> throw new IllegalArgumentException("Unexpected value: " + role);
        };
        return prefix + UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * Adds a new administrator to the CSV file.
     *
     * @param hospitalId        the unique ID of the administrator.
     * @param password          the administrator's password.
     * @param role              the role of the staff member (ADMINISTRATOR).
     * @param gender            the gender of the administrator.
     * @param administratorName the name of the administrator.
     * @param firstTimeLogin    flag indicating if it's the administrator's first login.
     */
    @Override
    public void addAdmin(String hospitalId, String password, Role role, Gender gender, String administratorName, Boolean firstTimeLogin) {
        Administrator newAdmin = new Administrator(hospitalId, password, role, gender, administratorName, firstTimeLogin);
        writeToCSV(newAdmin);
    }

    /**
     * Adds a new doctor to the CSV file.
     *
     * @param hospitalId       the unique ID of the doctor.
     * @param password         the doctor's password.
     * @param role             the role of the staff member (DOCTOR).
     * @param gender           the gender of the doctor.
     * @param doctorName       the name of the doctor.
     * @param department       the department the doctor belongs to.
     * @param specialisation   the doctor's specialization.
     * @param firstTimeLogin   flag indicating if it's the doctor's first login.
     */
    @Override
    public void addDoctor(String hospitalId, String password, Role role, Gender gender, String doctorName,
                          String department, String specialisation, Boolean firstTimeLogin) {
        Doctor newDoctor = new Doctor(hospitalId, password, role, gender, doctorName, department, specialisation, firstTimeLogin);
        writeToCSV(newDoctor);
    }

    /**
     * Adds a new pharmacist to the CSV file.
     *
     * @param hospitalId      the unique ID of the pharmacist.
     * @param password        the pharmacist's password.
     * @param role            the role of the staff member (PHARMACIST).
     * @param gender          the gender of the pharmacist.
     * @param pharmacistName  the name of the pharmacist.
     * @param firstTimeLogin  flag indicating if it's the pharmacist's first login.
     */
    @Override
    public void addPharmacist(String hospitalId, String password, Role role, Gender gender, String pharmacistName, Boolean firstTimeLogin) {
        Pharmacist newPharmacist = new Pharmacist(hospitalId, password, role, gender, pharmacistName, firstTimeLogin);
        writeToCSV(newPharmacist);
    }

    /**
     * Writes the given staff member's information to the appropriate CSV file based
     * on their role.
     *
     * @param staffMember the staff member object (Administrator, Doctor, or Pharmacist).
     * @throws IllegalArgumentException if the staff member type is unsupported.
     */
    @Override
    public void writeToCSV(Object staffMember) {
        String csvLine;
        String filePath = "";

        if (staffMember instanceof Administrator admin) {
            csvLine = admin.getHospitalId() + "," + admin.getPassword() + "," + admin.getRole() + ","
                    + admin.getGender() + "," + admin.getAdminName() + "," + admin.getFirstTimeLogin() + "\n";
            filePath = roleFilePathMap.get(admin.getRole());
        } else if (staffMember instanceof Doctor doctor) {
            csvLine = doctor.getHospitalId() + "," + doctor.getPassword() + "," + doctor.getRole() + ","
                    + doctor.getGender() + "," + doctor.getDoctorName() + "," + doctor.getDepartment() + ","
                    + doctor.getSpecialisation() + "," + doctor.getFirstTimeLogin() + "\n";
            filePath = roleFilePathMap.get(doctor.getRole());
        } else if (staffMember instanceof Pharmacist pharmacist) {
            csvLine = pharmacist.getHospitalId() + "," + pharmacist.getPassword() + "," + pharmacist.getRole() + ","
                    + pharmacist.getGender() + "," + pharmacist.getPharmacistName() + "," + pharmacist.getFirstTimeLogin() + "\n";
            filePath = roleFilePathMap.get(pharmacist.getRole());
        } else {
            throw new IllegalArgumentException("Unsupported staff member type");
        }

        // Write to CSV
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(csvLine);
            System.out.println("New " + staffMember.getClass().getSimpleName() + " added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}