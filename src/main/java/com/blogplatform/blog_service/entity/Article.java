package com.blogplatform.blog_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Article extends BaseEntity {
    @NotBlank
    @Size(max = 200)
    private String title;

    @Lob
    @NotBlank
    private String content;

    @NotBlank
    @Size(max = 100)
    private String author;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    private LocalDateTime publishedDate;
}