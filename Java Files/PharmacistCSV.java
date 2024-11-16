package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides functionality to manage pharmacist data stored in a CSV file.
 * It includes methods to load pharmacists from the CSV file.
 */
public class PharmacistCSV {

    /**
     * Loads pharmacist records from the CSV file specified in the application configuration.
     *
     * @return A list of {@link Pharmacist} objects loaded from the CSV file. 
     *         Returns an empty list if an error occurs or no records are found.
     */
    public List<Pharmacist> loadPharmacistsFromCSV() {
        List<Pharmacist> pharmacists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PHARMACIST_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Pharmacist pharmacist = new Pharmacist(
                    values[0], // Hospital ID
                    values[1], // Password
                    Role.PHARMACIST, // Role
                    Gender.valueOf(values[3].toUpperCase()), // Gender
                    values[4], // Pharmacist Name
                    Boolean.parseBoolean(values[5]) // First-time login status
                );
                pharmacists.add(pharmacist);
            }
        } catch (IOException e) {
            System.out.println("Error loading pharmacists: " + e.getMessage());
        }
        return pharmacists;
    }
}