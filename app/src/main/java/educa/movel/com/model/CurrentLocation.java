package educa.movel.com.model;

public class CurrentLocation {
    private String location;
    private int start,end;

    public CurrentLocation(String location, int start, int end) {
        this.location = location;
        this.start = start;
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
