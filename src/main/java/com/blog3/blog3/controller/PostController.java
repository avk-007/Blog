package com.blog3.blog3.controller;

import com.blog3.blog3.payload.PostDto;
import com.blog3.blog3.payload.PostResponse;
import com.blog3.blog3.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.xml.ws.RespectBindingFeature;
import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class PostController {

    private PostService postService;

    public PostController(PostService postService) {

        this.postService = postService;
    }

    //this is added to give admin permission
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto,
                                              BindingResult result) {
        if(result.hasErrors()) {
            //to remove the error we used <?> while using validation put this
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //    http://localhost:7070/api/posts?pageNo=0&pageSize=4&sortBy&sorDir=asc
    @GetMapping
    //public List<PostDto> getAllPosts(PostDto postdto){
    //now adding sortBy and sortDir here
    //adding pagination and sorting here
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir


    ) {
        //add page no and page size here
        //change    this to post Response=> List<PostDto> postDtos= postService.getAllposts(pageNo, pageSize,sortBy,sortDir);
        PostResponse postResponse = postService.getAllposts(pageNo, pageSize, sortBy, sortDir);
        return postResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto, @PathVariable("id") long id) {
        PostDto dto = postService.updatePost(postdto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {

        postService.deletePost(id);

        return new ResponseEntity<>("post deleted", HttpStatus.OK);
    }


}
