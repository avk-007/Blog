package com.blog3.blog3.repository;

import com.blog3.blog3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
