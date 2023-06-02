package com.blog3.blog3.service;

import com.blog3.blog3.payload.PostDto;
import com.blog3.blog3.payload.PostResponse;

import java.util.List;

public interface PostService {
    

    PostDto createPost(PostDto postDto);

    PostResponse getAllposts(int pageNo, int pageSize, String SortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postdto, long id);

    void deletePost(long id);
}
