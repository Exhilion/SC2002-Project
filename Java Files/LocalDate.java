package OOPProject;

import java.util.Date;

public class LocalDate {
    private Date date;

    public static Date now() {
        return new Date(); 
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}