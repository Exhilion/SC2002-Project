package OOPProject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicationCSV {
	//load medication from csv file
		public List<Medication> loadMedicationsFromCSV() {
	        List<Medication> medications = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.MEDICATION_FILE_PATH))) {
	            String line;
	            br.readLine(); // Skip the header line
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(",");
	                if (values.length == 5) {  // Expecting 4 columns: medicineID, medicineName, quantity, lowStockAlert
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
		
		  // Update medication quantity by medicineID
	    public void updateMedicationQuantity(int medicineID, int newQuantity) {
	        List<Medication> medications = loadMedicationsFromCSV(); // Load medications from CSV

	        // Find the medication by its ID and update the quantity
	        for (Medication med : medications) {
	            if (med.getMedicineID() == medicineID) {
	                med.setQuantity(newQuantity);
	                System.out.println("Quantity updated for " + med.getMedicineName() + " to " + newQuantity);
	                break;
	            }
	        }

	        saveMedicationsToCSV(medications);
	    }


	    private void saveMedicationsToCSV(List<Medication> medications) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AppConfig.MEDICATION_FILE_PATH))) {
	            bw.write("medicineID,medicineName,quantity,lowQuantity,lowStockAlert\n"); // Write header
	            for (Medication med : medications) {
	                bw.write(med.getMedicineID() + "," +
	                        med.getMedicineName() + "," +
	                        med.getQuantity() + "," +
	                        med.getLowQuantity() + "," +
	                        med.getLowStockAlert() + "\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		public void updateLowStockAlert(Medication med)
		{
			if(med.getLowQuantity() >= med.getQuantity())	
			med.setlowstockAlert(true);
			else 
			med.setlowstockAlert(false);
		}
		
		
}
