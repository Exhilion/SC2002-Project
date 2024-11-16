package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PharmacistCSV class is responsible for loading pharmacist records from a CSV file.
 * It reads the pharmacist details from the CSV and creates Pharmacist objects to be used
 * within the system.
 */
public class PharmacistCSV {

	/**
     * Loads a list of pharmacists from a CSV file.
     * The CSV file should contain the following columns: hospitalId, password, role, gender, pharmacistName, firstTimeLogin.
     * 
     * @return A list of Pharmacist objects loaded from the CSV file.
     */
    public List<Pharmacist> loadPharmacistsFromCSV() {
        List<Pharmacist> pharmacists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PHARMACIST_FILE_PATH))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Pharmacist pharmacist = new Pharmacist(
                    values[0],
                    values[1],
                    Role.PHARMACIST, 
                    Gender.valueOf(values[3].toUpperCase()), 
                    values[4],
                    Boolean.parseBoolean(values[5]) 
                );
                pharmacists.add(pharmacist);
            }
        } catch (IOException e) {
            System.out.println("Error loading pharmacists: " + e.getMessage());
        }
        return pharmacists;
    }
}
