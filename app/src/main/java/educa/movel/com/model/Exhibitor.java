package educa.movel.com.model;

import java.io.Serializable;


public class Exhibitor implements Serializable {

    String uid, category,
    chat,
    email,
    img1,
    img2,
    institution_description,
    institution_name,
    location,
    password,
    phone,
    video_link,
    website;

    public Exhibitor(String uid, String category, String chat, String email, String img1, String img2, String institution_description, String institution_name, String location, String password, String phone, String video_link, String website) {
        this.uid = uid;
        this.category = category;
        this.chat = chat;
        this.email = email;
        this.img1 = img1;
        this.img2 = img2;
        this.institution_description = institution_description;
        this.institution_name = institution_name;
        this.location = location;
        this.password = password;
        this.phone = phone;
        this.video_link = video_link;
        this.website = website;
    }

    public Exhibitor() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getInstitution_description() {
        return institution_description;
    }

    public void setInstitution_description(String institution_description) {
        this.institution_description = institution_description;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
