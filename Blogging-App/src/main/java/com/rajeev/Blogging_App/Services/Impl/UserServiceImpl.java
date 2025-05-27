package com.rajeev.Blogging_App.Services.Impl;

import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Model.User;
import com.rajeev.Blogging_App.Payloads.UserDTO;
import com.rajeev.Blogging_App.Repository.UserRepo;
import com.rajeev.Blogging_App.Services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {

        User user = userDtoToUser(userDto);
        User savedUser = userRepo.save(user);

        return  userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User oldUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User" , "Id", userId));

        oldUser.setPassword(userDto.getPassword());
        oldUser.setName(userDto.getName());
         oldUser.setEmail(userDto.getEmail());
        oldUser.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(oldUser);
        return userToUserDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user =  userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user" ,"Id" , userId));
        return  userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users =  userRepo.findAll();

        return users.stream().map(this::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepo.deleteById(userId);
    }

    private User userDtoToUser(UserDTO userDTO){

//   using ModelMapper
        User user = modelMapper.map(userDTO , User.class);



//        Converting The UsrDto to USer Manually.
//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setAbout(userDTO.getAbout());
//        user.setPassword(userDTO.getPassword());

        return user;
    }

    public  UserDTO userToUserDTO(User user){

//   using ModelMapper
        UserDTO userDto =  modelMapper.map(user , UserDTO.class);


//        Converting the user to USerDTo manually
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());

        return  userDto;
    }
}
