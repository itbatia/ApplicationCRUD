package com.itbatia.crud.service;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.repository.PostRepository;
import com.itbatia.crud.repository.database.DatabasePostRepositoryImpl;

import java.util.List;

public class PostService {

    private PostRepository postRepository = new DatabasePostRepositoryImpl();

    public PostService() {
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String content, List<Tag> tags, PostStatus postStatus) {
        Post post = new Post(null, content, tags, postStatus);
        return postRepository.save(post);
    }

    public Post getPost(Integer id) {
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post updatePost(Post newPost) {
        return postRepository.update(newPost);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}
