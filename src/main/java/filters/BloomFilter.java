package filters;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
            index = (int) (((Math.pow( input.hashCode() , 1/hashSeeds[i]))%1)*(myBooleanArray.length));
//            System.out.println(index);
            myBooleanArray[index] = true;
        }
    }

    public boolean test(E input) {
        int index;
        for(int i = 0; i < hashSeeds.length; i++) {
            index = (int) (((Math.pow( input.hashCode() , 1/hashSeeds[i]))%1)*(myBooleanArray.length));
//            System.out.println(index);
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

        BloomFilter<String> bfLowLow = new BloomFilter<String>(100, 2);
        BloomFilter<String> bfHighHigh = new BloomFilter<String>(500, 15);
        BloomFilter<String> bfLowHigh = new BloomFilter<String>(100, 15);
        BloomFilter<String> bfHighLow = new BloomFilter<String>(500, 2);

        HashSet<String> stringSet = new HashSet<String>();

        Scanner scanner = new Scanner(System.in);

        String current;
//        while(scanner.hasNext()) {
//            current = scanner.next();
//
//
//            if(current.equals("end"))
//                break;

        for (int i = 0; i < 1000; i++) {

            current = new BigInteger(130, new SecureRandom()).toString();

            bfLowLow.add(current);
            bfHighHigh.add(current);
            bfLowHigh.add(current);
            bfHighLow.add(current);

            stringSet.add(current);
        }


        boolean anyFalseNegatives = false;
        //verification
        for(String testString : stringSet) {
            if (!(bfLowLow.test(testString) && bfHighHigh.test(testString)
                && bfHighLow.test(testString) && bfLowHigh.test(testString))) {
                System.out.println("False Negative!");
                anyFalseNegatives = true;
            }
        }

        if(anyFalseNegatives) {
            System.out.println("There were false negatives. Algorithm Incorrect");
        } else {

            ArrayList<String> manyStrings = new ArrayList<String>();
            String temp;
            for(int i = 0; i < 2000; i++) {
                temp = new BigInteger(130, new SecureRandom()).toString();
                System.out.println(temp);
                manyStrings.add(temp);
            }
            int ll = 0;
            int lh = 0;
            int hl = 0;
            int hh = 0;

            for (String testString : manyStrings) {
                if (bfLowLow.test(testString))
                    ll++;
                if (bfHighHigh.test(testString))
                    hh++;
                if (bfHighLow.test( testString))
                    hl++;
                if (bfLowHigh.test(testString))
                    lh++;
            }

            System.out.println("False Positive Rates: ");
            System.out.println("Large Table, Many Hashes: " + hh);
            System.out.println("Large Table, Few Hashes:  " + hl);
            System.out.println("Small Table, Many Hashes: " + lh);
            System.out.println("Small Table, Few Hashes:  " + ll);
        }

    }
}
