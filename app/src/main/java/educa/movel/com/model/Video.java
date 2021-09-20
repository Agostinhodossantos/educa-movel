package educa.movel.com.model;

public class Video {
    private String url, thumbnail, uid;

    public Video(String url, String thumbnail, String uid) {
        this.url = url;
        this.thumbnail = thumbnail;
        this.uid = uid;
    }

    public Video() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
