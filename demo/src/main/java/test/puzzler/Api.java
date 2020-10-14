package test.puzzler;

/**
 * @author shane
 */

class ApiBase {
    public static final int ANSWER = 42;
}

public class Api extends ApiBase {
    private static final int ANSWER = 6 * 9;

    // private static class String{ }
    public static String newString() {
        return new String();
    }
}
