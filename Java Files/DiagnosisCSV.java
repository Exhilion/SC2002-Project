package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DiagnosisCSV} class is responsible for loading diagnosis data from a CSV file.
 * It reads the file, parses each line, and creates {@code Diagnosis} objects which are stored in a list.
 */
public class DiagnosisCSV {

    /**
     * Loads diagnosis data from a CSV file.
     * The CSV file is expected to contain data in the format: diagnosis id, diagnosis code, diagnosis name, and description.
     * The first line of the CSV is skipped as it contains header information.
     *
     * @return A list of {@code Diagnosis} objects populated from the CSV file.
     */
    public List<Diagnosis> loadDiagnosisFromCSV() {
        List<Diagnosis> diagnosiss = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.DIAGNOSIS_FILE_PATH))) {
            String line;
            br.readLine();
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
