package test;

import com.Recommender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 2:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecommenderTest {
    String path = null;
    Recommender sr = null;
    int[] m_ids = {260,1210,356,318,593,3578,1,2028,296,1259,2396,2916,780,541,1265,2571,527,2762,1198,34};
    //int[] input = {11,121,8587};
    int[] input = {3049,788,752};

    @Before
    public void setUp() throws Exception {
        path = "/home/adil/Dropbox/workspace/recommenderSystem/NonPersonal/data/recsys-data-ratings.csv";
        sr = new Recommender(path);
    }

    @After
    public void tearDown() throws Exception {
        sr = null;
    }

    @Test
    public void testMeanRating() throws Exception {
        for(int i : m_ids) {
            double d = sr.meanRating(i);
            System.out.printf(i + " = %.2f\n", d);
        }
    }

    @Test
    public void testFourPlus() throws Exception {
        for(int i : m_ids) {
            int d = sr.fourPlus(i);
            System.out.println(i + " = " + d);
        }
    }

    @Test
    public void testSimpleRecommender() throws Exception {
        for (int k : input) {
            Set map = sr.simpleRecommender(k);
            Iterator itr = map.iterator();
            System.out.print(k);
            for(int i = 1; i<=5; i++) {
                Entry pair= (Entry) itr.next();

                System.out.print("," + pair.getKey() + "," + roundTwoDecimals(Double.parseDouble(String.valueOf(pair.getValue()))));
            }
            System.out.println();
        }
    }

    private double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    @Test
    public void testAdvRecommender() throws Exception {
        for (int k : input) {
            Set map = sr.advRecommender(k);
            Iterator itr = map.iterator();
            System.out.print(k);
            for(int i = 1; i<=5; i++) {
                Entry pair= (Entry) itr.next();

                System.out.print("," + pair.getKey() + "," + roundTwoDecimals(Double.parseDouble(String.valueOf(pair.getValue()))));
            }
            System.out.println();
        }
    }
}
