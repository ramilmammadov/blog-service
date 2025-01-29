package com.blogplatform.blog_service.repository;

import com.blogplatform.blog_service.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    List<Article> findByPublishedDateBefore(LocalDateTime date);

    @Query("SELECT a FROM Article a WHERE " +
            "(:keyword IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:startDate IS NULL OR a.publishedDate >= :startDate) " +
            "AND (:endDate IS NULL OR a.publishedDate <= :endDate) " +
            "AND a.status = 'ACTIVE'")
    List<Article> searchByKeywordAndDateRange(
            @Param("keyword") String keyword,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}