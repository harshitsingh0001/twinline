package com.example.twinlineTask.controller;

import com.example.twinlineTask.dto.BlogCreationOrEditDto;
import com.example.twinlineTask.dto.ResponseDto;
import com.example.twinlineTask.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63342")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createBlog(@RequestBody BlogCreationOrEditDto blogCreationDto) {
        return ResponseEntity.ok(blogService.createBlog(blogCreationDto.getUserId(), blogCreationDto.getBlogData()));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editBlog(@RequestBody BlogCreationOrEditDto blogDto) {
        return ResponseEntity.ok(blogService.editBlog(blogDto.getBlogId(), blogDto.getUserId(), blogDto.getBlogData()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteBlog(@RequestParam UUID userId, @RequestParam UUID blogId) {
        return ResponseEntity.ok(blogService.deleteBlog(blogId, userId));
    }

    @GetMapping("/report")
    public ResponseDto repost(@RequestParam UUID userId) {
        return blogService.repost(userId);
    }
}
