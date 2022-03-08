package com.itbatia.crud.controller;

import com.itbatia.crud.molel.Tag;
import com.itbatia.crud.service.TagService;

import java.util.List;

public class TagController {
    private final TagService tagService = new TagService();

    public Tag createTag(String name) {
        return tagService.createTag(name);
    }

    public Tag getTag(Integer id) {
        return tagService.getTag(id);
    }

    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    public Tag updateTag(Integer id, String newName) {
        return tagService.updateTag(id, newName);
    }

    public void deleteTag(Integer id) {
        tagService.deleteTag(id);
    }

}
