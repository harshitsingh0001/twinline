package com.example.twinlineTask.service;

import com.example.twinlineTask.dto.ResponseDto;
import com.example.twinlineTask.dto.UserAndBlogResponseDto;
import com.example.twinlineTask.entities.Blog;
import com.example.twinlineTask.entities.Users;
import com.example.twinlineTask.repos.BlogRepository;
import com.example.twinlineTask.repos.UserRepository;
import com.example.twinlineTask.utilities.messageutility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseDto createBlog(UUID userId, String blogData) {
        UserAndBlogResponseDto userAndBlogResponseDto=new UserAndBlogResponseDto();
        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return Utility.createFailureResponse("User not found!", null);
        }
        Users user = userOptional.get();
        Blog blog = new Blog();
        blog.setBlogData(blogData);
        blog.setUser(user);
        blogRepository.save(blog);
        userAndBlogResponseDto.setBlogId(blog.getId());
        userAndBlogResponseDto.setUserId(userId);
        userAndBlogResponseDto.setMessage("Blog created successfully!");
        return Utility.createSuccessResponse("Blog created successfully!", userAndBlogResponseDto);
    }

    public ResponseDto editBlog(UUID blogId, UUID userId, String newBlogData) {
        UserAndBlogResponseDto userAndBlogResponseDto=new UserAndBlogResponseDto();
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isEmpty()) {
            userAndBlogResponseDto.setMessage("Blog not found!");
            return Utility.createFailureResponse("Blog not found!", userAndBlogResponseDto);
        }

        Blog blog = blogOptional.get();
        if (!blog.getUser().getId().equals(userId)) {
            userAndBlogResponseDto.setMessage("Unauthorized user! You can only edit your own blog posts.");
            return Utility.createFailureResponse("Unauthorized user! You can only edit your own blog posts.", userAndBlogResponseDto);
        }
        blog.setBlogData(newBlogData);
        blogRepository.save(blog);
         userAndBlogResponseDto.setUserId(userId);
         userAndBlogResponseDto.setBlogId(blogId);
         userAndBlogResponseDto.setMessage("Blog updated successfully!");
        return Utility.createSuccessResponse("Blog updated successfully!", userAndBlogResponseDto);
    }

    public ResponseDto deleteBlog(UUID blogId, UUID userId) {
        UserAndBlogResponseDto userAndBlogResponseDto=new UserAndBlogResponseDto();
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isEmpty()) {
            userAndBlogResponseDto.setMessage("Blog not found!");
            return Utility.createFailureResponse("Blog not found!", userAndBlogResponseDto);
        }
        Blog blog = blogOptional.get();

        if (!blog.getUser().getId().equals(userId)) {
            userAndBlogResponseDto.setMessage("Unauthorized user! You can only delete your own blog posts.");
            return Utility.createFailureResponse("Unauthorized user! You can only delete your own blog posts.", userAndBlogResponseDto); // Unauthorized access
        }
        blogRepository.delete(blog);
        userAndBlogResponseDto.setMessage("Blog deleted successfully!");
        return Utility.createSuccessResponse("Blog deleted successfully!", userAndBlogResponseDto);
    }

    public ResponseDto repost(UUID userId) {
        // Step 1: Fetch all blogs written by the user
        List<Blog> blogs = blogRepository.findByUserId(userId);

        if (blogs.isEmpty()) {
            return Utility.createFailureResponse("No blogs found for the given user.", null);
        }

        // Step 2: Use a map to store word frequencies
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Step 3: Process each blog's content and count words
        for (Blog blog : blogs) {
            String blogData = blog.getBlogData();
            String[] words = blogData.split("\\W+");
            for (String word : words) {
                word = word.toLowerCase();
                if (word.length() > 1) {
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCountMap.entrySet());
        sortedWords.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<String> topWords = new ArrayList<>();
        int limit = Math.min(5, sortedWords.size());
        for (int i = 0; i < limit; i++) {
            topWords.add(sortedWords.get(i).getKey());
        }
        return Utility.createSuccessResponse("Top 5 words for user " + userId, topWords);
    }
}
