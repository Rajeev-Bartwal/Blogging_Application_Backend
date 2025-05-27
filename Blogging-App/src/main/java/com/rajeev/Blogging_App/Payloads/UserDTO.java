package com.rajeev.Blogging_App.Payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Name should be minimum of 4 characters")
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10,message = "Password must be min of 3 and max of 10 characters")
    private String password;

    @NotEmpty
    private String about;

//    private List<CommentDTO> comments = new ArrayList<>();
}
