package com.blog.blog_app_apis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String image;

    private Date postDate;

    //A post will be associated/created by user
    @ManyToOne
    private User user; //post kis user ne kiya

    //A post will be associated with a category or many post can be mapped to a single category
    @ManyToOne
    private Category category; //post kis category ka h

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments=new HashSet<>();

}
