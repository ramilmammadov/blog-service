package com.blogplatform.blog_service.repository;

import com.blogplatform.blog_service.entity.BaseEntity;
import com.blogplatform.blog_service.exception.CustomEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    default T findByIdOrThrow(ID id) {
        return findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Resource not found with id: " + id));
    }

}
