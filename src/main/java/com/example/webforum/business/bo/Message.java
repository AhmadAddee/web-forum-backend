package com.example.webforum.business.bo;

import java.util.Date;

public class Message {
    private int id;
    private Date sentDate;
    private String content;
    private String sender;
    private String receiver;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
               "id=" + id +
               ", sentDate=" + sentDate +
               ", content='" + content + '\'' +
               ", sender='" + sender + '\'' +
               ", receiver='" + receiver + '\'' +
               '}';
    }
}
