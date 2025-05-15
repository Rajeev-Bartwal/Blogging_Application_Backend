package com.rajeev.Blogging_App.Repository;

import com.rajeev.Blogging_App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User , Integer> {

    User findByEmail(String email);
}
