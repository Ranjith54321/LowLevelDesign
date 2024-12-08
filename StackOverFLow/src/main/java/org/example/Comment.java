package org.example;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class Comment {

    private final int id;
    private final String content;
    private final User author;

    public Comment(User author, String content) {
        this.id = generateId();
        this.content = content;
        this.author = author;
    }

    private int generateId() {
        return (int) System.currentTimeMillis()%Integer.MAX_VALUE;
    }
}
