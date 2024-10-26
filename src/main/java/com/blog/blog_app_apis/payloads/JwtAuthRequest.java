package com.blog.blog_app_apis.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username; // email for unique

    private String password;
}
