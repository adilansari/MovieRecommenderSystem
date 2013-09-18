package com;

import com.sun.jmx.snmp.SnmpPduTrap;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 1:56 AM
 * Simple Recommender
 */
public class Recommender {

    private String dataPath = null;
    private HashMap<Integer, User> users;
    private HashMap<Integer, Movie> movies;

    public Recommender(String dataPath) {
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

    private double simpleReco(int master_id, int child_id) {
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
        double r = ((double) commonUsers / (double) masterSize);
        return r;
    }

    public Set simpleRecommender(int m_id){
        Map<Integer , Double> resultSet = new HashMap<Integer, Double>();
        if(!movies.containsKey(m_id))
            return null;

        Iterator itr = movies.keySet().iterator();
        while(itr.hasNext()) {
            int c_id = (Integer) itr.next();
            if( m_id != c_id)
                resultSet.put(c_id, simpleReco(m_id,c_id));
        }

        TreeSet sortedSet = (TreeSet) entriesSortedByValues(resultSet);

        return sortedSet;
    }

    private double advReco(int master_id, int child_id) {
        if(!movies.containsKey(master_id) || !movies.containsKey(child_id))
            return -1;
        double Numerator = simpleReco(master_id, child_id);
        HashMap masterMap = movies.get(master_id).getRatingMap();
        HashMap childMap = movies.get(child_id).getRatingMap();
        int commonCount=0;
        Iterator itr = users.keySet().iterator();
        while(itr.hasNext()) {
            User u = users.get((Integer) itr.next());
            if(!masterMap.containsKey(u) && childMap.containsKey(u))
                commonCount++;
        }

        int notMasterSize = users.size()-masterMap.size();

        double Denominator = (double) commonCount / (double) notMasterSize;

        return (Numerator/Denominator);
    }

    public Set advRecommender(int m_id){
        Map<Integer , Double> resultSet = new HashMap<Integer, Double>();
        if(!movies.containsKey(m_id))
            return null;

        Iterator itr = movies.keySet().iterator();
        while(itr.hasNext()) {
            int c_id = (Integer) itr.next();
            if( m_id != c_id)
                resultSet.put(c_id, advReco(m_id,c_id));
        }

        TreeSet sortedSet = (TreeSet) entriesSortedByValues(resultSet);

        return sortedSet;
    }

    public <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
