package com.blogplatform.blog_service.service;

import com.blogplatform.blog_service.dto.request.CreateArticleRequest;
import com.blogplatform.blog_service.dto.response.ArticleResponse;
import com.blogplatform.blog_service.entity.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleService {
    ArticleResponse createArticle(CreateArticleRequest request);
    ArticleResponse getArticleById(Long id);
    List<ArticleResponse> getArticlesPublishedBefore(LocalDateTime date);
    List<Article> searchArticles(String keyword, LocalDateTime startDate, LocalDateTime endDate);
    void deleteArticle(Long id);
}