package com.itbatia.crud.service;

import com.itbatia.crud.molel.Tag;
import com.itbatia.crud.repository.TagRepository;
import com.itbatia.crud.repository.database.DatabaseTagRepositoryImpl;

import java.util.List;

public class TagService {
    private final TagRepository tagRepository = new DatabaseTagRepositoryImpl();

    public Tag createTag(String name) {
        Tag tag = new Tag(null, name);
        return tagRepository.save(tag);
    }

    public Tag getTag(Integer id) {
        return tagRepository.getById(id);
    }

    public List<Tag> getAllTags() {
        return tagRepository.getAll();
    }

    public Tag updateTag(Integer id, String newName) {
        Tag newTag = new Tag(id, newName);
        return tagRepository.update(newTag);
    }

    public void deleteTag(Integer id) {
        tagRepository.deleteById(id);
    }
}
