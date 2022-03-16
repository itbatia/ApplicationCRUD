package com.itbatia.crud.view;

import com.itbatia.crud.molel.*;

import static com.itbatia.crud.utils.Messages.*;
import static com.itbatia.crud.utils.ShowInConsole.*;

import java.util.Scanner;

public class MainView {
    private final TagView tagView = new TagView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();
    Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        System.out.println(MAIN_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                tagMenu();
            case 2:
                postMenu();
            case 3:
                writerMenu();
            case 0:
                System.out.println("\nExit the program.");
                System.exit(0);
            default:
                System.out.println("Incorrect data.");
                mainMenu();
        }
    }

    private void tagMenu() {
        System.out.println(TAG_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                tagView.createTag();
                tagMenu();
            case 2:
                Tag tag = tagView.getTag();
                if (tag != null) {
                    menuAfterGetTag(tag);
                } else {
                    tagMenu();
                }
            case 3:
                tagView.updateTagById();
                tagMenu();
            case 4:
                tagView.deleteTagById();
                tagMenu();
            case 5:
                tagView.getAllTags();
                tagMenu();
            case 6:
                mainMenu();
            case 0:
                System.out.println("\nExit the program.");
                System.exit(0);
            default:
                System.out.println("Incorrect data.");
                tagMenu();
        }
    }

    private void menuAfterGetTag(Tag tag) {
        System.out.println(AFTER_GET_TAG_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                tagView.updateTagByTag(tag);
                tagMenu();
            case 2:
                tagView.deleteTagByTag(tag);
                tagMenu();
            case 3:
                tagMenu();
            default:
                System.out.println("Incorrect data.");
                menuAfterGetTag(tag);
        }
    }

    private void postMenu() {
        System.out.println(POST_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                postView.createPost();
                postMenu();
            case 2:
                Post post = postView.getPost();
                if (post != null) {
                    menuAfterGetPost(post);
                } else postMenu();
            case 3:
                postView.updatePostById();
                postMenu();
            case 4:
                postView.deletePostById();
                postMenu();
            case 5:
                postView.getAllPosts();
                postMenu();
            case 6:
                mainMenu();
            case 0:
                System.out.println("\nExit the program.");
                System.exit(0);
            default:
                System.out.println("Incorrect data.");
                postMenu();
        }
    }

    private void menuAfterGetPost(Post post) {
        System.out.println(AFTER_GET_POST_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                postView.updatePostByPost(post);
                postMenu();
            case 2:
                postView.deletePostByPost(post);
                postMenu();
            case 3:
                postMenu();
            default:
                System.out.println("Incorrect data.");
                menuAfterGetPost(post);
        }
    }

    private void writerMenu() {
        System.out.println(WRITER_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                writerView.createWriter();
                writerMenu();
            case 2:
                Writer writer = writerView.getWriter();
                if (writer != null) {
                    menuAfterGetWriter(writer);
                } else {
                    writerMenu();
                }
            case 3:
                writerView.updateWriterById();
                writerMenu();
            case 4:
                writerView.deleteWriterById();
                writerMenu();
            case 5:
                writerView.getAllWriters();
                writerMenu();
            case 6:
                mainMenu();
            case 0:
                System.out.println("\nExit the program.");
                System.exit(0);
            default:
                System.out.println("Incorrect data.");
                writerMenu();
        }
    }

    private void menuAfterGetWriter(Writer writer) {
        System.out.println(AFTER_GET_WRITER_MENU_SELECTION.getMessage());
        switch (userInput()) {
            case 1:
                writerView.updateWriter(writer);
                writerMenu();
            case 2:
                writerView.deleteWriter(writer);
                writerMenu();
            case 3:
                showPosts(writerView.getAllWriterPosts(writer));
                writerMenu();
            case 4:
                writerMenu();
            default:
                System.out.println("Incorrect data.");
                menuAfterGetWriter(writer);
        }
    }

    private Integer userInput() {
        System.out.println("-----------------\nMake your choice:");
        String stringUserInput = scanner.nextLine();
        if (!stringUserInput.matches("\\d+")) {
            System.out.println("Incorrect data. Insert the number:");
            stringUserInput = scanner.nextLine();
            if (!stringUserInput.matches("\\d+")) {
                System.out.println("Incorrect data.\nExit!");
                System.exit(0);
                return 0;
            } else return Integer.parseInt(stringUserInput);
        } else return Integer.parseInt(stringUserInput);
    }
}
