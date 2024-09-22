package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.entities.User;
import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.UserDto;
import com.blog.blog_app_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST - create user

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createdUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uId)
    {
        // agar naam same rakh rhe ho to pathVariable ke bracket me likhna jaroori nhi h.
        // else put path variable name in quotes in parenthesis and get thet in any variable name you want
        UserDto updatedUserDto=this.userService.updateUser(userDto,uId);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }


    //DELETE - delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
    {
        this.userService.deleteUser(userId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }

    //GET - get all users

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers()
    {
        List<UserDto> users=this.userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    //Get - get user by id

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
    {
        UserDto user=this.userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }



}
