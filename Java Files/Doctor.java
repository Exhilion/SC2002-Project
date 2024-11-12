package OOPProject;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Doctor extends User{
	private String doctorName;
    private String department;
    private String specialisation;

    public Doctor(String hospitalId, String password, Role role, Gender gender, 
                  String doctorName, String department, String specialisation) {
        super(hospitalId, password, role, gender);
        this.doctorName = doctorName;
        this.department = department;
        this.specialisation = specialisation;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDepartment() {
        return department;
    }

    public String getSpecialisation() {
        return specialisation;
    }

	
}
