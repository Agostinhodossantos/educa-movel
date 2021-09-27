package educa.movel.com.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String uid, url;

    public Image(String uid, String url) {
        this.uid = uid;
        this.url = url;
    }

    public Image() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
