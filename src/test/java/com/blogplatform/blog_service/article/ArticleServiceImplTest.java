package com.blogplatform.blog_service.article;

import com.blogplatform.blog_service.dto.response.ArticleResponse;
import com.blogplatform.blog_service.entity.Article;
import com.blogplatform.blog_service.exception.CustomEntityNotFoundException;
import com.blogplatform.blog_service.repository.ArticleRepository;
import com.blogplatform.blog_service.service.imp.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void getArticleById_WhenExists_ReturnsResponse() {
        Article article = Article.builder().id(1L).title("Test").build();
        when(articleRepository.findByIdOrThrow(1L)).thenReturn(article);

        ArticleResponse response = articleService.getArticleById(1L);
        assertEquals("Test", response.getTitle());
    }

    @Test
    void getArticleById_WhenNotExists_ThrowsException() {
        when(articleRepository.findByIdOrThrow(1L))
                .thenThrow(new CustomEntityNotFoundException("Article not found with id: 1"));
        assertThrows(CustomEntityNotFoundException.class, () -> articleService.getArticleById(1L));
    }
}
