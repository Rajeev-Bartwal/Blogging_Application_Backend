package com.rajeev.Blogging_App.Controller;

import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.UserDTO;
import com.rajeev.Blogging_App.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/")
    public ResponseEntity<UserDTO> createUSer(@Valid @RequestBody UserDTO userDto)
    {
        return new ResponseEntity<>(userService.createUser(userDto) , HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto ,@PathVariable Integer userId){
       UserDTO user = userService.updateUser(userDto,userId);
       return  new ResponseEntity<>(user , HttpStatus. OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        if(userService.getUserById(userId) != null) {
            userService.deleteUser(userId);
            return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
        }else
           throw new ResourceNotFoundException("user" ,"Id" ,userId);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUSerById(@PathVariable Integer userId){
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }
}

