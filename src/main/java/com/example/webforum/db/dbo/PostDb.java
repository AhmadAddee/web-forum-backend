package com.example.webforum.db.dbo;

import javax.persistence.*;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@Entity
@Table(name = "post")
public class PostDb {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "content")
    private String content;
    /** TODO: join all posts made by a user to that specific user.
     */
    /*
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "creator", referencedColumnName = "username")
    @JsonIgnoreProperties("posts")
    private User creator;
   */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
     */
    @Override
    public String toString() {
        return "Post{" +
               "id=" + id +
               //        ", creator=" + creator.toString() +
               ", createdDate=" + createdDate +
               ", content='" + content + '\'' +
               '}';
    }
}
