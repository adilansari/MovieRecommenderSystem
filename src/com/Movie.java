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
    private HashMap<User, Double> ratings;

    Movie(int id) {
        this.id = id;
        ratings = new HashMap<User, Double>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addRating(User user, Double rating) {
        ratings.put(user,rating);
    }

    public Double getRating(User user) {
        if(ratings.containsKey(user))
            return ratings.get(user);
        else
            return -1.00;
    }

    public HashMap getRatingMap() {
        return ratings;
    }
}
