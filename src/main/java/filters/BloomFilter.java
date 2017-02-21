package filters;


import javafx.scene.effect.Bloom;

import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Jake Finnegan on 2/20/2017.
 */

public class BloomFilter<E> extends BloomFilterAbstract<E> {

    boolean[] myBooleanArray;
    int[] hashSeeds;

    public BloomFilter(int lengthOfTable, int numberOfHashes) {
        this(lengthOfTable, new int[numberOfHashes]);
        makeSeeds(numberOfHashes);
    }

    public BloomFilter(int lengthOfTable, int[] hashseeds) {
        myBooleanArray = new boolean[lengthOfTable];
        this.hashSeeds = hashseeds;
    }

    public BloomFilter(Collection<E> collection, int numberOfHashes) {
        this(collection.size()*10, numberOfHashes);
        for (E entry : collection) {
            add(entry);
        }
    }

    public void makeSeeds(int numberOfSeeds) {
        if (hashSeeds == null);
            hashSeeds = new int[numberOfSeeds];
        for(int i = 0; i < numberOfSeeds; i++) {
            hashSeeds[i] = (int)(Math.random()*myBooleanArray.length);
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
        for(int i = 0; i < hashSeeds.length; i++) {
            index = (int) (((Math.pow( input.hashCode() , (double)(-hashSeeds[i])))%1)*(myBooleanArray.length+1));
            myBooleanArray[index] = true;
        }
    }

    public boolean test(E input) {
        int index;
        for(int i = 0; i < hashSeeds.length; i++) {
            index = (int) (((Math.pow( input.hashCode() , (double)(-hashSeeds[i])))%1)*(myBooleanArray.length+1));
            if (myBooleanArray[index] == false)
                return false;
        }
        return true;
    }

    public boolean[] test(Collection<E> input) {
        boolean[] answer = new boolean[input.size()];
        int i = 0;
        for (E entry : input) {
            answer[i] = test(entry);
            i++;
        }
        return answer;
    }

    public static void main(String[] args) {

        BloomFilter<String> bfLowLow = new BloomFilter<String>(10, 2);
        BloomFilter<String> bfHighHigh = new BloomFilter<String>(4000, 10);
        BloomFilter<String> bfLowHigh = new BloomFilter<String>(10, 10);
        BloomFilter<String> bfHighLow = new BloomFilter<String>(4000, 2);


        Scanner scanner = new Scanner(System.in);

        String current;
        while(scanner.hasNext()) {
            current = scanner.next();

        }
    }
}
