package com.rockzhai.readdaily.bean.Essay;

import java.io.Serializable;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public class EssayForDB implements Serializable {
    private int id;
    private String title;
    private String digest;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String author;
    private String content;

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
