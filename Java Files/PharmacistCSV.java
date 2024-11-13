package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PharmacistCSV {

    // Method to load pharmacists from CSV
    public List<Pharmacist> loadPharmacistsFromCSV() {
        List<Pharmacist> pharmacists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PHARMACIST_FILE_PATH))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assuming CSV structure: HospitalID, Password, Role, Gender, Name, firstTimeLogin
                Pharmacist pharmacist = new Pharmacist(
                    values[0], // HospitalID
                    values[1], // Password
                    Role.PHARMACIST, // Hardcoded role since it's a pharmacist
                    Gender.valueOf(values[3].toUpperCase()), // Gender
                    values[4], // Name
                    Boolean.parseBoolean(values[5]) // firstTimeLogin
                );
                pharmacists.add(pharmacist);
            }
        } catch (IOException e) {
            System.out.println("Error loading pharmacists: " + e.getMessage());
        }
        return pharmacists;
    }
}
