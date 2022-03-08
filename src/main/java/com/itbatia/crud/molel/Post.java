package com.itbatia.crud.molel;

import java.util.List;
import java.util.Objects;

public class Post {
    private Integer id;
    private String content;
    private List<Tag> tags;
    private PostStatus status;

    public Post(Integer id, String content, List<Tag> tags, PostStatus status) {
        this.id = id;
        this.content = content;
        this.tags = tags;
        this.status = status;
    }

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(content, post.content) && Objects.equals(tags, post.tags) && status == post.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, tags, status);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", status=" + status +
                '}';
    }
}
