package com.itbatia.crud.service;

import com.itbatia.crud.molel.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class PostServiceTest {

    private PostService postService;
    private String content;
    private PostStatus postStatus;
    private List<Tag> tags;

    @Before
    public void setUp() {
        postService = new PostService();
        content = "Some content";
        postStatus = PostStatus.ACTIVE;
        tags = new ArrayList<>();
    }

    @Test
    public void createPost() {
        Post newPost = postService.createPost(content, tags, postStatus);

        assertEquals(newPost.getContent(), content);
        assertEquals(newPost.getStatus(), postStatus);
        assertEquals(newPost.getTags(), tags);
        assertNotNull(newPost.getId());
    }

    @Test
    public void getPost() {

    }

    @Test
    public void getAllPosts() {
    }

    @Test
    public void updatePost() {
    }

    @Test
    public void deletePost() {
    }
}