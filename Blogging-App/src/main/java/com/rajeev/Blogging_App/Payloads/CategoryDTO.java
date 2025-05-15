package com.rajeev.Blogging_App.Payloads;


import com.rajeev.Blogging_App.Model.Post;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer categoryId;

    @Size(min = 4 , message = "Title should be minimum of 4 characters")
    private String categoryTitle;

    @Size(min = 10, message = " Description should be minimum of 10 characters")
    private String categoryDesc;

}
