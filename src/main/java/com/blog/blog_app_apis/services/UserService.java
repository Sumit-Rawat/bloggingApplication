package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.payloads.UserDto;

import java.util.List;

public interface UserService {

    // directly won't be giving entity to service. will create a DTO
    // in this we can only give required details of entity not all detail.
    // or even we can give extra field not present in entity.
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
