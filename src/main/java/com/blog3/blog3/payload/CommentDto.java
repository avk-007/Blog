package com.blog3.blog3.payload;

import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
   // private Long postId;
}
