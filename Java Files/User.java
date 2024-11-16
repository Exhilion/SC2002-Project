package OOPProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader; 
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

/**
 * The User class represents a user of the system with details such as hospital ID, password, role, gender, and login status.
 * It serves as a base class for different types of users in the system.
 */
public class User{
	private String hospitalID;
    private String password;
    private Role role;
    private Gender gender;
	private Boolean firstTimeLogin;
    
	/**
     * Default constructor for User. Initializes a User object without any properties.
     */
	public User() {
		
	}
	
	/**
     * Constructor to create a User object with hospital ID and password.
     * 
     * @param username The hospital ID of the user.
     * @param password The password of the user.
     */
	public User(String username, String pasword) {
		this.hospitalID = username;
		this.password = password;
	}
	
	/**
     * Constructor to create a User object with all properties.
     * 
     * @param hospitalID The hospital ID of the user.
     * @param password The password of the user.
     * @param role The role of the user (e.g., ADMIN, DOCTOR, PHARMACIST).
     * @param gender The gender of the user.
     * @param firstTimeLogin A flag indicating whether this is the user's first time logging in.
     */
	public User(String hospitalID, String password, Role role, Gender gender, Boolean firstTimeLogin) {
		this.hospitalID = hospitalID; 
		this.password = password; 
		this.role = role; 
		this.gender = gender;
		this.firstTimeLogin = firstTimeLogin;
	}
	
	/**
     * Gets the hospital ID of the user.
     * 
     * @return The hospital ID of the user.
     */
	public String getHospitalId() {
        return hospitalID;
    }

	/**
     * Sets the hospital ID of the user.
     * 
     * @param hospitalID The hospital ID to set for the user.
     */
    public void setHospitalId(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    // Getter and Setter for password
    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password The password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    /**
     * Gets the role of the user (e.g., ADMIN, DOCTOR, PHARMACIST).
     * 
     * @return The role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user (e.g., ADMIN, DOCTOR, PHARMACIST).
     * 
     * @param role The role to set for the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    // Getter and Setter for gender
    /**
     * Gets the gender of the user.
     * 
     * @return The gender of the user.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     * 
     * @param gender The gender to set for the user.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
 // Getter for firstTimeLogin
    /**
     * Gets whether this is the user's first time logging in.
     * 
     * @return True if it is the user's first time logging in, otherwise false.
     */
    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    // Setter for firstTimeLogin
    /**
     * Sets whether this is the user's first time logging in.
     * 
     * @param firstTimeLogin True if it is the user's first time logging in, otherwise false.
     */
    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

	
	
	
}
