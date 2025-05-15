package com.rajeev.Blogging_App.Repository;

import com.rajeev.Blogging_App.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment , Integer> {
}
