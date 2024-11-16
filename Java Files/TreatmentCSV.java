package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality to load treatments from a CSV file.
 * The treatments include details such as treatment ID, name, and description.
 */
public class TreatmentCSV {

    /**
     * Loads treatments from the specified CSV file path defined in {@link AppConfig}.
     *
     * @return a list of {@link Treatment} objects parsed from the CSV file.
     *         If the file contains invalid records, they are skipped with a message.
     */
    public List<Treatment> loadTreatmentsFromCSV() {
        List<Treatment> treatments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.TREATMENT_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    Treatment treatment = new Treatment(values[0], values[1], values[2]);
                    treatments.add(treatment);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading treatments: " + e.getMessage());
        }
        return treatments;
    }
}