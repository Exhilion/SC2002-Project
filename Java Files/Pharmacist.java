package OOPProject;

public class Pharmacist extends User {
	private String pharmacistName;

    public Pharmacist(String hospitalId, String password, Role role, Gender gender, 
                      String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }
    public Pharmacist() {
    	
    }

}
