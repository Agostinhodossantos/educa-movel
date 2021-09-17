package educa.movel.com.model;

import java.io.Serializable;


public class Exhibitor implements Serializable {
    private String uid, name, img,contact ,location, email, password, video, description, qrcode;
    private long rating;
    private long count;

    public Exhibitor(String uid, String name, String img, String contact, String location, String email, String password, String video, String description, String qrcode, long rating, long count) {
        this.uid = uid;
        this.name = name;
        this.img = img;
        this.contact = contact;
        this.location = location;
        this.email = email;
        this.password = password;
        this.video = video;
        this.description = description;
        this.qrcode = qrcode;
        this.rating = rating;
        this.count = count;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
