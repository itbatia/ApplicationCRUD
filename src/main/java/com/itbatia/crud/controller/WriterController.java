package com.itbatia.crud.controller;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.service.WriterService;

import java.util.ArrayList;
import java.util.List;

public class WriterController {
    private final WriterService writerService = new WriterService();
    private final PostController postController = new PostController();

    public Writer createWriter(String name, List<Post> posts) {
        return writerService.createWriter(name, posts);
    }

    public Writer getWriter(Integer id) {
        return writerService.getWriter(id);
    }

    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }

    public List<Post> getAllFreePosts() {
        List<Post> allPosts = postController.getAllPosts();
        List<Post> allWritersPosts = new ArrayList<>();
        List<Writer> writers = writerService.getAllWriters();
        for (Writer w : writers) {
            allWritersPosts.addAll(w.getPosts());
        }
        allPosts.removeAll(allWritersPosts);
        return allPosts;
    }

    public Writer updateWriter(Writer writer) {
        return writerService.updateWriter(writer);
    }

    public void updateWriterName(Integer id, String newName) {
        Writer writer = writerService.getWriter(id);
        writer.setName(newName);
        writerService.updateWriter(writer);
    }

    public void addPostByWriterId(Integer id, Post newPost) {
        Writer writer = writerService.getWriter(id);
        List<Post> posts = writer.getPosts();
        posts.add(newPost);
        writer.setPosts(posts);
        updateWriter(writer);
    }

    public void deleteWriter(Integer id) {
        writerService.deleteWriter(id);
    }
}
