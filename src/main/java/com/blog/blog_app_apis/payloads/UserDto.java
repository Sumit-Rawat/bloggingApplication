package com.blog.blog_app_apis.payloads;

import com.blog.blog_app_apis.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotEmpty
    @Size(max=30, message = "Max 30 characters allowed for username !!")
    private String name;
    @Email(message="Invalid Email !!")
    @Pattern(regexp = ".*?@?[^@]*\\.+", message = "Invalid Email !!")
    private String email;
    @NotEmpty
    @Size(min=8, max=20, message = "Password should be of minimum 8 character and maximum 20 characters !!")
    private String password;
    @NotNull
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}
