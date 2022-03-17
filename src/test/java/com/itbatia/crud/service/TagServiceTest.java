package com.itbatia.crud.service;

import com.itbatia.crud.molel.Tag;
import com.itbatia.crud.repository.TagRepository;
import com.itbatia.crud.repository.database.DatabaseTagRepositoryImpl;
import org.junit.*;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TagServiceTest {

    @Mock
    private final TagRepository mock = mock(DatabaseTagRepositoryImpl.class);

    private TagService tagService;
    private String name;

    @Before
    public void setUp() {
        tagService = new TagService(mock);
        name = "Java";
    }

    @Test
    public void createTag() {
        Tag tag = new Tag(null, name);
        Tag tagExpected = new Tag(1, name);

        when(mock.save(tag)).thenReturn(tagExpected);
        Tag tagActual = tagService.createTag(name);

        verify(mock, times(1)).save(tag);
        assertEquals(tagExpected, tagActual);
    }

    @Test
    public void getTag() {
        Tag tagExpected = new Tag(1, name);

        when(mock.getById(1)).thenReturn(tagExpected);
        Tag tagActual = tagService.getTag(1);

        verify(mock).getById(1);
        verify(mock, never()).getById(2);
        assertEquals(tagExpected, tagActual);
    }

    @Test
    public void getAllTags() {
        List<Tag> tagsExpected = new ArrayList<>();

        when(mock.getAll()).thenReturn(tagsExpected);
        List<Tag> tagsActual = tagService.getAllTags();

        verify(mock).getAll();
        assertEquals(tagsExpected, tagsActual);
    }

    @Test
    public void updateTag() {
        Tag tagExpected = new Tag(1, name);

        when(mock.update(tagExpected)).thenReturn(tagExpected);
        Tag tagActual = tagService.updateTag(1, name);

        verify(mock, times(1)).update(tagExpected);
        assertEquals(tagExpected, tagActual);
    }

    @Test
    public void deleteTag() {

        tagService.deleteTag(1);
        verify(mock).deleteById(1);
    }
}
