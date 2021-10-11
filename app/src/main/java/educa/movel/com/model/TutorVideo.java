package educa.movel.com.model;

public class TutorVideo extends Video{
    private String tag;

    public TutorVideo(String url, String thumbnail, String uid, String title, String tag) {
        super(url, thumbnail, uid, title);
        this.tag = tag;
    }

    public TutorVideo(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
