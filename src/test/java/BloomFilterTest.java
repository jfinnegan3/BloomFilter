
import filters.BloomFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class BloomFilterTest {

    BloomFilter filter1;
    BloomFilter filter2;
    ArrayList<String> stringList;

    @Before
    public void setUp() throws Exception {
        stringList = new ArrayList<String>(50);
        filter1 = new BloomFilter<String>(50, 0);
        filter2 = new BloomFilter<String>(stringList, 0);
    }

    @Test
    public void testConstructor() {
        assert(filter1 != null);
        assert(filter1.getArray().length == 50);
    }

    @Test
    public void testConstructor2() {
        assert(filter2 != null);
        assert(filter2.getArray().length == stringList.size());
    }

    @Test
    public void testHashFunction() {
        assert(filter1.)
    }

    public void testInsertData() {
    }
}