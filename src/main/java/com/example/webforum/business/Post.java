package com.example.webforum.business;

import java.util.Date;

public class Post {
    private int id;
    private Date createdDate;
    private String content;
    private String creator;
    private String timeAgo;

    public Post(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    @Override
    public String toString() {
        return "Post{" +
               ", postContent='" + content + '\'' +
               ", createdDate=" + createdDate +
               ", creator=" + creator +
               timeAgo +
               '}';
    }
}
