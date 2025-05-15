package com.rajeev.Blogging_App.Payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rajeev.Blogging_App.Model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    private String title;

    private  String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date addedDate;

    private String imageName;

    private UserDTO user;

    private CategoryDTO category;

    private List<CommentDTO> comments;
}
