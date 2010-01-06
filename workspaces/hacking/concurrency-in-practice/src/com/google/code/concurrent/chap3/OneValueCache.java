package com.google.code.concurrent.chap3;

import java.math.BigInteger;
import java.util.Arrays;

import net.jcip.annotations.Immutable;

/**
 * OneValueCache
 * <p/>
 * Immutable holder for caching a number and its factors
 * 
 * This class can be used on a thread-safe environment to hold values that depend on 
 * compound operations. If an instance of this class is declared as volatile and the reference is updated
 * by one thread, all the other threads automatically will see the changes.
 * 
 * @see VolatileCachedFactorizer as an example of class that uses volitile object reference.
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
