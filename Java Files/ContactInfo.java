package OOPProject;

/**
 * Represents a contact information record that stores a phone number and an email address.
 * Provides methods to get and set these details, as well as a constructor for initialization.
 * 
 * <p>
 * This class also allows creating a ContactInfo instance with only a phone number, 
 * defaulting the email field to null.
 * </p>
 */
public class ContactInfo {
    private String phoneNumber; 
    private String email; 
    
    /**
     * Constructs a ContactInfo object with both a phone number and an email address.
     * 
     * @param phoneNumber the phone number of the contact
     * @param email the email address of the contact
     */
    public ContactInfo(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber; 
        this.email = email;
    }
    
    /**
     * Gets the phone number of the contact.
     * 
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber; 
    }
    
    /**
     * Sets the phone number of the contact.
     * 
     * @param phoneNumber the new phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Gets the email address of the contact.
     * 
     * @return the email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email address of the contact.
     * 
     * @param email the new email address to set
     */
    public void setEmail(String email) {
        this.email = email; 
    }
    
    /**
     * Constructs a ContactInfo object with only a phone number.
     * The email address is initialized to {@code null}.
     * 
     * @param contactDetail the phone number of the contact
     */
    public ContactInfo(String contactDetail) {
        this.phoneNumber = contactDetail; 
    }
    
    /**
     * Returns a string representation of the contact information.
     * 
     * @return a string in the format "Phone: [phoneNumber]"
     */
    @Override
    public String toString() {
        return "Phone: " + phoneNumber; 
    }
}
