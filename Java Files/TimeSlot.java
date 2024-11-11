public class TimeSlot {
    private String id;
    private String date;
    private String time;

    public TimeSlot(String id, String date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
