package com.example.twinlineTask.dto;

import java.util.UUID;



public class BlogCreationOrEditDto {

    private UUID userId;

    private String blogData;

    private UUID blogId;

    public UUID getBlogId() {
        return blogId;
    }

    public void setBlogId(UUID blogId) {
        this.blogId = blogId;
    }



    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getBlogData() {
        return blogData;
    }

    public void setBlogData(String blogData) {
        this.blogData = blogData;
    }


    public BlogCreationOrEditDto(String blogData, UUID blogId) {
        this.blogData = blogData;
        this.blogId = blogId;
    }
}
