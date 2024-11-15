package OOPProject;

public class ContactInfo {
	private String phoneNumber; 
	private String email; 
	
	public ContactInfo(String phoneNumber, String email) {
		this.phoneNumber = phoneNumber; 
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber; 
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email; 
	}
	public ContactInfo(String contactDetail) {
		this.phoneNumber = contactDetail; 
	}
	
	@Override
	public String toString() {
		return "Phone: " + phoneNumber; 
	}
}
