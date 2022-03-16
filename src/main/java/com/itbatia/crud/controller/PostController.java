package com.itbatia.crud.controller;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.service.*;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();
    private final TagService tagService = new TagService();

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

    public void updatePostContent(Integer id, String newContent) {
        Post post = postService.getPost(id);
        post.setContent(newContent);
        updatePost(post);
    }

    public void updatePostStatus(Integer id, PostStatus postStatus) {
        Post post = postService.getPost(id);
        post.setStatus(postStatus);
        updatePost(post);
    }

    public void addTagToPost(Integer postId, String tagName) {
        if (isExistingTag(tagName)) {
            int tagId = getIdOfExistingTag(tagName);
            Tag tag = tagService.getTag(tagId);
            Post post = postService.getPost(postId);
            post.getTags().add(tag);
            updatePost(post);
        } else {
            Tag tag = tagService.createTag(tagName);
            Post post = postService.getPost(postId);
            post.getTags().add(tag);
            updatePost(post);
        }
    }

    private boolean isExistingTag(String tagName) {
        List<Tag> tags = tagService.getAllTags();
        return tags.stream().anyMatch(tag -> tag.getName().equals(tagName));
    }

    private Integer getIdOfExistingTag(String tagName) {
        List<Tag> tags = tagService.getAllTags();
        return tags.stream().filter(tag -> tag.getName().equals(tagName)).map(Tag::getId).findFirst().orElse(null);
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
