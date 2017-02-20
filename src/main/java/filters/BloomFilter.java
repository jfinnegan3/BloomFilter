package filters;


import java.util.Collection;

/**
 * Created by Jake Finnegan on 2/20/2017.
 */
public class BloomFilter<E> extends BloomFilterAbstract<E> {

    boolean[] myBooleanArray;

    public BloomFilter(int n) {
        myBooleanArray = new boolean[n];
    }

    public BloomFilter(Collection<E> collection) {
        myBooleanArray = new boolean[collection.size()];
        for (E entry : collection) {
            add(entry);
        }
    }

    public boolean[] getArray() {
        return myBooleanArray;
    }

    public void add(E input) {
        myBooleanArray[input.hashCode()%myBooleanArray.length] = true;
    }

    public boolean test() {
        return false;
    }

}
