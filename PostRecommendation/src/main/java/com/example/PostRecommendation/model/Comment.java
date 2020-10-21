package com.example.PostRecommendation.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "COMMENT_INFO")
@Component("comment")
@Scope(scopeName = "prototype")
public class Comment {

    @Id
    @Column(name = "COMMENT_ID")
    private String commentId;

    @Column(name = "data")
    private String data;


    @Column(name = "TITLE")
    private String title;


    @JsonIgnoreProperties("comment")
    @ManyToOne
    @JoinColumn(name = "POST_Id", referencedColumnName = "POST_ID")
    private Post post;

    public String getCommentId() {
        return commentId;
    }




    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }




    public String getData() {
        return data;
    }




    public void setData(String data) {
        this.data = data;
    }




    public String getTitle() {
        return title;
    }




    public void setTitle(String title) {
        this.title = title;
    }




    public Post getPost() {
        return post;
    }




    public void setPost(Post post) {
        this.post = post;
    }




    public Comment(String commentId, String data, String title, Post post) {
        super();
        this.commentId = commentId;
        this.data = data;
        this.title = title;
        this.post = post;
    }
    public Comment() {

    }




    @Override
    public String toString() {
        return "Comment [commentId=" + commentId + ", data=" + data + ", title=" + title + ", post=" + post + "]";
    }














}
