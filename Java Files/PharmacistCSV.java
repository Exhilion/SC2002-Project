package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PharmacistCSV {

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
