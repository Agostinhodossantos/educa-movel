package educa.movel.com.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String uid, img;

    public Image(String uid, String img) {
        this.uid = uid;
        this.img = img;
    }

    public Image() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
