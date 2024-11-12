package com.example.twinlineTask.dto;


import java.util.UUID;

public class UserAndBlogResponseDto {


    private UUID userId;

    private UUID blogId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public UserAndBlogResponseDto() {

    }

    public UUID getUserId() {
        return userId;
    }

    public UserAndBlogResponseDto(UUID userId, UUID blogId) {
        this.userId = userId;
        this.blogId = blogId;
    }

    public UserAndBlogResponseDto(UUID userId) {
        this.userId = userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBlogId() {
        return blogId;
    }

    public void setBlogId(UUID blogId) {
        this.blogId = blogId;
    }

}
