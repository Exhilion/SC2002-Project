package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles user login operations by validating credentials against CSV files for various roles (Doctor, Pharmacist, Administrator, Patient).
 */
public class UserCSV {

    /**
     * Validates user credentials based on their role and returns the role type if login is successful.
     *
     * @param Username the username or hospital ID of the user attempting to log in.
     * @param Password the password associated with the user.
     * @return a string representing the role of the user: "DR" for Doctor, "PH" for Pharmacist, "AD" for Administrator, "PT" for Patient,
     *         or an empty string if authentication fails.
     */
    public String login(String Username, String Password) {
        String role = Username.substring(0, 2);

        if (role.equalsIgnoreCase("DR")) {
            // Validate against Doctor database
            try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.DOCTOR_FILE_PATH))) {
                String line;
                br.readLine(); // Skip header
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 8) {
                        String fileUsername = values[0].trim();
                        String filePassword = values[1].trim();
                        if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            return "DR";
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error Reading the file: " + e.getMessage());
            }
            return " ";
        } else if (role.equalsIgnoreCase("PH")) {
            // Validate against Pharmacist database
            try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PHARMACIST_FILE_PATH))) {
                String line;
                br.readLine(); // Skip header
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 6) {
                        String fileUsername = values[0].trim();
                        String filePassword = values[1].trim();
                        if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            return "PH";
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error Reading the file: " + e.getMessage());
            }
            return " ";
        } else if (role.equalsIgnoreCase("AD")) {
            // Validate against Administrator database
            try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.ADMIN_FILE_PATH))) {
                String line;
                br.readLine(); // Skip header
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 6) {
                        String fileUsername = values[0].trim();
                        String filePassword = values[1].trim();
                        if (fileUsername.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            return "AD";
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error Reading the file: " + e.getMessage());
            }
            return " ";
        } else if (role.equalsIgnoreCase("PT")) {
            // Validate against Patient database
            try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
                String line;
                br.readLine(); // Skip header
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 10) {
                        String filePatientID = values[0].trim();
                        String filePassword = values[1].trim();
                        if (filePatientID.equalsIgnoreCase(Username) && filePassword.equalsIgnoreCase(Password)) {
                            return "PT";
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error Reading the file: " + e.getMessage());
            }
            return " ";
        } else {
            System.out.println("Username does not exist");
            return " ";
        }
    }
}