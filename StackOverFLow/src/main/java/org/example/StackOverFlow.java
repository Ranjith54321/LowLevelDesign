package org.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class StackOverFlow {

    private final Map<Integer, Question> questionMap;
    private final Map<Integer, Answer> answerMap;
    private final Map<Integer, User> userMap;
    private final Map<String, Tag> tagMap;

    public StackOverFlow() {
        this.questionMap = new ConcurrentHashMap<>();
        this.answerMap = new ConcurrentHashMap<>();
        this.userMap = new ConcurrentHashMap<>();
        this.tagMap = new ConcurrentHashMap<>();
    }

    public User registerUser(String name, String email) {
        int userId = userMap.size()+1;
        User user = new User(userId, name, email);
        userMap.put(userId, user);
        return user;
    }

    public Question askQuestion(User user, String title, String content, List<String> tags) {
        Question question = user.askQuestion(title, content, tags);
        questionMap.put(question.getId(), question);
        return  question;
    }

    public Answer answerQuestion(User user, Question question, String content) {
        Answer answer = user.answerToQuestion(question, content);
        answerMap.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(User user, Commentable commentable, String content) {
        return user.addComment(commentable, content);
    }

    public void voteQuestion(User user, Question question, int value) {
        question.vote(user, value);
    }

    public void voteAnswer(User user, Answer answer, int value) {
        answer.vote(user, value);
    }

    public List<Question> searchQuestionByQuery(String query) {
        return questionMap.values().stream().filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                q.getContent().toLowerCase().contains(query.toLowerCase()) ||
                q.getTags().stream().anyMatch( t -> t.getName().equalsIgnoreCase(query))).collect(Collectors.toList());
    }

    public List<Question> getQuestionByUser(User user) {
        return user.getQuestions();
    }

}
