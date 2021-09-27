package educa.movel.com.model;

public class College {
    String uid, college;

    public College(String uid, String college) {
        this.uid = uid;
        this.college = college;
    }

    public College() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
