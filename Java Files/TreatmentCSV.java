package OOPProject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TreatmentCSV class handles the loading of treatment data from a CSV file.
 * It reads the CSV file and creates Treatment objects from the data.
 */
public class TreatmentCSV {
	// Method to load treatments from CSV
	/**
     * Loads treatments from a CSV file and returns a list of Treatment objects.
     * 
     * @return A list of Treatment objects loaded from the CSV file.
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
	         e.printStackTrace();
	     }
	     return treatments;
	 }
}
