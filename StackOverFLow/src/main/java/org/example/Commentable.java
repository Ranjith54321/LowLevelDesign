package org.example;

import java.util.List;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public interface Commentable {
    void addComment(Comment comment);
    List<Comment> getComments();
}
