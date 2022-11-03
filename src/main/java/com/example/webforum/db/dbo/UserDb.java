package com.example.webforum.db.dbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDb {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String full_name;
    @Column(name = "age")
    private int age;
    /** TODO: join the user table with the posts she made, and all messages she received.
     */
    /*
    @OneToMany(targetEntity = Post.class, fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_post_user_username", referencedColumnName = "username")
    @JsonIgnoreProperties("creator")
    private List<Post> posts;
    @OneToMany(targetEntity = Message.class, fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk1_message_user_username", referencedColumnName = "sender")
    @JsonIgnoreProperties("sender")
    private List<Post> myMessages;
*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
/*
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getMyMessages() {
        return myMessages;
    }

    public void setMyMessages(List<Post> myMessages) {
        this.myMessages = myMessages;
    }
*/
    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", full_name='" + full_name + '\'' +
               ", age=" + age +
        //       ", posts=" + posts +
        //       ", myMessages=" + myMessages +
               '}';
    }
}
