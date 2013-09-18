package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 12:31 AM
 * Loads data from csv in the format "user_id, movie_id, rating
 * Returns a hashmap of users and movies
 */
public class DataLoader {

    private HashMap<Integer, User> users;
    private HashMap<Integer, Movie> movies;
    private String path;

    public DataLoader(String path) {
        this.path = path;
        users = new HashMap<Integer, User>();
        movies = new HashMap<Integer, Movie>();
        loadData(path);
    }

    public HashMap getUsers() {
        return users;
    }

    public HashMap getMovies() {
        return movies;
    }

    private void loadData(String path) {
        String splitby = ",";
        String line = "";
        BufferedReader br = null;
        int count = 1;

        try {
            br= new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null) {
                String data[] = line.split(splitby);

                int user_id = Integer.parseInt(data[0].trim());
                User user = updateUsers(user_id);
                int muvi_id = Integer.parseInt(data[1].trim());
                Movie movie = updateMovies(muvi_id);
                Double rating = Double.parseDouble(data[2].trim());
                updateRating(user_id,muvi_id,rating);


                //System.out.println((count++) + " - "+ Arrays.toString(data) );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private User updateUsers(int u_id) {
        User user = null;
        if(!users.containsKey(u_id)) {
            user = new User(u_id);
            users.put(u_id, user);
        }
        user = users.get(u_id);
        return user;
    }

    private Movie updateMovies(int m_id) {
        Movie movie = null;
        if(!movies.containsKey(m_id)) {
            movie = new Movie(m_id);
            movies.put(m_id, movie);
        }
        movie = movies.get(m_id);
        return movie;
    }

    private boolean updateRating(int user_id, int muvi_id, Double rating) {
        User user = users.get(user_id);
        Movie muvi = movies.get(muvi_id);
        muvi.addRating(user,rating);
        return true;
    }
}
