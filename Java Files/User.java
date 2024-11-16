package OOPProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader; 
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class User{
	private String hospitalID;
    private String password;
    private Role role;
    private Gender gender;
	private Boolean firstTimeLogin;
    

	public User() {
		
	}
	
	public User(String username, String pasword) {
		this.hospitalID = username;
		this.password = password;
	}
	
	public User(String hospitalID, String password, Role role, Gender gender, Boolean firstTimeLogin) {
		this.hospitalID = hospitalID; 
		this.password = password; 
		this.role = role; 
		this.gender = gender;
		this.firstTimeLogin = firstTimeLogin;
	}
	
	
	public String getHospitalId() {
        return hospitalID;
    }

    public void setHospitalId(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Getter and Setter for gender
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
 // Getter for firstTimeLogin
    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    // Setter for firstTimeLogin
    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

	
	
	
}
