package com.blog3.blog3.payload;


import lombok.Data;

import javax.validation.constraints.*;
import javax.validation.constraints.*;
import java.util.*;

@Data
public class PostDto {
    private long id;

    @NotEmpty
    @Size(min=2,message="post title should be of at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min=5,message="post description should be of at least 5 characters")
    private String description;
    @NotEmpty
    private String content;
}
