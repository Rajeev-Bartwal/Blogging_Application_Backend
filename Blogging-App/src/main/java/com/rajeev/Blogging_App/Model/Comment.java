package com.rajeev.Blogging_App.Model;


import com.rajeev.Blogging_App.Payloads.PostDTO;
import com.rajeev.Blogging_App.Payloads.UserDTO;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String content;

    @ManyToOne
    private Post post;

//    @ManyToOne
//    private User user;
}
