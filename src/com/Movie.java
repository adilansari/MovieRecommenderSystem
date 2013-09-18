package com;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 12:23 AM
 * Movie data structure
 */
public class Movie {
    private int id;
    private HashMap<User, Integer> ratings;

    Movie(int id) {
        this.id = id;
        ratings = new HashMap<User, Integer>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addRating(User user, int rating) {
        ratings.put(user,rating);
    }

    public int getRating(User user) {
        if(ratings.containsKey(user))
            return ratings.get(user);
        else
            return -1;
    }

    public HashMap getRatingMap() {
        return ratings;
    }
}
