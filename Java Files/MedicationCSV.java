package OOPProject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicationCSV {
	//load medication from csv file
		public static List<Medication> loadMedicationsFromCSV() {
	        List<Medication> medications = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.MEDICATION_FILE_PATH))) {
	            String line;
	            br.readLine(); // Skip the header line
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(",");
	                if (values.length == 4) {  // Expecting 4 columns: medicineID, medicineName, quantity, lowStockAlert
	                    int medicineID = Integer.parseInt(values[0]);
	                    String medicineName = values[1];
	                    int quantity = Integer.parseInt(values[2]);
	                    int lowQuantity = Integer.parseInt(values[3]);
	                    boolean lowStockAlert = Boolean.parseBoolean(values[4]);
	                    
	                    // Create Medication object and add it to the list
	                    Medication medication = new Medication(medicineID, medicineName, quantity, lowQuantity, lowStockAlert);
	                    medications.add(medication);
	                } else {
	                    System.out.println("Invalid record format: " + line);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return medications;
	    }
		
		public void updateLowStockAlert(Medication med)
		{
			if(med.getLowQuantity() >= med.getQuantity())	
			med.setlowstockAlert(true);
			else 
			med.setlowstockAlert(false);
		}
}
