package test;


import com.DataLoader;
import com.Movie;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: adil
 * Date: 9/18/13
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */

public class DataLoaderTest extends TestCase {

    String path = null;
    DataLoader dl = null;

    @Before
    public void setUp() throws Exception {
        path = "/home/adil/Dropbox/workspace/recommenderSystem/NonPersonal/data/sample.csv";
        dl = new DataLoader(path);
    }

    @After
    public void tearDown() throws Exception {
        dl = null;
    }

    @Test
    public void testGetUsers() throws Exception {
        HashMap map = dl.getUsers();
        assertEquals(20, map.size());
    }

    @Test
    public void testGetMovies() throws Exception {
        HashMap map = dl.getMovies();
        assertEquals(20, map.size());
    }

    @Test
    public void testRatingCount() throws Exception {
        HashMap map = dl.getMovies();
        int count = 0;
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Movie m= (Movie) pair.getValue();
            count += m.getRatingMap().size();
        }
        assertEquals(241, count);
    }
}
