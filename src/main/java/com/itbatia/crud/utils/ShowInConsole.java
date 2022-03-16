package com.itbatia.crud.utils;

import com.itbatia.crud.molel.*;

import java.util.List;

public class ShowInConsole {
    public static void showTag(Tag tag) {
        System.out.printf("id: %-3d\t name: %s\n", tag.getId(), tag.getName());
    }

    public static void showTags(List<Tag> tags) {
        if (tags ==null) {
            System.out.println("0 tags available!");
            return;
        }
        System.out.printf("\n%d tags available:\n", tags.size());
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
        System.out.println("_______________________");
        System.out.printf("Post\n\tid: %-2d\n\tstatus: %-1s\n\tcontent: \"%s\"\n", post.getId(), post.getStatus(), post.getContent());
        List<Tag> tags = post.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                System.out.printf("\ttag: id-%-3d name-%s\n", tag.getId(), tag.getName());
            }
        }
        System.out.println("_______________________");
    }

    public static void showPosts(List<Post> posts) {
        if (posts == null || posts.size()==0) {
            System.out.println("0 posts available!");
            return;
        }
        System.out.printf("\n%d posts available:\n", posts.size());
        System.out.println("_______________________");
        for (Post post : posts) {
            System.out.printf("Post\n\tid: %-2d\n\tstatus: %-1s\n\tcontent: \"%s\"\n", post.getId(), post.getStatus(), post.getContent());
            List<Tag> tags = post.getTags();
            for (Tag tag : tags) {
                System.out.printf("\ttag: id-%-3d name-%s\n", tag.getId(), tag.getName());
            }
        }
        System.out.println("_______________________");
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
        System.out.println("_______________________");
    }

    public static void showWriters(List<Writer> writers) {
        if (writers ==null || writers.size() == 0) {
            System.out.println("0 writers available!");
            return;
        }
        System.out.printf("\n%d writers available:\n", writers.size());
        System.out.println("_______________________");
        for (Writer writer : writers) {
            showWriter(writer);
        }
    }
}
