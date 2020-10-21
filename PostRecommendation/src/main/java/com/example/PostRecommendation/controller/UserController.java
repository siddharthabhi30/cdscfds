package com.example.PostRecommendation.controller;


import com.example.PostRecommendation.model.Comment;
import com.example.PostRecommendation.model.Post;
import com.example.PostRecommendation.model.User;
import com.example.PostRecommendation.service.JavaMailService;
import com.example.PostRecommendation.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="user")
@CrossOrigin
public class UserController {

    @Autowired
    private JavaMailService javaMailService;


    @Autowired
    private UserService service;


    @GetMapping(path="/")
    public String welcome(){
        return "hei";
    }


    @GetMapping(path="/sendMail/{emailId}/{data}")
    public String sendMail(@PathVariable("emailId") String emailId,@PathVariable("data") String data){
        System.out.println("tyring to senf mail   to   "+emailId);
        javaMailService.sendEmail(emailId,data);
        return "mail sent";
    }

    @GetMapping(path="/checkUser/{emailId}")
    public String checkUser(@PathVariable("emailId") String emailId){
       User user=service.findUser(emailId);
       if(user==null){
           return "false";
       }
        return "true";
    }



    @GetMapping(path = "/{emailId}/{password}")
    public String customerLoginAuthentication(@PathVariable("emailId") String emailId,
                                              @PathVariable("password") String password) {
        System.out.println(emailId+" login is happening "+password);
        //return "fads";
        return service.userLoginAuthentication(emailId, password);
    }

    @GetMapping(path="/viewPosts.do")
    public List<Post> getAllPosts(){
        return service.findAllPosts();
    }


    @GetMapping(path="/viewPostsByUser/{emailId}")
    public List<Post> getAllPostsByUser(@PathVariable("emailId") String emailId){

        return service.findPostByUser(emailId);
    }
    @GetMapping(path="/viewPositivePostsByUser/{emailId}")
    public List<Post> getAllPostsByUserPositive(@PathVariable("emailId") String emailId){

      List<Post> posts= service.findPostByUser(emailId);


      return posts;
    }

    @GetMapping(path="/viewUsers.do")
    public List<User> getAllUsers(){
        return service.findAllUsers();
    }


    @PostMapping(path="/addPost.do/{emailId}")
    public String addPost(@PathVariable("emailId") String emailId,@RequestBody Post post) {
        System.out.println("email id"+emailId);
        User user=service.findUser(emailId);
        user.addPost(post);
        post.setUser(user);

        //System.out.println(post);
        //System.out.println(user);

        //service.modifyPost(post);
        try {
            service.modifyUser(user);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "err";
        }

        return "hello";
        //return service.addPost(post);
    }



    @PutMapping(path="/updatePost.do")
    public String updatePost(@RequestBody Post post) {
        System.out.println("fedfd"+"  "+post.getPostId());
        Post post1=service.findPost(post.getPostId());
        User user=service.findUser(post1.getUser().getUserName());
        System.out.println("user is   "+ user.getUserName());
        post1.setData(post.getData());
        post1.setNegative(post.getNegative());
        post1.setPositive(post.getPositive());
        System.out.println(post1.getData());
        //user.addPost(post1);
        service.modifyUser(user);
        //service.modifyPost(post1);
        //Post post1=service.findPost(post.getPostId());
        //System.out.println(post.getPostId());
        //post1.setData("put is in the secopnd  schnage");
        //service.modifyPost(post1);
        //System.out.println(post1);
        //	User user=service.findUser(emailId);
        //post.setUser(user);
        //user.addPost(post);

        //System.out.println(post);
        //System.out.println(user);

        //service.modifyPost(post);
        //service.modifyUser(user);
        return "hello";
        //return service.addPost(post);
    }

    @GetMapping(path = "updateUser/{emailId}/{positive}/{negative}")
    public String updateUser(@PathVariable("emailId") String emailId,
                             @PathVariable("positive") double positive,@PathVariable("negative") double negative) {
        User user=service.findUser(emailId);
        user.setNegative(negative);
        user.setPositive(positive);
        service.modifyUser(user);
        System.out.println(emailId+"  user is being updated "+negative);
        return "user updated";

    }

    @GetMapping(path = "findPost/{postid}")
    public Post findPost(@PathVariable("postid") String postid) {

        return service.findPost(postid);
    }

    @GetMapping(path = "test")
    public User Testinge() {

        Post post1=service.findPost("post92");
        return post1.getUser();

    }
    @PostMapping(path="/addComment.do/{postId}")
    public String addComment(@PathVariable("postId") String postId,@RequestBody Comment comment) {
        System.out.println("Post_id"+postId);
        Comment comment2=new Comment();
        comment2.setData(comment.getData());
        comment2.setCommentId(comment.getCommentId());
        comment2.setTitle(comment.getTitle());
        Post post=service.findPost(postId);
        post.addComment(comment);
        comment.setPost(post);
        System.out.println(comment.getTitle()+"   this is the tirle");
        User user=service.findUser(comment.getTitle());

        //System.out.println(post);
        //System.out.println(user);

        //service.modifyPost(post);
        try {
            service.modifyUser(user);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "err";
        }

        return "hello";
        //return service.addPost(post);
    }
    @GetMapping(path = "getComment/{postid}")
    public List<Comment> getComment(@PathVariable("postid") String postid) {

        return service.getComment(postid);
    }

    @GetMapping(path = "getMyComment/{emailId}/{postId}")
    public List<Comment> getMyComment(@PathVariable("emailId") String emailId,@PathVariable("postId") String postId) {

        return service.getMyComment(emailId,postId);
    }





}
