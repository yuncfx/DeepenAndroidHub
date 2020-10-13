package test.shane.multithread;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}