package com.example.webforum.db.dbo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class MessageDb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "sent_date")
    private Timestamp sentDate;

    @Column(name = "message_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver", updatable = false)
    @Fetch(FetchMode.JOIN)
    private UserDb receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", updatable = false)
    @Fetch(FetchMode.JOIN)
    private UserDb sender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDb getSender() {
        return sender;
    }

    public void setSender(UserDb sender) {
        this.sender = sender;
    }

    public UserDb getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDb receiver) {
        this.receiver = receiver;
    }
}
