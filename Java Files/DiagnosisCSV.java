package OOPProject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to handle loading Diagnosis data from a CSV file.
 */
public class DiagnosisCSV {
	// Method to load diagnosis from CSV
	/**
     * Loads a list of {@link Diagnosis} objects from a CSV file.
     * 
     * <p>The CSV file should have three columns: 
     * <ul>
     *   <li>Diagnosis ID</li>
     *   <li>Diagnosis Name</li>
     *   <li>Description</li>
     * </ul>
     * Each record is parsed and converted into a {@link Diagnosis} object.
     * Invalid or improperly formatted records are skipped, and a message is logged to the console.
     * 
     * @return a list of {@link Diagnosis} objects loaded from the CSV file
     */
	public List<Diagnosis> loadDiagnosisFromCSV() {
	  List<Diagnosis> diagnosiss = new ArrayList<>();
	  try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.DIAGNOSIS_FILE_PATH))) {
	      String line;
	      br.readLine(); // Skip the header line
	      while ((line = br.readLine()) != null) {
	          String[] values = line.split(",");
	          if (values.length == 3) {
	              Diagnosis diagnosis = new Diagnosis(values[0], values[1], values[2]);
	              diagnosiss.add(diagnosis);
	          } else {
	              System.out.println("Invalid record format: " + line);
	          }
	      }
	  } catch (IOException e) {
	      e.printStackTrace();
	  }
	  return diagnosiss;
	}
	
}
