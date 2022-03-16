package com.itbatia.crud.utils;

public enum Messages {

    MAIN_MENU_SELECTION("\nMain menu:\n1 - Tags\n2 - Posts\n3 - Writers\n0 - Exit the program"),
    TAG_MENU_SELECTION("\nTags menu:\n1 - Create tag\n2 - Get tag by id\n3 - Update tag\n4 - Delete tag" +
            "\n5 - Get all tags\n6 - Back to main menu\n0 - Exit the program"),
    AFTER_GET_TAG_MENU_SELECTION("What do you want to do with this tag?\n1 - Update tag" +
            "\n2 - Delete tag\n3 - Nothing. Back to Tag menu"),
    POST_MENU_SELECTION("\nPosts Menu:\n1 - Create post\n2 - Get post by id\n3 - Update post" +
            "\n4 - Delete post\n5 - Get all posts\n6 - Back to main menu\n0 - Exit the program"),
    AFTER_GET_POST_MENU_SELECTION("What do you want to do with this post?\n1 - Update post" +
            "\n2 - Delete post\n3 - Nothing. Back to Post menu"),
    WRITER_MENU_SELECTION("\nWriters menu:\n1 - Create writer\n2 - Get writer by id\n3 - Update writer" +
            "\n4 - Delete writer\n5 - Get all writers\n6 - Back to main menu\n0 - Exit the program"),
    AFTER_GET_WRITER_MENU_SELECTION("What do you want to do with this writer?\n1 - Update writer" +
            "\n2 - Delete writer\n3 - Get all posts this writer\n4 - Nothing. Back to Writer menu"),
    UPDATE_POST_SELECTION_1("What do you want to do?\n1 - Change content\n2 - Add new tag"+
        "\n3 - Delete tag\n4 - Update post status\nq - quit"),
    UPDATE_POST_SELECTION_2("What else do you want to do?\n1 - Change content\n2 - Add new tag" +
                                    "\n3 - Delete tag\n4 - Update post status\nq - quit"),
    CONFIRMATION("Do you confirm the deletion?\n1 - Yes\n2 - No"),
    CONFIRMATION_2("Are you sure?\n1 - Yes\n2 - No"),
    UPDATE_WRITER_SELECTION_1("\nWhat do you want to do?\n1 - Change name\n2 - Update post" +
            "\n3 - Create new post\n4 - Add created post\n5 - Delete post\nq - quit"),
    UPDATE_WRITER_SELECTION_2("Done! Do you want to change anything else?\n1 - Change name\n2 - Update post" +
            "\n3 - Create new post\n4 - Add created post\n5 - Delete post\nq - quit");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
