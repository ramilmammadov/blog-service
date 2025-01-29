package com.blogplatform.blog_service.dto.response;


import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter @Builder
public class ArticleResponse {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime publishedDate;
}