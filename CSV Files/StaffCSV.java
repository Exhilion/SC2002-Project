package HospitalProject;

import java.io.*;
import java.util.*;

public class StaffCSV {
    private static final String FILE_PATH = "staff.csv";

    // Method to read staff details from CSV file
    public List<Staff> loadStaff() {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    String hospitalId = data[0];
                    String password = data[1];
                    Role role = Role.valueOf(data[2]);
                    Gender gender = Gender.valueOf(data[3]);
                    String name = data[4];
                    boolean firstTimeLogin = Boolean.parseBoolean(data[5]);

                    Staff staff = new Staff(hospitalId, password, role, gender, name, firstTimeLogin);
                    staffList.add(staff);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading staff data: " + e.getMessage());
        }
        return staffList;
    }

    // Method to add a new staff member to the CSV file
    public void addStaff(String hospitalID, String password, Role role, Gender gender, String name) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("%s,%s,%s,%s,%s,%b", 
                                        hospitalID, password, role, gender, name, true);
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error adding staff: " + e.getMessage());
        }
    }

    // Method to update existing staff details in the CSV file
    public void updateStaffDetails(String staffID, String newName, Role newRole, Gender newGender) {
        List<Staff> staffList = loadStaff();
        boolean updated = false;

        for (Staff staff : staffList) {
            if (staff.getHospitalId().equals(staffID)) {
                staff.setName(newName);
                staff.setRole(newRole);
                staff.setGender(newGender);
                updated = true;
                break;
            }
        }

        if (updated) {
            saveStaffList(staffList);
            System.out.println("Staff updated successfully.");
        } else {
            System.out.println("Staff not found.");
        }
    }

    // Method to remove a staff member from the CSV file
    public boolean removeStaffById(String staffID) {
        List<Staff> staffList = loadStaff();
        boolean removed = staffList.removeIf(staff -> staff.getHospitalId().equals(staffID));

        if (removed) {
            saveStaffList(staffList);
        }
        return removed;
    }

    // Method to save the updated staff list back to the CSV file
    private void saveStaffList(List<Staff> staffList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Staff staff : staffList) {
                String line = String.format("%s,%s,%s,%s,%s,%b", 
                                            staff.getHospitalId(), staff.getPassword(),
                                            staff.getRole(), staff.getGender(), staff.getName(), 
                                            staff.isFirstTimeLogin());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving staff data: " + e.getMessage());
        }
    }
}
