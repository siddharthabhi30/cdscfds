package com.example.PostRecommendation.model;


import java.util.HashSet;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "USER_INFO")
@Component("user")
@Scope(scopeName = "prototype")
public class User {
    @Id
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "POSITIVE")
    private double positive;

    @Column(name = "NEGATIVE")
    private double negative;


    @Autowired
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private Set<Post> post= new HashSet<Post>();

    public double getPositive() {
        return positive;
    }
    public void setPositive(double positive) {
        this.positive = positive;
    }
    public double getNegative() {
        return negative;
    }
    public void setNegative(double negative) {
        this.negative = negative;
    }





    public Set<Post> getPost() {
        return post;
    }
    public void setPost(Set<Post> post) {
        this.post = post;
    }
    public void addPost(Post post1) {
        post.add(post1);
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    @Override
    public String toString() {
        return "User [userName=" + userName + ", password=" + password + ", positive=" + positive + ", negative="
                + negative + ", post=" + post + "]";
    }
    public User(String userName, String password, Post post) {
        super();
        this.userName = userName;
        this.password = password;

    }
    public User() {

    }



}
