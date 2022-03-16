package com.itbatia.crud.view;

import com.itbatia.crud.controller.PostController;
import com.itbatia.crud.molel.*;

import static com.itbatia.crud.utils.Messages.*;
import static com.itbatia.crud.utils.ShowInConsole.*;

import java.util.*;

public class PostView {
    private final PostController postController = new PostController();
    private final TagView tagView = new TagView();
    private final Scanner scanner = new Scanner(System.in);

    public Post createPost() {
        System.out.println("Enter new post content:");
        String postContent = scanner.nextLine();
        System.out.println("The post is created. Add tags to it?\n1 - YES\n2 - NO");
        List<Tag> tags = new ArrayList<>();
        if (yesFromUser()) {
            tags = createPostTags();
        }
        Post createdPost = postController.createPost(postContent, tags, PostStatus.ACTIVE);
        System.out.println("Post created:");
        showPost(createdPost);
        return createdPost;
    }

    private boolean yesFromUser() {
        String userInput = scanner.nextLine();
        return userInput.equals("1");
    }

    private List<Tag> createPostTags() {
        List<Tag> tags = new ArrayList<>();
        String userInput;
        do {
            tags.add(tagView.createTag());
            System.out.println("Add another tag?\n1 - YES\n2 - NO");
            userInput = scanner.nextLine();
        } while (userInput.equals("1"));
        return tags;
    }

    public Post createPostWithoutId() {
        System.out.println("Enter new post content:");
        String content = scanner.nextLine();
        System.out.println("The post is created. Add tags to it?\n1 - YES\n2 - NO");
        List<Tag> tags = new ArrayList<>();
        if (yesFromUser()) {
            tags = createPostTags();
        }
        Post newPost = postController.createPost(content, tags, PostStatus.ACTIVE);
        System.out.println("Post created:");
        showPost(newPost);
        return newPost;
    }

    public Post getPost() {
        int id = idFromUser();
        if (id != 0) {
            System.out.println("Found Post:");
            showPost(postController.getPost(id));
            return postController.getPost(id);
        } else return null;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postController.getAllPosts();
        showPosts(posts);
        return posts;
    }

    public void updatePostById() {
        int id = idFromUser();
        if (id != 0) {
            System.out.println("Found post:");
            showPost(postController.getPost(id));
            System.out.println(UPDATE_POST_SELECTION_1.getMessage());
            updatePost(id);
        }
    }

    public Post updatePostByPost(Post post) {
        System.out.println(UPDATE_POST_SELECTION_1.getMessage());
        return updatePost(post.getId());
    }

    private Post updatePost(Integer id) {
        String userInput = scanner.nextLine();
        if (userInput.matches("[1-4]")) {
            while (userInput.matches("[1-4]")) {
                switch (Integer.parseInt(userInput)) {
                    case 1:
                        updatePostCase1_UpdateContent(id);
                        break;
                    case 2:
                        updatePostCase2_AddNewTag(id);
                        break;
                    case 3:
                        updatePostCase3_DeleteTag(id);
                        break;
                    case 4:
                        updatePostCase4_UpdatePostStatus(id);
                        break;
                }
                System.out.println(UPDATE_POST_SELECTION_2.getMessage());
                userInput = scanner.nextLine();
            }
            System.out.println("Changes applied!");
        } else if (userInput.equals("q")) {
            System.out.println("Back.");
        } else {
            System.out.println("Incorrect data.");
        }


        return postController.getPost(id);
    }

    private void updatePostCase1_UpdateContent(Integer id) {
        System.out.println("Enter new post content:");
        postController.updatePostContent(id, scanner.nextLine());
        System.out.println("Content is changed!");
    }

    private void updatePostCase2_AddNewTag(Integer id) {
        System.out.println("Enter new Tag name: ");
        String tagName = scanner.nextLine();
        postController.addTagToPost(id, tagName);
        System.out.println("Tag added!");
    }

    private void updatePostCase3_DeleteTag(Integer id) {
        System.out.print("List of tags for this post:");
        showTags(postController.getPost(id).getTags());
        System.out.println("Enter tag ID to remove it:");
        String tagId = scanner.nextLine();
        if (tagId.matches("\\d+")) {
            boolean result = postController.deleteTagFromPost(id, Integer.parseInt(tagId));
            if (result) {
                System.out.println("Tag deleted!");
            } else {
                System.out.println("Post doesn't have this tag.");
            }
        } else {
            System.out.println("Incorrect data.");
        }
    }

    private void updatePostCase4_UpdatePostStatus(Integer id) {
        System.out.println("Make your choice:\n1 - ACTIVE\n2 - DELETED");
        String userInput = scanner.nextLine();
        if (userInput.equals("1")) {
            postController.updatePostStatus(id, PostStatus.ACTIVE);
            System.out.println("Done!");
        } else if (userInput.equals("2")) {
            postController.updatePostStatus(id, PostStatus.DELETED);
            System.out.println("Done!");
        } else {
            System.out.println("Incorrect data.");
        }
    }

    public void deletePostById() {
        int id = idFromUser();
        if (id != 0) {
            System.out.println("Found post. " + CONFIRMATION.getMessage());
            deletePost(id);
        }
    }

    public void deletePostByPost(Post post) {
        System.out.println(CONFIRMATION_2.getMessage());
        deletePost(post.getId());
    }

    private void deletePost(Integer id) {
        String userChoice = scanner.nextLine();
        if (userChoice.equals("1")) {
            postController.deletePost(id);
            System.out.println("Post successfully deleted!");
        } else {
            System.out.println("Back to Post menu.");
        }
    }

    private Integer idFromUser() {
        System.out.println("Enter post id:");
        String stringID = scanner.nextLine();
        if (!stringID.matches("\\d+")) {
            System.out.println("Incorrect data.");
            return 0;
        }
        int intID = Integer.parseInt(stringID);
        if (postController.getPost(intID) == null) {
            System.out.printf("Post %s doesn't exist\n", intID);
            return 0;
        } else return intID;
    }
}
