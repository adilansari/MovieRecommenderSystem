package com;

import com.sun.jmx.snmp.SnmpPduTrap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 1:56 AM
 * Simple Recommender
 */
public class SimpleRecommender {

    private String dataPath = null;
    private HashMap<Integer, User> users;
    private HashMap<Integer, Movie> movies;

    public SimpleRecommender(String dataPath) {
        DataLoader dl = new DataLoader(dataPath);
        users = dl.getUsers();
        movies = dl.getMovies();
    }

    public double meanRating(int muvi_Id) {
        Movie muvie = null;
        if(!movies.containsKey(muvi_Id))
            return -1;
        else
            muvie = movies.get(muvi_Id);

        int ratingSum = 0;
        HashMap r_map = muvie.getRatingMap();
        Iterator itr = r_map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair= (Map.Entry) itr.next();
            ratingSum +=  (Integer) pair.getValue();
        }

        double d = ((double) ratingSum/ (double) r_map.size());
        return d;
    }

    public int fourPlus(int muvi_Id) {
        Movie muvie = null;
        if(!movies.containsKey(muvi_Id))
            return -1;
        else
            muvie = movies.get(muvi_Id);

        int ratingCount = 0;
        HashMap r_map = muvie.getRatingMap();
        Iterator itr = r_map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair= (Map.Entry) itr.next();
            int rating =  (Integer) pair.getValue();
            if( rating >= 4)
                ratingCount++;
        }
        return ratingCount;
    }

    public double simpleReco(int master_id, int child_id) {
        //raters of (child_id and master_id)/master_id;

        if(!movies.containsKey(master_id) || !movies.containsKey(child_id))
            return -1;
        HashMap masterMap = movies.get(master_id).getRatingMap();
        HashMap childMap = movies.get(child_id).getRatingMap();
        int masterSize = masterMap.size();
        int commonUsers = 0;
        Iterator itr = childMap.keySet().iterator();
        while(itr.hasNext()) {
            User u = (User) itr.next();
            if(masterMap.containsKey(u))
                commonUsers++;
        }
        double r = ((double) commonUsers / (double) masterSize) * 100;
        return r;
    }
}
