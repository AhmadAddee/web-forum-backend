package com.example.webforum.db.dbo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class PostDb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator", updatable = false)
    @Fetch(FetchMode.JOIN)
    private UserDb creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDb getCreator() {
        return creator;
    }

    public void setCreator(UserDb creator) {
        this.creator = creator;
    }

}
