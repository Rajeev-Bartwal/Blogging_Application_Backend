package com.rajeev.Blogging_App.Repository;

import com.rajeev.Blogging_App.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category , Integer> {
}
