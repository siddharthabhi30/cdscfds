package com.example.PostRecommendation.service;


import java.util.List;

import com.example.PostRecommendation.model.Comment;
import com.example.PostRecommendation.model.Post;
import com.example.PostRecommendation.model.User;


public interface UserService {
    public String userLoginAuthentication(String userName,String password);

    public List<Post> findAllPosts();
    public boolean modifyPost(Post post);

    public String addPost(Post post);

    public Post findPost(String postid);


    public User findUser(String userId);

    public void modifyUser(User user);

    public List<User> findAllUsers();

    public List<Post> findPostByUser(String emailId);

    public List<Comment> getComment(String postid);


    List<Comment> getMyComment(String emailId,String postId);
}
