package test;

import com.SimpleRecommender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 2:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleRecommenderTest {
    String path = null;
    SimpleRecommender sr = null;
    int[] m_ids = {260,1210,356,318,593,3578,1,2028,296,1259,2396,2916,780,541,1265,2571,527,2762,1198,34};

    @Before
    public void setUp() throws Exception {
        path = "/home/adil/Dropbox/workspace/recommenderSystem/NonPersonal/data/sample.csv";
        sr = new SimpleRecommender(path);
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
            System.out.println(i + " = "+ d);
        }
    }

    @Test
    public void testSimpleReco() throws Exception {
        for(int i : m_ids) {
            double d = sr.simpleReco(260,i);
            System.out.printf(i + " = %.2f\n", d);
        }
    }
}
