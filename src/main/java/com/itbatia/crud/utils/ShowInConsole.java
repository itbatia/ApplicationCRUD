package com.itbatia.crud.utils;

import com.itbatia.crud.molel.*;

import java.util.List;

public class ShowInConsole {
    public static void showTag(Tag tag) {
        if (tag == null) {
            return;
        }
        if (tag.getId() < 100) {
            String header = "+----+------------------+\n| id |       name       |\n+----+------------------+\n";
            System.out.print(header);
            System.out.printf("| %-2s | %-16s |\n", tag.getId(), tag.getName());
            System.out.println("+----+------------------+");
        } else {
            String header = "+------+------------------+\n| id  |       name       |\n+------+------------------+\n";
            System.out.print(header);
            System.out.printf("| %-5s| %-16s |\n", tag.getId(), tag.getName());
            System.out.println("+------+------------------+");

        }
    }

    public static void showTags(List<Tag> tags) {
        if (tags.size() < 100) {
            String header = "+----+------------------+\n| id |       name       |\n+----+------------------+\n";
            System.out.print(header);
            for (Tag tag : tags) {
                System.out.printf("| %-2s | %-16s |\n", tag.getId(), tag.getName());
                System.out.println("+----+------------------+");
            }
        } else {
            String header = "+------+------------------+\n| id  |       name       |\n+------+------------------+\n";
            System.out.print(header);
            for (Tag tag : tags) {
                System.out.printf("| %-5s| %-16s |\n", tag.getId(), tag.getName());
                System.out.println("+------+------------------+");
            }
        }
    }

    public static void showPost(Post post) {
        if (post == null) {
            return;
        }
        String header = "+------+-------------------------------------+--------+------------------------------------+\n" +
                "|  id  |               content               | status |                tags                |\n" +
                "+------+-------------------------------------+--------+------------------------------------+\n";
        System.out.print(header);
        System.out.printf("| %-4s | %-35s | %-6s |", post.getId(), post.getContent(), post.getStatus());
        if (post.getTags() != null) {
            for (Tag tag : post.getTags()) {
                System.out.printf(" %s-%s", tag.getId(), tag.getName());
            }
        }
        System.out.println("\n+------+-------------------------------------+--------+------------------------------------+");

    }

    public static void showPosts(List<Post> posts) {
        String header = "+----+-------------------------------------+--------+------------------------------------+\n" +
                "| id |               content               | status |                tags                |\n" +
                "+----+-------------------------------------+--------+------------------------------------+\n";
        System.out.print(header);
        for (Post post : posts) {
            System.out.printf("| %-2s | %-35s | %-6s |", post.getId(), post.getContent(), post.getStatus());
            for (Tag tag : post.getTags()) {
                System.out.printf(" %s-%s", tag.getId(), tag.getName());
            }
            System.out.println("\n+----+-------------------------------------+--------+------------------------------------+");
        }
    }

    public static void showWriter(Writer writer) {
        if (writer == null) {
            return;
        }
        System.out.printf("id: %-3d\tname: %-8s\n", writer.getId(), writer.getName());
        List<Post> posts = writer.getPosts();
        if (posts != null) {
            for (Post post : posts) {
                System.out.printf("        post -> id: %-3d status: %-7s content: \"%s\"\n",
                        post.getId(), post.getStatus(), post.getContent());
                List<Tag> tags = post.getTags();
                if (tags != null) {
                    for (Tag tag : tags) {
                        System.out.printf("\t\t\ttag id: %-3d name: %s\n", tag.getId(), tag.getName());
                    }
                }
            }
        }
    }

    public static void showWriters(List<Writer> writers) {
        System.out.printf("\n%d writers available:\n", writers.size());
        for (Writer writer : writers) {
            showWriter(writer);
        }
    }
}
