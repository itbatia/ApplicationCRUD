package com.itbatia.crud.service;

import com.itbatia.crud.molel.Tag;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class TagServiceTest {

    private TagService tagService;
    private String name;

    @Before
    public void setUp() {
        tagService = new TagService();
        name = "Java";
    }

    @Test
    public void createTag() {
        Tag tagExpected = tagService.createTag(name);
        Tag tagActual = new Tag(tagExpected.getId(), name);

        assertEquals(tagExpected, tagActual);
        assertNotNull(tagExpected.getId());
    }

    @Test
    public void getTag() {
        Tag tagActual = tagService.createTag(name);
        Tag tagExpected = tagService.getTag(tagActual.getId());

        assertEquals(tagExpected, tagActual);
        assertNotNull(tagExpected);
    }

    @Test
    public void getAllTags() {
        int countBeforeAddedTag = tagService.getAllTags().size();
        tagService.createTag(name);
        List<Tag> tagsExpected = tagService.getAllTags();
        int countAfterAddedTag = tagsExpected.size();

        assertNotNull(tagsExpected);
        assertTrue(tagsExpected.size() > 0);
        assertEquals(countBeforeAddedTag + 1, countAfterAddedTag);
    }

    @Test
    public void updateTag() {
        int createdTagId = tagService.createTag(name).getId();
        Tag tagActual = tagService.updateTag(createdTagId, "Piton");
        Tag tagExpected = tagService.getTag(createdTagId);

        assertEquals(tagExpected.getName(), tagActual.getName());
    }

    @Test
    public void deleteTag() {
        Tag createdTag = tagService.createTag(name);
        tagService.deleteTag(createdTag.getId());

        assertNull(tagService.getTag(createdTag.getId()));
    }

    @After
    public void clean() {
        tagService.getAllTags().forEach(tag -> {
            if (tag.getName().equals(name) || tag.getName().equals("Piton")) {
                tagService.deleteTag(tag.getId());
            }
        });
    }
}
