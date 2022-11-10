/*

package com.example.webforum.db.dbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "message")
public class MessageDb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int id;
    @Column(name = "sent_date")
    private Date sentDate;
    @Column(name = "message_content")
    private String content;
    // TODO: join all messages received by a specific user to that user.


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "sender", referencedColumnName = "username")
@JsonIgnoreProperties("myMessages")
private UserDb sender;
@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "receiver", referencedColumnName = "username")
@JsonIgnoreProperties("myMessages")
private UserDb receiver;


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

@Override
public String toString() {
        return "MessageDb{" +
        "id=" + id +
        ", sentDate=" + sentDate +
        ", content='" + content + '\'' +
        ", sender=" + sender +
        ", receiver=" + receiver +
        '}';
        }
        }

 */