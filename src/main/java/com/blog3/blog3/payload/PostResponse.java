package com.blog3.blog3.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    //this dto is  made to define the pagination details
//because controller returnig  back postDto over entity is exposed.
    private List<PostDto>content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean Last;
}
