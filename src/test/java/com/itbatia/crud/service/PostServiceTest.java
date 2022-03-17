package com.itbatia.crud.service;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.repository.PostRepository;
import com.itbatia.crud.repository.database.DatabasePostRepositoryImpl;
import org.junit.*;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Mock
    private final PostRepository mock = mock(DatabasePostRepositoryImpl.class);

    private PostService postService;
    private String content;
    private PostStatus postStatus;
    private List<Tag> tags;

    @Before
    public void setUp() {
        postService = new PostService(mock);
        content = "Some content";
        postStatus = PostStatus.ACTIVE;
        tags = new ArrayList<>();
    }

    @Test
    public void createPost() {
        Post post = new Post(null, content, tags, postStatus);
        Post postExpected = new Post(1, content, tags, postStatus);

        when(mock.save(post)).thenReturn(postExpected);
        Post postActual = postService.createPost(content, tags, postStatus);

        verify(mock, times(1)).save(post);
        assertEquals(postExpected, postActual);
    }

    @Test
    public void getPost() {
        Post postExpected = new Post(1, content, tags, postStatus);

        when(mock.getById(1)).thenReturn(postExpected).thenReturn(postExpected);
        Post postActual = postService.getPost(1);

        verify(mock, atLeast(1)).getById(1);
        assertEquals(postExpected, postActual);
    }

    @Test
    public void getAllPosts() {
        List<Post> postsExpected = new ArrayList<>();

        when(mock.getAll()).thenReturn(postsExpected);
        List<Post> postsActual = postService.getAllPosts();

        verify(mock, times(1)).getAll();
        assertEquals(postsExpected, postsActual);
    }

    @Test
    public void updatePost() {
        Post postExpected = new Post(1, content, tags, postStatus);

        when(mock.update(postExpected)).thenReturn(postExpected);
        Post postActual = postService.updatePost(postExpected);

        verify(mock, times(1)).update(postExpected);
        assertEquals(postExpected, postActual);
    }

    @Test
    public void deletePost() {

        postService.deletePost(1);
        verify(mock).deleteById(1);
    }
}