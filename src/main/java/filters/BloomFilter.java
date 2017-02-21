package filters;

import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Jake Finnegan on 2/20/2017.
 */

public class BloomFilter<E> extends BloomFilterAbstract<E> {

    boolean[] myBooleanArray;
    double[] hashSeeds;

    public BloomFilter(int lengthOfTable, int numberOfHashes) {
        this(lengthOfTable, new double[numberOfHashes]);
        makeSeeds(numberOfHashes);
    }

    public BloomFilter(int lengthOfTable, double[] hashSeeds) {
        myBooleanArray = new boolean[lengthOfTable];
        this.hashSeeds = hashSeeds;
    }

    public BloomFilter(Collection<E> collection, int numberOfHashes) {
        this(collection.size(), numberOfHashes);
        for (E entry : collection) {
            add(entry);
        }
    }

    public void makeSeeds(int numberOfSeeds) {
        if (hashSeeds == null);
            hashSeeds = new double[numberOfSeeds];
        for(int i = 0; i < numberOfSeeds; i++) {
            hashSeeds[i] = Math.random() +1;
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
//            System.out.println(input.hashCode());
//            System.out.println(1/hashSeeds[i]);
//            System.out.println((Math.pow( input.hashCode() , 1/hashSeeds[i]))%1);
//            System.out.println((int)(((Math.pow( input.hashCode() , 1/hashSeeds[i]))%1)*(myBooleanArray.length+1)));
            index = (int) (((Math.pow( input.hashCode() , 1/hashSeeds[i]))%1)*(myBooleanArray.length+1));
//            System.out.println(index);
            myBooleanArray[index] = true;
        }
    }

    public boolean test(E input) {
        int index;
        for(int i = 0; i < hashSeeds.length; i++) {
            index = (int) (((Math.pow( input.hashCode() , 1/hashSeeds[i]))%1)*(myBooleanArray.length+1));
            System.out.println(index);
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
            bfLowLow.add(current);
            bfHighHigh.add(current);
            bfLowHigh.add(current);
            bfHighLow.add(current);
        }
    }
}
