package com.example.PostRecommendation.service;

import com.example.PostRecommendation.dao.UserDao;
import com.example.PostRecommendation.model.Comment;
import com.example.PostRecommendation.model.Post;
import com.example.PostRecommendation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("userService")
@Scope(scopeName = "singleton")
public class UserServiceImpl  implements  UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public String userLoginAuthentication(String userName,String password) {
        System.out.println("algo here ser");
        User result=userDao.readuserById(userName);
        if(result==null) {
            System.out.println("no user is there");
            return "false";
        }
        if(result.getPassword().equals(password)) {
            return "true";
        }
        return "false";

    }

    @Override
    public List<Post> findAllPosts() {
        return userDao.getAllPosts();
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifyPost(Post post) {


        Post result=userDao.updatePost(post);
        return (result!=null) ? true : false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addPost(Post post) {
        String resultString=userDao.addPost(post);
        return resultString;
    }

    @Override
    public Post findPost(String postid) {
        Post post=userDao.readPostById(postid);
        if(post!=null) {
            return post;
        }
        return null;
    }

    @Override
    public User findUser(String userId) {
        return userDao.readuserById(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyUser(User user) {
        userDao.updateUser(user);

    }

    @Override
    public List<User> findAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<Post> findPostByUser(String emailId) {
        return userDao.getPostsByUser(emailId);
    }

    @Override
    public List<Comment> getComment(String postid) {
        return userDao.getComment(postid);
    }

    @Override
    public List<Comment> getMyComment(String emailId,String postId) {
        return userDao.getMyComment(emailId,postId);
    }
}
