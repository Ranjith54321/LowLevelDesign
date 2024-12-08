package org.example;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public interface Votable {
    void vote(User user, int value);
    int getVoteCount();
}
