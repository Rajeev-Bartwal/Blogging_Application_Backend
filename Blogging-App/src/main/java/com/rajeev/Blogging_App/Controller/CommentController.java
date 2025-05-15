package com.rajeev.Blogging_App.Controller;


import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.CommentDTO;
import com.rajeev.Blogging_App.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer postId,
                                                 @RequestBody CommentDTO comment){

        CommentDTO comm = commentService.addComment(postId, comment);

        return new ResponseEntity<CommentDTO>( comm , HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("comment deleted successfully" , true) , HttpStatus.OK);
    }
}
