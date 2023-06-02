package com.blog3.blog3.repository;

import com.blog3.blog3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
 //to get comments by Id we are using Repo layer because by default it not there in repo by default
    // we can use any of the methods by taking any variable with the id i.e- findByname,findbyEmail,

    // but we're using findpostById
    List<Comment>findByPostId(long postId);
}
