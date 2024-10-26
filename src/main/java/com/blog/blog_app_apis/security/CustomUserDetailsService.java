package com.blog.blog_app_apis.security;

import com.blog.blog_app_apis.entities.User;
import com.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_app_apis.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    //load user from username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // load user from db by email
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email: " + username, 0));

        return user;
    }
}
