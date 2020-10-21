package com.example.PostRecommendation.dao;



import java.util.List;

import com.example.PostRecommendation.model.Comment;
import com.example.PostRecommendation.model.Post;
import com.example.PostRecommendation.model.User;



public interface UserDao {
    public User readuserById(String userName);

    public List<Post> getAllPosts();

    public Post updatePost(Post post);

    public String addPost(Post post);

    public Post readPostById(String postid);

    public void updateUser(User user);

    public List<User> getAllUsers();

    public List<Post> getPostsByUser(String emailId);

    public List<Comment> getComment(String postid);

    List<Comment> getMyComment(String emailId,String postId);
}
