package concurrency.multithread;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        // long compute
        return new BigInteger(arg);
    }
}