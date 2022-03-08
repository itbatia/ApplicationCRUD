package com.itbatia.crud.controller;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.service.WriterService;

import java.util.List;

public class WriterController {
    private final WriterService writerService = new WriterService();

    public Writer createWriter(String name, List<Post> posts) {
        return writerService.createWriter(name, posts);
    }

    public Writer getWriter(Integer id) {
        return writerService.getWriter(id);
    }

    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }

    public Writer updateWriter(Writer writer) {
        return writerService.updateWriter(writer);
    }

    public void updateWriterName(Integer id, String newName) {
        Writer writer = writerService.getWriter(id);
        writer.setName(newName);
        writerService.updateWriter(writer);
    }

    public void updateAllWriterPosts(Integer id, List<Post> newPosts) {
        Writer writer = writerService.getWriter(id);
        writer.setPosts(newPosts);
        writerService.updateWriter(writer);
    }

    public void updateWriterPost(Integer id, Post newPost) {
        Writer writer = writerService.getWriter(id);
        writer.getPosts().forEach(post -> {
            if (post.getId().equals(newPost.getId())) {
                post.setContent(newPost.getContent());
                post.setTags(newPost.getTags());
                post.setStatus(newPost.getStatus());
            }
        });
        writerService.updateWriter(writer);
    }

    public void deleteWriter(Integer id) {
        writerService.deleteWriter(id);
    }
}
