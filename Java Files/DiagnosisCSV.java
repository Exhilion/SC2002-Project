package OOPProject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisCSV {
	// Method to load diagnosis from CSV
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
