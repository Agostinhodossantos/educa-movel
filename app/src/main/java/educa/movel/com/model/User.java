package educa.movel.com.model;

import java.io.Serializable;

public class User implements Serializable {
    String  category, contact, date_of_birth,
            email, genre, imageUrl, name, password,
            residence, school, userId;

    public User(String category, String contact, String date_of_birth, String email, String genre, String imageUrl, String name, String password, String residence, String school, String userId) {
        this.category = category;
        this.contact = contact;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.name = name;
        this.password = password;
        this.residence = residence;
        this.school = school;
        this.userId = userId;
    }

    public User() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}