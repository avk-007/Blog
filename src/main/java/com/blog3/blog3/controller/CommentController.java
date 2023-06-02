package com.blog3.blog3.controller;

import com.blog3.blog3.payload.CommentDto;
import com.blog3.blog3.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    //http://localhost:7070/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createcomment(@PathVariable("postId") long postId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.saveComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //for findpostById
    //http://localhost:7070/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable("postId") long postId) {
        List<CommentDto> commentDto = commentService.getCommentByPostId(postId);

        return commentDto;
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    //get comment by commentId
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId,
                                                     @PathVariable("commentId") long commentId) {
        CommentDto dto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<String>deleteComment(@PathVariable("postId")long postId,
                                               @PathVariable("Id")long Id){
        commentService.deleteComment(postId,Id);

        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }

}
