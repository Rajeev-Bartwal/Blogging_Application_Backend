package com.rajeev.Blogging_App.Repository;

import com.rajeev.Blogging_App.Model.Category;
import com.rajeev.Blogging_App.Model.Post;
import com.rajeev.Blogging_App.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository< Post,Integer> {

    Page<Post> findByUser(User user, Pageable p);
    Page<Post> findByCategory(Category category,Pageable p);

    @Query(value = "SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%',:keywords ,'%')) OR " +
            "LOWER(p.user.name) LIKE LOWER(CONCAT('%',:keywords ,'%')) OR " +
            "LOWER(p.category.categoryTitle) LIKE LOWER(CONCAT('%',:keywords ,'%'))")
    List<Post> searchByTitle(String keywords);
}
