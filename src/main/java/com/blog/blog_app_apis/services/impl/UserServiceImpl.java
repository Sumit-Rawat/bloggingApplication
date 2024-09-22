package com.blog.blog_app_apis.services.impl;

import com.blog.blog_app_apis.entities.User;
import com.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_app_apis.payloads.UserDto;
import com.blog.blog_app_apis.repositories.UserRepo;
import com.blog.blog_app_apis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // save karte samay user pass karenge but parameter me 'dto' ka object pass kar rhe
        // isliye phle hmko convert karna hoga dto to user.

        User user=userDtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return userToUserDto(savedUser); // return karte samay phir change kar denge kyuki return type is UserDto
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        // first get the user

        // user h tb us user ka detail aa jayega nhi to exception(custom) throw kar dega(using lambda function).
        // exception me 3 para: kon sa resource nhi mil rha, kon sa field nhi mil rha , field ka value

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","User Id" , userId));

        // now update this 'user' with provided detail i.e 'userDto'.
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser=this.userRepo.save(user);
        return userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));

        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=this.userRepo.findAll();

        // hmko har ek user ko 'userDto' me convert karna hoga and usko return karna hoga list me.

        List<UserDto> userDtoList = userList.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        //get the user
        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));

        //if user exists then delete
        this.userRepo.delete(user);

    }

    public User userDtoToUser(UserDto userDto)
    {
//        User user=new User();
//
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        //instead of doing object conversion manually we will use model mapper for mapping
        //will use map method which takes source object and the destination class to which the source object need to be mapped

        User user=this.modelMapper.map(userDto,User.class);

        return user;
    }

    public UserDto userToUserDto(User user)
    {
//        UserDto userDto=new UserDto();
//
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        return this.modelMapper.map(user,UserDto.class);
    }


}
