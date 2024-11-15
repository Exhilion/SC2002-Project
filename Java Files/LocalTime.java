package OOPProject;

public class LocalTime {
    private java.time.LocalTime time;

    public static java.time.LocalTime now() {
        return java.time.LocalTime.now();
    }

    public java.time.LocalTime getTime() {
        return time;
    }

    public void setTime(java.time.LocalTime time) {
        this.time = time;
    }
}

