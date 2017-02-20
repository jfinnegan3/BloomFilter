package filters;


import java.util.Collection;

/**
 * Created by Jake Finnegan on 2/20/2017.
 */

public class BloomFilter<E> extends BloomFilterAbstract<E> {

    boolean[] myBooleanArray;
    int[] hashseeds;

    public BloomFilter(int lengthOfTable, int numberOfHashes) {
        this(lengthOfTable, new int[numberOfHashes]);
        makeSeeds(numberOfHashes);
    }

    public BloomFilter(int lengthOfTable, int[] hashseeds) {
        myBooleanArray = new boolean[lengthOfTable];
        this.hashseeds = hashseeds;
    }

    public BloomFilter(Collection<E> collection, int numberOfHashes) {
        this(collection.size()*10, numberOfHashes);
        for (E entry : collection) {
            add(entry);
        }
    }

    public void makeSeeds(int numberOfSeeds) {
        if (hashseeds == null);
            hashseeds = new int[numberOfSeeds];
        for(int i = 0; i < numberOfSeeds; i++) {
            hashseeds[i] = (int)(Math.random()*myBooleanArray.length);
        }
    }

    public boolean[] getArray() {
        return myBooleanArray;
    }

    public void add(Collection<E> input) {
        for (E entry : input) {
            add(entry);
        }
    }

    public void add(E input) {
        int index;
        for(int i = 0; i < hashseeds.length; i++) {
            index = (int) (((Math.pow( input.hashCode() , (double)(-hashseeds[i])))%1)*(myBooleanArray.length+1));
            myBooleanArray[index] = true;
        }
    }

    public boolean test() {
        return false;
    }

}
