package org.example;

/**
 * Created by Ranjith S on 05/12/24.
 **/
public class Tag {
    private final int id;
    /**
     tag don't need Author unlike Vote,
        because: Imagine if you are up-Vote and agine down vote after some time, we need to keep track.
     But, in tags any user can add and remove the tag. (until, if Interviewer asks any feature), that's why we dont maintain Autor for tag.

     */

    private final String name;

    public Tag(String name) {
        this.id = generateId();
        this.name = name;
    }

    private int generateId() {
        return (int) System.currentTimeMillis()%Integer.MAX_VALUE;
    }

    public String getName() {
        return name;
    }
}
