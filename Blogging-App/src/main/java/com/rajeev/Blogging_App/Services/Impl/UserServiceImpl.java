package com.rajeev.Blogging_App.Services.Impl;

import com.rajeev.Blogging_App.Configuration.AppConstants;
import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Model.Role;
import com.rajeev.Blogging_App.Model.User;
import com.rajeev.Blogging_App.Payloads.UserDTO;
import com.rajeev.Blogging_App.Repository.RoleRepo;
import com.rajeev.Blogging_App.Repository.UserRepo;
import com.rajeev.Blogging_App.Services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDTO registerNewUser(UserDTO userDto) {

        User user = modelMapper.map(userDto , User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepo.findById(AppConstants.NORMAL_ROLE).get();
        user.getRoles().add(role);

        return modelMapper.map(userRepo.save(user) , UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {

//        User user = userDtoToUser(userDto);
        User user = modelMapper.map(userDto , User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);

        return  modelMapper.map(savedUser , UserDTO.class);
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
        return modelMapper.map(updatedUser , UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user =  userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user" ,"Id" , userId));
        return  modelMapper.map(user , UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users =  userRepo.findAll();

//        return users.stream().map(this::userToUserDTO).collect(Collectors.toList())
          return  users.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user" ,"userId" , userId));

        user.getRoles().clear();
        userRepo.save(user);

        userRepo.delete(user);
    }
}
