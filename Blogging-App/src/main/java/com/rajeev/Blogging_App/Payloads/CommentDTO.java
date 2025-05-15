package com.rajeev.Blogging_App.Payloads;

import com.rajeev.Blogging_App.Model.Post;
import com.rajeev.Blogging_App.Model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer commentId;

    private String content;

}
