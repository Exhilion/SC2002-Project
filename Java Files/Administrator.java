package HospitalProject;

public class Administrator {
    private String adminName;
    private int adminID;

    public Administrator(String adminName, int adminID) {
        this.adminName = adminName;
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void updateInventory(Inventory inv, Medication med, int newStock) {
        int temp = -1;
        for (Medication m : inv.getMedications()) {
            if (m.getMedicineID() == med.getMedicineID()) {
                temp = m.getQuantity();
                m.setQuantity(newStock);
                System.out.println("Updated the quantity of " + m.getMedicineName() + " from " + temp + " to " + m.getQuantity() + ".");
                m.updateLowStockAlert();
                return;
            }
        }
        med.updateLowStockAlert();
        inv.addMedication(med);
        System.out.println("Added new medication: " + med.getMedicineName() + " with quantity: " + med.getQuantity() + ".");
    }
    

    public int getStock(Inventory inv, Medication med) //returns quatity of 'med'. returns 0 otherwise
    {
        for(Medication m: inv.getMedications()){
            if(m.getMedicineID() == med.getMedicineID()){
                return m.getQuantity();
            }
        }
        return 0;
    }

    public void approveReplenishmentRequest(Inventory inv, Medication med, int amount){
        int temp = -1;
        for(Medication m: inv.getMedications()){
            if(m.getMedicineID() == med.getMedicineID()){
                temp = med.getQuantity();
                m.setQuantity(med.getQuantity() + amount);
                System.out.println("Successfully replenished " + med.getMedicineName() + ": " + temp + " --> " + med.getQuantity());
                return;
            }
        }
        System.out.println("Medication " + "'" + med.getMedicineName() + "' is not identified.");  
    }

    public void approveReplenishmentRequest(Inventory inv, Medication med){
        int temp = -1;
        for(Medication m: inv.getMedications()){
            if(m.getMedicineID() == med.getMedicineID()){
                temp = med.getQuantity();
                m.setQuantity(med.getQuantity() + 100);
                System.out.println("Successfully replenished " + med.getMedicineName() + ": " + temp + " --> " + med.getQuantity());
                return;
            }
        }
        System.out.println("Medication " + "'" + med.getMedicineName() + "' is not identified.");  
    }

    public void checkInventory(Inventory inv) {
        if (inv.getMedications().isEmpty()) {
            System.out.println("Hi " + this.adminName + ", the inventory is empty.");
            return;
        }
        
        System.out.println("Hi " + this.adminName + ", this is the list:");
        System.out.println("---------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Quantity", "Low Stock Alert");
        System.out.println("---------------------------------------------");
    
        // Iterate through the list of medications and print each one
        for (Medication med : inv.getMedications()) {
            System.out.printf("%-10d %-20s %-10d %-15s%n", 
                              med.getMedicineID(), 
                              med.getMedicineName(), 
                              med.getQuantity(), 
                              med.getLowStockAlert() ? "Yes" : "No");
        }
        System.out.println("---------------------------------------------");
    }
}
