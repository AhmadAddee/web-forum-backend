package com.example.webforum.db.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "user")
public class UserDb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "age")
    private int age;

    @OneToMany(targetEntity = PostDb.class, mappedBy = "creator", fetch = FetchType.LAZY)
    private List<PostDb> postDbList;

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

    public List<PostDb> getPostDbList() {
        return postDbList;
    }

    public void setPostDbList(List<PostDb> postDbList) {
        this.postDbList = postDbList;
    }
}
