package com.itbatia.crud;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.repository.database.*;

import static com.itbatia.crud.utils.ShowInConsole.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseTagRepositoryImpl tag = new DatabaseTagRepositoryImpl();
        DatabasePostRepositoryImpl post = new DatabasePostRepositoryImpl();
        DatabaseWriterRepositoryImpl writer = new DatabaseWriterRepositoryImpl();

        Tag tag1 = new Tag(null, "first");
        Tag tag2 = new Tag(null, "second");
        Tag tag3 = new Tag(null, "third");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

//        showTag(tag.save(tag1));
//        showTag(tag.getById(8));
//        showTag(tag.update(new Tag(15, "15-th")));
//        tag.deleteById(15);
//        showTags(tag.getAll());

        Post post1 = new Post(null, "third going", null, PostStatus.ACTIVE);
        Post post2 = new Post(null, "10-th", tags, PostStatus.DELETE);
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

//        showPost(post.save(post2));
//        showPost(post.getById(4));
//        showPost(post.update(post2));
//        post.deleteById(1);

//        showPosts(post.getAll());

        Writer writer1 = new Writer(1, "Petya", null);
        Writer writer2 = new Writer(null, "Egorkaaaa!!!", posts);
        Writer writer3 = new Writer(null, "OOO", null);
        List<Writer> writers = new ArrayList<>();
        writers.add(writer1);
        writers.add(writer2);

//        showWriter(writer.save(writer2));
//        showWriter(writer.getById(2));
//        showWriter(writer.update(writer1));
        writer.deleteById(2);
        showWriters(writer.getAll());
    }
}
