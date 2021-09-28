package educa.movel.com.model;

public class Book {
    private String uid, title, subject, url;

    public Book(String uid, String title, String subject, String url) {
        this.uid = uid;
        this.title = title;
        this.subject = subject;
        this.url = url;
    }

    public Book() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
