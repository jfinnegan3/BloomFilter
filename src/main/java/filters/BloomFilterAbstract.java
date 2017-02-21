package filters;

/**
 * Created by Jake Finnegan on 2/20/2017.
 */
public abstract class BloomFilterAbstract<E> {

    public abstract void add(E thing);

    public abstract boolean test(E thing);

}
