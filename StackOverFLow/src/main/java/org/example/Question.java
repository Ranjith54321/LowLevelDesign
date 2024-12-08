package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class Question implements Commentable, Votable {
    private final int id;
    private final String title;
    private final String content;
    private final Date createdDate;
    private final User author;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Vote> votes;

    public Question(String title, String content, User author, List<String> tags) {
        this.id = generateId();
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = new Date();
        this.tags = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();

        for (String tag: tags) {
            this.tags.add(new Tag(tag));
        }
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
    private int generateId() {
        return (int) System.currentTimeMillis()%Integer.MAX_VALUE;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void vote(User user, int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("Value must be either 1 or -1");
        }
        votes.removeIf(v -> v.getAuthor().equals(user));
        votes.add(new Vote(user, value));
        this.author.updateReputation(value*5);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
