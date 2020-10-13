package test.shane.multithread;

import java.util.Vector;

/**
 * @author shane
 */

public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }

        return absent;
    }
}
