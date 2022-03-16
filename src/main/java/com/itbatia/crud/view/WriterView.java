package com.itbatia.crud.view;

import com.itbatia.crud.controller.PostController;
import com.itbatia.crud.controller.WriterController;
import com.itbatia.crud.molel.*;

import static com.itbatia.crud.utils.Messages.*;
import static com.itbatia.crud.utils.ShowInConsole.*;

import java.util.*;
import java.util.stream.Collectors;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final PostView postView = new PostView();
    private final PostController postController = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    public void createWriter() {
        System.out.println("Enter new writer name:");
        String writerName = scanner.nextLine();
        List<Post> posts = new ArrayList<>();
        System.out.println("The writer is created. Add post to him?\n1 - YES\n2 - NO");
        boolean result = answerFromUser();
        while (result) {
            posts.add(postView.createPost());
            System.out.println("Add another post to him?\n1 - YES\n2 - NO");
            result = answerFromUser();
        }
        Writer newWriter = writerController.createWriter(writerName, posts);
        System.out.println("Writer created:");
        showWriter(newWriter);
    }

    private boolean answerFromUser() {
        String userInput = scanner.nextLine();
        return userInput.equals("1");
    }

    public Writer getWriter() {
        int id = idFromUser();
        if (id != 0) {
            System.out.print("Found Writer: \t");
            showWriter(writerController.getWriter(id));
            return writerController.getWriter(id);
        } else return null;
    }

    public void getAllWriters() {
        List<Writer> writers = writerController.getAllWriters();
        showWriters(writers);
    }

    public List<Post> getAllWriterPosts(Writer writer) {
        return writer.getPosts();
    }

    public void updateWriterById() {
        Writer writer = getWriter();
        if (writer != null) {
            updateWriter(writer);
        }
    }

    public void updateWriter(Writer writer) {
        System.out.println(UPDATE_WRITER_SELECTION_1.getMessage());
        String userInput = scanner.nextLine();
        if (userInput.matches("[1-5]")) {
            while (userInput.matches("[1-5]")) {
                switch (Integer.parseInt(userInput)) {
                    case 1:
                        updateWriterCase1_UpdateName(writer);
                        break;
                    case 2:
                        updateWriterCase2_UpdatePost(writer);
                        break;
                    case 3:
                        updateWriterCase3_CreateNewPost(writer);
                        break;
                    case 4:
                        updateWriterCase4_AddCreatedPost(writer);
                        break;
                    case 5:
                        updateWriterCase5_DeletePost(writer);
                        break;
                }
                System.out.println(UPDATE_WRITER_SELECTION_2.getMessage());
                userInput = scanner.nextLine();
            }
            System.out.println("Changes applied! Back to Writers menu.");
        } else {
            System.out.println("Back to Writer menu.");
        }
    }

    private void updateWriterCase1_UpdateName(Writer writer) {
        System.out.println("Enter new writer name:");
        writerController.updateWriterName(writer.getId(), scanner.nextLine());
    }

    private void updateWriterCase2_UpdatePost(Writer writer) {
        System.out.println("Enter post id:");
        String stringPostId = scanner.nextLine();
        if (stringPostId.matches("\\d+")) {
            int postId = Integer.parseInt(stringPostId);
            List<Post> posts = writer.getPosts();
            List<Integer> idOfPosts = posts.stream().map(Post::getId).collect(Collectors.toList());
            if (idOfPosts.contains(postId)) {
                writer.getPosts().forEach(post -> {
                    if (post.getId().equals(postId)) {
                        postView.updatePostByPost(post);
                    }
                });
            } else {
                System.out.println("Writer doesn't have post with this id.");
            }
        }
    }

    private void updateWriterCase3_CreateNewPost(Writer writer) {
        Post newPost = postView.createPostWithoutId();
        writerController.addPostByWriterId(writer.getId(), newPost);
    }

    private void updateWriterCase4_AddCreatedPost(Writer writer) {
        System.out.print("List of available free posts.");
        List<Post> freePosts = writerController.getAllFreePosts();
        showPosts(freePosts);
        int freePostId = idFreePostFromUser(freePosts);
        if (freePostId != 0) {
            List<Post> writerPosts = writer.getPosts();
            writerPosts.add(postController.getPost(freePostId));
            writer.setPosts(writerPosts);
            writerController.updateWriter(writer);
        }
    }

    private void updateWriterCase5_DeletePost(Writer writer) {
        System.out.print("List of posts for this writer:");
        List<Post> writerPosts = writer.getPosts();
        showPosts(writerPosts);
        System.out.println("Enter post ID to remove it:");
        String stringPostId = scanner.nextLine();
        if (stringPostId.matches("\\d+")) {
            int postId = Integer.parseInt(stringPostId);
            boolean result = writerPosts.removeIf(post -> post.getId().equals(postId));
            writerController.updateWriter(writer);
            if (result) {
                System.out.println("Post deleted!");
            } else {
                System.out.println("Writer doesn't have this post.");
            }
        } else {
            System.out.println("Incorrect data.");
        }
    }

    private Integer idFreePostFromUser(List<Post> freePosts) {
        System.out.println("Select free post id to add it:");
        String stringID = scanner.nextLine();
        if (!stringID.matches("\\d+")) {
            System.out.println("Incorrect data.");
            return 0;
        }
        int intId = Integer.parseInt(stringID);
        for (Post post : freePosts) {
            if (post.getId().equals(intId)) {
                return intId;
            }
        }
        System.out.println("This post is not among the available posts");
        return 0;
    }

    public void deleteWriterById() {
        Writer writer = getWriter();
        if (writer != null) {
            deleteWriter(writer);
        } else {
            System.out.println("Back to Writer menu.");
        }
    }

    public void deleteWriter(Writer writer) {
        System.out.println("Do you confirm the deletion?\n1 - Yes\n2 - No");
        String userChoice = scanner.nextLine();
        if (userChoice.equals("1")) {
            System.out.printf("Writer %s successfully deleted!\n", writer.getName());
            writerController.deleteWriter(writer.getId());
        }
    }

    private Integer idFromUser() {
        System.out.println("Enter writer ID:");
        String stringID = scanner.nextLine();
        if (!stringID.matches("\\d+")) {
            System.out.println("Incorrect data. Insert the number.");
            return 0;
        }
        int intID = Integer.parseInt(stringID);
        if (writerController.getWriter(intID) == null) {
            System.out.printf("Writer %s doesn't exist\n", intID);
            return 0;
        } else return intID;
    }
}
