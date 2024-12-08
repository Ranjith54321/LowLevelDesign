package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class Answer implements Commentable, Votable {

    private final int id;
    private final String content;
    private final Date createdDate;
    private final User author;
    private final Question question;
    private final List<Comment> comments;
    private final List<Vote> votes;
    private boolean isAccepted;

    public Answer(User author, Question question, String content) {
        this.id = generateId();
        this.content = content;
        this.author = author;
        this.createdDate = new Date();
        this.isAccepted = false;
        this.question = question;
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    private int generateId() {
        return (int) System.currentTimeMillis()%Integer.MAX_VALUE;
    }

    public void acceptAnswer() {
        if (this.isAccepted) {
            throw new IllegalArgumentException(" Answer Already Accepted");
        }
        this.isAccepted = true;
        this.author.updateReputation(15);
    }


    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void vote(User user, int value) {
        if(value != 1 && value!=-1) {
            throw new IllegalArgumentException("Value must be either 1 or -1");
        }
        votes.removeIf(v -> v.getAuthor().equals(user));
        votes.add(new Vote(user, value));
        this.author.updateReputation(value*10);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public int getId() {
        return id;
    }
}
