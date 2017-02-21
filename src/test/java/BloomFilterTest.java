
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
        filter1 = new BloomFilter<String>(50, 2);
        filter2 = new BloomFilter<String>(stringList, 2);
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
    public void testFilter() {
        String testString = "test";
        filter1.add(testString);
        assert(filter1.test(testString));
        assert(!filter2.test(testString));
    }

    public void testInsertData() {
    }
}