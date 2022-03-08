package com.itbatia.crud.molel;

import java.util.List;
import java.util.Objects;

public class Writer {
    private Integer id;
    private String name;
    private List<Post> posts;

    public Writer(Integer id, String name, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }
    public Writer (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(id, writer.id) && Objects.equals(name, writer.name) && Objects.equals(posts, writer.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, posts);
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}
