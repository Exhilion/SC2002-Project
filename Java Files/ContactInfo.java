package OOPProject;

/**
 * The ContactInfo class represents the contact details of an individual,
 * including their phone number and email address.
 */
public class ContactInfo {
    
    private String phoneNumber;  
    private String email;       
    
    /**
     * Constructs a ContactInfo object with the specified phone number and email.
     * 
     * @param phoneNumber the phone number of the individual
     * @param email the email address of the individual
     */
    public ContactInfo(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber; 
        this.email = email;
    }
    
    /**
     * Returns the phone number of the individual.
     * 
     * @return the phone number as a String
     */
    public String getPhoneNumber() {
        return phoneNumber; 
    }
    
    /**
     * Sets the phone number of the individual.
     * 
     * @param phoneNumber the new phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Returns the email address of the individual.
     * 
     * @return the email address as a String
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email address of the individual.
     * 
     * @param email the new email address to set
     */
    public void setEmail(String email) {
        this.email = email; 
    }
    
    /**
     * Constructs a ContactInfo object with a single contact detail (phone number).
     * This constructor is intended for cases where only the phone number is provided.
     * 
     * @param contactDetail the phone number to set
     */
    public ContactInfo(String contactDetail) {
        this.phoneNumber = contactDetail; 
    }
    
    /**
     * Returns a string representation of the ContactInfo object.
     * This includes the phone number, but can be extended to include email.
     * 
     * @return a string representing the contact information
     */
    @Override
    public String toString() {
        return "Phone: " + phoneNumber; 
    }
}
