package OOPProject;

public class Pharmacist extends User {
	private String pharmacistName;
	private String hospitalID;
	private String password;
	private Role role; 
	private Gender gender; 
	
	private static PharmacistCSV pharmacistCSV  = new PharmacistCSV();

    public Pharmacist(String hospitalID, String password, Role role, Gender gender, 
                      String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalID, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
        this.hospitalID = hospitalID; 
        this.role = role;
        this.gender = gender;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }
    
    public String getHospitalID() {
        return hospitalID;
    }
    
    public Role getrole() {
        return role;
    }
    
    public Gender getGender() {
		return gender; 
    }
}