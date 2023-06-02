package com.blog3.blog3.service.Impl;

import com.blog3.blog3.entity.Post;
import com.blog3.blog3.exception.ResourceNotFoundException;
import com.blog3.blog3.payload.PostDto;
import com.blog3.blog3.payload.PostResponse;
import com.blog3.blog3.repository.PostRepository;
import com.blog3.blog3.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    //model mapper uses its own java library not springs
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;

}

    @Override
    public PostDto createPost(PostDto postDto) {
        //PostDto to entity
        //PostDto is a entity object
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        Post savedPost = postRepository.save(post);
        Post post = mapToEntity(postDto);
        //entity to dto
//        PostDto dto=new PostDto();
//        dto.setId(savedPost.getId());
//        dto.setTitle(savedPost.getTitle());
//        dto.setDescription(savedPost.getDescription());
//        dto.setContent(savedPost.getContent());
        //entity to dto
        Post savedPost = postRepository.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public PostResponse getAllposts(int pageNo, int pageSize, String sortBy, String sortDir) {
        //using ternary operator here (this is more like if else condtion we're going
        // to compare bw condition,to tun as true false ,true=asc,false=desc

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //create pageable instance
        PageRequest pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> content = postRepository.findAll(pageable);
        //convert back to list now
        List<Post> posts = content.getContent();

        List<PostDto> dtos = posts.stream().map(n -> mapToDto(n)).collect(Collectors.toList());
        //now create new postResponse that we made in PostResponse Dto class
        PostResponse postResponse = new PostResponse();

        postResponse.setContent(dtos);

        postResponse.setPageNo(content.getNumber());
        postResponse.setPageSize(content.getSize());
        postResponse.setTotalElements(content.getTotalElements());
        postResponse.setTotalPages(content.getTotalPages());
        postResponse.setLast(content.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("post not found by id:" + id));
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    //here postdto is there
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("post not found by id:" + id));
        //dto to entity
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        //map to dto
        PostDto dto = mapToDto(updatedPost);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("post not found by id:"+id)
        );
        postRepository.deleteById(id);
    }

    //maptoentity
   Post mapToEntity(PostDto postDto) {
        //using Modelmapper now instead of normal traditional method
       Post post = mapper.map(postDto, Post.class);
       //     Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
       return post;
   }
   //MapToDto
   PostDto mapToDto(Post post){
       PostDto dto = mapper.map(post, PostDto.class);
//PostDto dto=new PostDto();
//dto.setId(post.getId());
//dto.setTitle(post.getTitle());
//dto.setDescription(post.getDescription());
//dto.setContent(post.getContent());
       return dto;
   }

}
