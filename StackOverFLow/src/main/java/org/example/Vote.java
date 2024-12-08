package org.example;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class Vote {
    private final int id;
    private final int value;
    private final User author;

    public Vote(User author, int value) {
        this.id = generateId();
        this.value = value;
        this.author = author;
    }

    public int getValue() {
        return value;
    }

    public User getAuthor() {
        return author;
    }

    private int generateId() {
        return (int) System.currentTimeMillis()%Integer.MAX_VALUE;
    }
}
