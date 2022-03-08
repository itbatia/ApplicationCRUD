package com.itbatia.crud.controller;

import com.itbatia.crud.molel.Post;
import com.itbatia.crud.molel.PostStatus;
import com.itbatia.crud.molel.Tag;
import com.itbatia.crud.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public Post createPost(String content, List<Tag> tags, PostStatus postStatus) {
        return postService.createPost(content, tags, postStatus);
    }

    public Post getPost(Integer id) {
        return postService.getPost(id);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post updatePost(Post newPost) {
        return postService.updatePost(newPost);
    }

    public Post updatePostContent(Integer id, String newContent) {
        Post post = postService.getPost(id);
        post.setContent(newContent);
        return postService.updatePost(post);
    }

    public Post updatePostTags(Integer id, List<Tag> newTags) {
        Post post = postService.getPost(id);
        post.setTags(newTags);
        return postService.updatePost(post);
    }

    public Post updatePostStatus(Integer id, PostStatus postStatus) {
        Post post = postService.getPost(id);
        post.setStatus(postStatus);
        return postService.updatePost(post);
    }

    public Post addTagToPost(Integer id, Tag tag) {
        Post post = postService.getPost(id);
        post.getTags().add(tag);
        return postService.updatePost(post);
    }

    public boolean deleteTagFromPost(Integer postId, Integer tagId) {
        Post post = postService.getPost(postId);
        List<Tag> tags = post.getTags();
        boolean result = tags.removeIf(tag -> tag.getId().equals(tagId));
        postService.updatePost(post);
        return result;
    }
    public void deletePost(Integer id) {
        postService.deletePost(id);
    }
}
