package com.rajeev.Blogging_App.Services.Impl;

import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Model.Comment;
import com.rajeev.Blogging_App.Model.Post;
import com.rajeev.Blogging_App.Model.User;
import com.rajeev.Blogging_App.Payloads.CommentDTO;
import com.rajeev.Blogging_App.Payloads.PostDTO;
import com.rajeev.Blogging_App.Payloads.UserDTO;
import com.rajeev.Blogging_App.Repository.CommentRepo;
import com.rajeev.Blogging_App.Repository.PostRepository;
import com.rajeev.Blogging_App.Repository.UserRepo;
import com.rajeev.Blogging_App.Services.CommentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService{


    @Autowired
    private CommentRepo  commentRepo;

    @Autowired
    private ModelMapper mod;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepo userRepo;


    @Override
    public CommentDTO addComment(Integer postId, CommentDTO commentDto ) {

        Post post =postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" ,"PostId" ,postId));
//        User user = userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("User", "userId", userId));
//
        Comment comment = mod.map(commentDto, Comment.class);
        comment.setPost(post);
//        comment.setUser(user);

        CommentDTO com = mod.map(commentRepo.save(comment), CommentDTO.class);
        return com;
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Post" ,"PostId" ,commentId));

        commentRepo.delete(com);
    }
}
