package com.example.twinlineTask.repos;

import com.example.twinlineTask.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {

    List<Blog> findByUserId(UUID userId);

}
