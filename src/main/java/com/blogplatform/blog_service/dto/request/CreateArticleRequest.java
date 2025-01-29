package com.blogplatform.blog_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateArticleRequest {
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 200, message = "Title must be less than 200 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author name must be less than 100 characters")
    private String author;
}