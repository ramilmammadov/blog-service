package com.blogplatform.blog_service.service.imp;

import com.blogplatform.blog_service.dto.request.CreateArticleRequest;
import com.blogplatform.blog_service.dto.response.ArticleResponse;
import com.blogplatform.blog_service.entity.Article;
import com.blogplatform.blog_service.entity.ArticleStatus;
import com.blogplatform.blog_service.exception.CustomEntityNotFoundException;
import com.blogplatform.blog_service.repository.ArticleRepository;
import com.blogplatform.blog_service.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponse createArticle(CreateArticleRequest request) {
        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .publishedDate(LocalDateTime.now())
                .build();

        Article savedArticle = articleRepository.save(article);
        return convertToResponse(savedArticle);
    }

    @Override
    public ArticleResponse getArticleById(Long id) {
        Article article = articleRepository.findByIdOrThrow(id);
        return convertToResponse(article);
    }

    private ArticleResponse convertToResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .publishedDate(article.getPublishedDate())
                .build();
    }

    @Override
    public List<ArticleResponse> getArticlesPublishedBefore(LocalDateTime date) {
        return articleRepository.findByPublishedDateBefore(date)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> searchArticles(String keyword, LocalDateTime startDate, LocalDateTime endDate) {
        return articleRepository.searchByKeywordAndDateRange(keyword, startDate, endDate);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not foun with id: " + id));
        article.setStatus(ArticleStatus.DELETED);
        articleRepository.save(article);
    }

}
