package educa.movel.com.model;

public class GameUser {
    private String uid;
    private String userUid;
    private String name;
    private String category;
    private String date;
    private int score;


    public GameUser(String uid, String userUid, String name, String category, String date, int score) {
        this.uid = uid;
        this.userUid = userUid;
        this.name = name;
        this.category = category;
        this.date = date;
        this.score = score;
    }

    public GameUser() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
