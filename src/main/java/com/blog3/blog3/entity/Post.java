package com.blog3.blog3.entity;

import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table
        (name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    // verify twice here while writing mapping
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment>comments=new HashSet<>();
}
