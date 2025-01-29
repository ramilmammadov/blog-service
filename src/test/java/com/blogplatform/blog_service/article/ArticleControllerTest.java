package com.blogplatform.blog_service.article;

import com.blogplatform.blog_service.config.SecurityConfig;
import com.blogplatform.blog_service.controller.article.ArticleController;
import com.blogplatform.blog_service.dto.request.CreateArticleRequest;
import com.blogplatform.blog_service.dto.response.ArticleResponse;
import com.blogplatform.blog_service.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    @WithMockUser(username = "testuser")
    void createArticle_ValidRequest_ReturnsCreated() throws Exception {
        CreateArticleRequest request = new CreateArticleRequest("Test Title", "Content", "Author");
        ArticleResponse response = ArticleResponse.builder().id(1L).title("Test Title").build();

        Mockito.when(articleService.createArticle(any())).thenReturn(response);

        mockMvc.perform(post("/api/articles")
                        .header("Authorization", "Bearer my-static-jwt-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
