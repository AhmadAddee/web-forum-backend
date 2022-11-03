package com.example.webforum.business;

public class User {
    private String username;
    private String fullName;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", fullName='" + fullName + '\'' +
               ", age=" + age +
               '}';
    }
}
