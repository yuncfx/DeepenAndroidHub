package test.shanemao.puzzler;

/**
 * @author shane
 */

final class Enigma {
    /**
     * Don't do this
     * <p>
     * 尽管这个声明能够解决本谜题，但是它的做法确实非常不好的。它违反了谜题
     * 58 的建议：如果同一个方法的两个重载版本都可以应用于某些参数，那么它们
     * 应该具有相同的行为。在本例中，e.equals(e)和 e.equals((Object)e)将返回
     * 不同的结果，其潜在的混乱是显而易见的。
     *
     * @see Conundrum
     */
    public Boolean equals(Enigma enigma) {
        return false;
    }

    /**
     * 能够产生我们想要的
     * 输出的 println 调用出现在了构造器中，而不是在 main 方法中。然而，它确实
     * 解决了这个谜题，你不得不承认它很伶俐。
     */
    public Enigma() {
        System.out.println(false);
        System.exit(0);
    }
}
