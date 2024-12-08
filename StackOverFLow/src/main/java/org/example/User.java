package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class User {
    private final int id;
    private final String name;
    private final String email;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private int reputation;

    private final int QUESTION_REPUTATION=5;
    private final int ANSWER_REPUTATION=10;
    private final int COMMENT_REPUTATION=2;


    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Question askQuestion(String title, String content, List<String> tags) {
        Question question = new Question(title,content, this, tags);
        questions.add(question);
        this.updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Answer answerToQuestion(Question question, String content) {
        Answer answer = new Answer(this, question,content);
        answers.add(answer);
        question.addAnswer(answer);
        this.updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        comments.add(comment);
        commentable.addComment(comment);
        this.updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public synchronized void updateReputation(int value) {
        this.reputation+=value;
        if(this.reputation < 0) {
            this.reputation = 0;
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
