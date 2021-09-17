package educa.movel.com.model;

import java.io.Serializable;


public class Exhibitor implements Serializable {
    private String uid, name, img,contact ,location, email, video, description;


    public Exhibitor(String uid, String name, String img, String contact, String location, String email, String video, String description) {
        this.uid = uid;
        this.name = name;
        this.img = img;
        this.contact = contact;
        this.location = location;
        this.email = email;
        this.video = video;
        this.description = description;
    }

    public Exhibitor() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
