package com.itbatia.crud.view;

import com.itbatia.crud.controller.TagController;
import com.itbatia.crud.molel.Tag;

import static com.itbatia.crud.utils.ShowInConsole.*;

import java.util.List;
import java.util.Scanner;

public class TagView {
    private final TagController tagController = new TagController();
    private final Scanner scanner = new Scanner(System.in);

    public Tag createTag() {
        System.out.println("Enter new Tag name: ");
        String name = scanner.nextLine();
        Tag createdTag = tagController.createTag(name);
        System.out.print("Tag created. ");
        return createdTag;
    }

    public Tag getTag() {
        int id = idFromUser();
        if (id != 0) {
            System.out.printf("Found the Tag with name \"%s\". ", tagController.getTag(id).getName());
            return tagController.getTag(id);
        } else return null;
    }

    public List<Tag> getAllTags() {
        List<Tag> tags = tagController.getAllTags();
        showTags(tags);
        return tags;
    }

    public void updateTagById() {
        int id = idFromUser();
        if (id != 0) {
            Tag tag = tagController.getTag(id);
            System.out.print("Found tag: \"" + tag.getName() + "\". ");
            updateTag(tag);
        }
    }

    public void updateTagByTag(Tag tag) {
        if (tag != null) {
            updateTag(tag);
        }
    }

    private void updateTag(Tag tag) {
        System.out.println("Enter new Tag name:");
        String newName = scanner.nextLine();
        tagController.updateTag(tag.getId(), newName);
        System.out.printf("Tag %s updated to \"%s\"\n", tag.getId(), newName);
    }

    public void deleteTagById() {
        int id = idFromUser();
        if (id != 0) {
            Tag tag = tagController.getTag(id);
            System.out.println("Found tag: \"" + tag.getName() + "\".");
            System.out.println("Do you confirm the deletion?\n1 - Yes\n2 - No");
            deleteTag(tag);
        }
    }

    public void deleteTagByTag(Tag tag) {
        if (tag != null) {
            System.out.println("Are you sure?\n1 - Yes\n2 - No");
            deleteTag(tag);
        }
    }

    private void deleteTag(Tag tag) {
        String userChoice = scanner.nextLine();
        if (userChoice.equals("1")) {
            System.out.println("Tag deleted successfully!");
            tagController.deleteTag(tag.getId());
        } else {
            System.out.println("Back to Tag menu.");
        }
    }

    private Integer idFromUser() {
        System.out.println("Enter Tag ID:");
        String stringID = scanner.nextLine();
        if (!stringID.matches("\\d+")) {
            System.out.println("Incorrect data.");
            return 0;
        }
        int intID = Integer.parseInt(stringID);
        if (tagController.getTag(intID) == null) {
            System.out.printf("Tag %s doesn't exist.\n", intID);
            return 0;
        } else return intID;
    }
}
