package educa.movel.com.model;

import java.io.Serializable;

public class Course implements Serializable {
   String uid,course,description;

    public Course(String uid, String course, String description) {
        this.uid = uid;
        this.course = course;
        this.description = description;
    }

    public Course() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
