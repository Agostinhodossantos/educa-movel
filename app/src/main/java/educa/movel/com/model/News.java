package educa.movel.com.model;

import java.io.Serializable;

public class News implements Serializable {
   String author;
   String category;
   String date;
   String descricao;
   String fonte;
   String img;
   String title;
   String uid;
   String video_link;

    public News(String author, String category, String date, String descricao, String fonte,
                String img, String title, String uid, String video_link) {
        this.author = author;
        this.category = category;
        this.date = date;
        this.descricao = descricao;
        this.fonte = fonte;
        this.img = img;
        this.title = title;
        this.uid = uid;
        this.video_link = video_link;
    }

    public News() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }
}
