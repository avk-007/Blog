package com.blog3.blog3.service.Impl;

import com.blog3.blog3.entity.Comment;
import com.blog3.blog3.entity.Post;
import com.blog3.blog3.exception.BLOGAPIEXCEPTION;
import com.blog3.blog3.exception.*;
import com.blog3.blog3.payload.CommentDto;
import com.blog3.blog3.repository.CommentRepository;
import com.blog3.blog3.repository.PostRepository;
import com.blog3.blog3.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.ID;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    //http://localhost:7070/api/posts/1/comments
    @Override
    public CommentDto saveComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        //retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found by Id:" + id));

//set post to comment entity
        comment.setPost(post);
        //comment entity to db
        Comment newcomment = commentRepository.save(comment);
        return mapToDto(newcomment);

    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        List<CommentDto> commentsById = byPostId.stream().map(n -> mapToDto(n)).collect(Collectors.toList());
        return commentsById;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found by id" + id)
        );
        //does comment exist for this id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found by id" + commentId));
        //does comment exist for the particular post
        // create new Blog APi exception here
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BLOGAPIEXCEPTION("Comment does not belong to post");

        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found by id" + id)
        );
        //does comment exist for this id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found by id" + commentId));
        //does comment exist for the particular post//create new Blog APi exception here
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BLOGAPIEXCEPTION("Comment does not belong to post");

        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long Id) {
       // throw exception
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found by id" + id)
        );
        //does comment exist for this id
        Comment comment = commentRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("comment not found by id" + Id));
        //does comment exist for the particular post//create new Blog APi exception here
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BLOGAPIEXCEPTION("Comment does not belong to post");

        }
        commentRepository.deleteById(Id);

    }


    //5-convert commentDto to entity then save createmethof here separately
    Comment mapToEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getName());
        return comment;
    }

    //8 convert comment Back to Dto
    CommentDto mapToDto(Comment comment) {
        CommentDto dto = mapper.map(comment, CommentDto.class);
//        CommentDto dto = new CommentDto();
//        dto.setId(comment.getId());
//        dto.setBody(comment.getBody());
//        dto.setEmail(comment.getEmail());
//        dto.setName(comment.getName());
        return dto;
    }
}


