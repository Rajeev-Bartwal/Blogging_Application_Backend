package com.rajeev.Blogging_App.Services;

import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    CommentDTO addComment(Integer postId , CommentDTO commentDto);

    void deleteComment(Integer commentId);
}
