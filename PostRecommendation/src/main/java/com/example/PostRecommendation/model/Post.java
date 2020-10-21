package com.example.PostRecommendation.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Post")
@Component("post")
@Scope(scopeName = "prototype")
public class Post {
    @Column(name = "positive")
    private double positive;


    @Column(name = "negative")
    private double negative;

    @Column(name = "data")
    private String data;

    @Id
    @Column(name = "POST_Id")
    private String postId;



    @JsonIgnoreProperties("post")
    @ManyToOne
    @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME")
    private User user;

    @Autowired
    @OneToMany(mappedBy = "post",cascade = {CascadeType.ALL})
    private Set<Comment> comment= new HashSet<Comment>();


    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public void addComment(Comment comment1) {
        comment.add(comment1);
    }

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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Post [positive=" + positive + ", negative=" + negative + ", data=" + data + ", postId=" + postId
                + ", user=" + user + "]";
    }

    public Post(String data, User user) {
        super();
        this.data = data;

        this.user = user;
    }
    public Post() {

    }



}
