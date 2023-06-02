package com.blog3.blog3.service;

import com.blog3.blog3.payload.CommentDto;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentService {
    //save comment or addcomment or createcomment all are same
    CommentDto saveComment(long postId, CommentDto commentDto);

    //for  findPostById
    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);
    //(flow-url postid and comment id will go to service layer,serice layer(post id and commnet id take and
    // then  goes to service impl & and post and comment is exist or not and matching postid and commentid in the main table
    // and throw the blog api exception)

    CommentDto updateComment(Long postId, long commentId, CommentDto commentDto);



     void deleteComment(long postId, long Id);
}