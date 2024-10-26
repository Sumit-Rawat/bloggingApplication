package com.blog.blog_app_apis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", nullable = false, length = 50)
    private String name;
    private String email;
    private String password;
    private String about;

    // one user can have do many posts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    // many to many ke case me ek or table extra banega relation handle karne ke liye
    // uska naam apne anusar rakhne ke liye '@JoinTable' use karenge.
    // table ka kon sa field foreign key jaisa kam karega usko btane ke liye
    // '@joinColumns' if writing in same class and 'inverseJoinColumns' if writing in different class.


    // e.g: User class ka jo table bnega 'user' naam se bnega and iska 'id' field foreign key jaisa kam karega
    // similaryly for 'Role' table

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name ="user_role",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "role", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).toList();

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
