package com.rajeev.Blogging_App.Services;


import com.rajeev.Blogging_App.Payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface UserService {

    UserDTO registerNewUser(UserDTO userDto);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user,Integer userId);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);
}
