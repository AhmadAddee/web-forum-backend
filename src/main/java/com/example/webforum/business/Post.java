package com.example.webforum.business;

import java.util.Date;

/**
 * Nothing more than a DTO that is an amalgamation of the two entities User and Post.
 * */

public class Post {
    private int postId;
    private String creatorName;
    private String postContent;
    private Date createdDate;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Post{" +
               "postId=" + postId +
               ", creatorName='" + creatorName + '\'' +
               ", postContent='" + postContent + '\'' +
               ", createdDate=" + createdDate +
               '}';
    }
}
