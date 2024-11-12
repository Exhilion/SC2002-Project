package OOPProject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Administrator extends User{
	private String adminName;
    private int adminID;
    public Administrator(String adminName, int adminID) {
        this.adminName = adminName;
        this.adminID = adminID;
    }
    public Administrator() {
    	
    }
    
    public Administrator(String hospitalID, String password, Role role, Gender gender, String administratorName,Boolean firstTimeLogin) {
        super(hospitalID, password, role, gender, firstTimeLogin);
		this.adminName = administratorName;

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
    

}
