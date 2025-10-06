package com.rajeev.Blogging_App.Payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rajeev.Blogging_App.Model.Comment;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    @Size(min = 4, message = "Title should be at least 4 characters")
    private String title;

    @Size(min = 25, message = "Content should be at least 25 characters")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date addedDate;

    private String imageName;

    private UserDTO user;

    private CategoryDTO category;

    private List<CommentDTO> comments;
}
