package test.puzzler;

/**
 * @author shane
 * @see Words
 */
public class Words3 {
    private Words3() {
    }

    /**
     * 不是常量变量表达式，因此FIRST不会在编译期被写入到class文件
     */
    public static final String FIRST = ident("the");
    public static final String SECOND = ident(null);
    public static final String THIRD = ident("set");

    private static String ident(String s) {
        return s;
    }

}

class Words4 {
    private Words4() {
    }

    /**
     * 不是常量变量表达式，因此FIRST不会在编译期被写入到class文件
     */
    public static final String FIRST = ident("physics");
    public static final String SECOND = ident("chemistry");
    public static final String THIRD = ident("biology");

    private static String ident(String s) {
        return s;
    }

} 