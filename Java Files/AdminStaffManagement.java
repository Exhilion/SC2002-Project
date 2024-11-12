package OOPProject;

import java.util.List;

public interface AdminStaffManagement {
	// Method to add a new administrator
    void addAdmin(String hospitalId, String password, Role role, Gender gender, String administratorName);

    // Method to add a new doctor
    void addDoctor(String hospitalId, String password, Role role, Gender gender, String doctorName,
                   String department, String specialisation);

    // Method to add a new pharmacist
    void addPharmacist(String hospitalId, String password, Role role, Gender gender, String pharmacistName);

    // Method to write staff information to a CSV file
    void writeToCSV(Object staffMember);
}