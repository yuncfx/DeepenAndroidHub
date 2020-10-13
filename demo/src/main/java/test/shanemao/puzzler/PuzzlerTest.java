package test.shanemao.puzzler;

import org.junit.Test;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * puzzler test
 *
 * @author shane
 */

public class PuzzlerTest {

    private static Random rnd = new Random();

    public static void main(String[] args) {

        System.out.println(isOddErr(-1));

    }

    /**
     * puzzler 1, 在任何负整数时，该方法返回false
     * 这是 Java 对取余操作符（%）的定义所产生的后果。该操作符被定义为对于所
     * 有的 int 数值 a 和所有的非零 int 数值 b，都满足下面的恒等式：
     * (a / b) * b + (a % b) == a
     * 换句话说，如果你用 b 整除 a，将商乘以 b，然后加上余数，那么你就得到了最
     * 初的值 a 。该恒等式具有正确的含义，但是当与 Java 的截尾整数整除操作符
     * 相结合时，它就意味着：当取余操作返回一个非零的结果时，它与左操作数具有
     * 相同的正负符号。
     */
    private static boolean isOddErr(int i) {
        return i % 2 == 1;
    }

    private static boolean isOddRight(int i) {
        return i % 2 != 0;
    }

    private static boolean isOddRight2(int i) {
        return (i & 1) != 0;
    }

    /**
     * 并不是所有的小数都可以用二进制浮点数来精确表示的。
     * 二进制浮点对于货币计算是非常不适合的， 因为它不可能将 0.1——或者 10 的其它任何次负幂——
     * 精确表示为一个长度有限的二进制小数
     * 解决该问题的一种方式是使用某种整数类型，例如 int 或 long，并且以分为单
     * 位来执行计算。
     * 在需要精确答案的地方，要避免使用 float 和 double；对于货币计算，
     * 要使用 int、long 或 BigDecimal。
     * 一定要用BigDecimal(String)构造器，而千万不要用 BigDecimal(double)。
     */
    @Test
    public void puzzler2() {
        System.out.println((2.00 - 1.10));
    }

    @Test
    public void puzzler3() {
        // numeric overflow
        long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        long MICROS_PER_DAY_2 = 24 * 60 * 60 * 1000 * 1000L;
        long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    }

    /**
     * 十进制字面常量具有一个很好的属性，即所有的十进制字面常量都是正的，而十
     * 六进制或是八进制字面常量并不具备这个属性。要想书写一个负的十进制常量，
     * 可以使用一元取反操作符（-）连接一个十进制字面常量。
     * 但是十六进制和八进制字面常量并不是这么回事，它们可以具有正的以及负的数值。如果十六进制和八进制字
     * 面常量的最高位被置位了， 那么它们就是负数。 在这个程序中， 数字 0xcafebabe
     * 是一个 int 常量，它的最高位被置位了，所以它是一个负数。它等于十进制数值 -889275714
     * <p>
     * 该程序执行的这个加法是一种“混合类型的计算（mixed-type computation）:
     * 左操作数是 long 类型的，而右操作数是 int 类型的。为了执行该计算，Java 将
     * int 类型的数值用拓宽原始类型转换提升为一个 long 类型，然后对两个 long 类
     * 型数值相加。因为 int 是一个有符号的整数类型，所以这个转换执行的是符合扩
     * 展：它将负的 int 类型的数值提升为一个在数值上相等的 long 类型数值。
     * <p>
     * 这个加法的右操作数 0xcafebabe 被提升为了 long 类型的数值
     * 0xffffffffcafebabeL。这个数值之后被加到了左操作数 0x100000000L 上。当作
     * 为 int 类型来被审视时，经过符号扩展之后的右操作数的高 32 位是-1，而左操
     * 作数的高 32 位是 1，将这两个数值相加就得到了 0，这也就解释了为什么在程序
     * 输出中前导 1 丢失了。下面所示是用手写的加法实现。 （在加法上面的数字是进
     * 位。）
     * 1111111
     * 0xffffffffcafebabeL
     * + 0x0000000100000000L
     * ---------------------
     * 0x00000000cafebabeL
     */
    @Test
    public void puzzler5() {
        //"cafebabe"
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));
        //-889275714
        System.out.println(0xffffffffcafebabeL);
        //-889275714
        System.out.println(0xcafebabe);
        //"1cafebabe"
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
    }

    /**
     * 有一条很简单的规则能够描述从较窄的整
     * 型转换成较宽的整型时的符号扩展行为：如果最初的数值类型是有符号的，那么
     * 就执行符号扩展；如果它是 char，那么不管它将要被转换成什么类型，都执行
     * 零扩展。了解这条规则可以使我们很容易地解决这个谜题。
     * 因为 byte 是一个有符号的类型，所以在将 byte 数值-1 转换成 char 时，会发生
     * 符号扩展。作为结果的 char 数值的 16 个位就都被置位了，因此它等于 216-1，
     * 即 65535。从 char 到 int 的转型也是一个拓宽原始类型转换，所以这条规则告
     * 诉我们， 它将执行零扩展而不是符号扩展。 作为结果的 int 数值也就成了 65535，
     * 这正是程序打印出的结果。
     * <p>
     * 如果你在将一个 char 数值 c 转型为一个宽度更宽的类型，并且你不希望有符号
     * 扩展，那么为清晰表达意图，可以考虑使用一个位掩码，即使它并不是必需的：
     * int i = c & 0xffff;
     * 或者，书写一句注释来描述转换的行为：
     * int i = c; //不会执行符号扩展
     * 如果你在将一个 char 数值 c 转型为一个宽度更宽的整型，并且你希望有符号扩
     * 展，那么就先将 char 转型为一个 short，它与 char 具有同样的宽度，但是它是
     * 有符号的。在给出了这种细微的代码之后，你应该也为它书写一句注释：
     * int i = (short) c; //转型将引起符号扩展
     * 如果你在将一个 byte 数值 b 转型为一个 char，并且你不希望有符号扩展，那么
     * 你必须使用一个位掩码来限制它。这是一种通用做法，所以不需要任何注释：
     * char c = (char) (b & 0xff);
     * 这个教训很简单：如果你通过观察不能确定程序将要做什么，那么它做的就很有
     * 可能不是你想要的。
     */
    @Test
    public void puzzler6() {
        // 65535
        System.out.println((int) (char) (byte) -1);
    }

    /**
     * Java 语言规范描述到：操作符的操作数是从左向右求值的(但是操作符还是从右往左赋值的)。为了求表达式 x ^=
     * expr 的值，x 的值是在计算 expr 之前被提取的，并且这两个值的异或结果被赋
     * 给变量 x。
     */
    @Test
    public void puzzler7() {
        int x = 1984;
        int y = 2001;
        x ^= y ^= x ^= y;
        System.out.println("x=" + x + "; y=" + y);
    }

    /**
     * The actual behavior of x ^=y ^= x^= y in Java
     * 在单个的表达式中不要对相同的变量赋值两次
     * <p>
     * 要避免所谓聪明的编程技巧。
     * 它们都是易于产生 bug 的，很难以维护，并且运行速度经常是比它们所替代掉的
     * 简单直观的代码要慢。
     */
    @Test
    public void puzzler7Equals() {
        int x = 1984;
        int y = 2001;
        // first appearance of x in the expression
        int tmp1 = x;
        // first appearance of y
        int tmp2 = y;
        // compute x ^ y
        int tmp3 = x ^ y;
        // last assignment : store x^y in x
        x = tmp3;
        // 2nd assignment : store original x value in y
        y = tmp2 ^ tmp3;
        // first assignment: store 0 in x
        x = tmp1 ^ y;

        System.out.println("x=" + x + "; y=" + y);
    }

    //works, swap x and y.
    @Test
    public void puzzler7two() {
        int x = 1984;
        int y = 2001;
        x = x ^ y;
        y = y ^ x;
        x = y ^ x;
        System.out.println("x=" + x + "; y=" + y);
    }

    /**
     * 由于i是一个变量 即使为它赋值了 编译期仍无法确定它的值 于是 false？i：x中无法确定i的值
     * 是否能用char表示 于是表达式的返回类型被定义为int
     * 如果把i 显式的定义为常量 final int i 那么第二条语句在编译期就知道int的值能否在不
     * 损失精度的前提下转化为char 于是整个表达式的类型为char
     * <p>
     * •  如果第二个和第三个操作数具有相同的类型， 那么它就是条件表达式的类
     * 型。换句话说，你可以通过绕过混合类型的计算来避免大麻烦。
     * •  如果一个操作数的类型是 T，T 表示 byte、short 或 char，而另一个操作
     * 数是一个 int 类型的常量表达式，它的值是可以用类型 T 表示的，那么条
     * 件表达式的类型就是 T。
     * •  否则，将对操作数类型运用二进制数字提升，而条件表达式的类型就是第
     * 二个和第三个操作数被提升之后的类型。
     */
    @Test
    public void puzzler8() {
        char x = 'X';
        int i = 0;
        /**
         * 在程序的两个条件表达式中，一个操作数的类型是
         char，另一个的类型是 int。在两个表达式中，int 操作数都是 0，它可以被表
         示成一个 char。然而，只有第一个表达式中的 int 操作数是常量（0），而第二
         个表达式中的 int 操作数是变量（i）。因此，第 2 点被应用到了第一个表达式
         上，它返回的类型是 char，而第 3 点被应用到了第二个表达式上，其返回的类
         型是对 int 和 char 运用了二进制数字提升之后的类型，即 int。
         */
        System.out.println(true ? x : 0);
        System.out.println(false ? 0 : x);
        System.out.println(false ? i : x);
    }

    /**
     * (复合赋值操作符包括 +=、-=、*=、/=、%=、<<=、>>=、>>>=、&=、^=
     * 和|=)Java 语言规范中讲到,复合赋值 E1 op= E2 等价于简单赋值 E1 =
     * (T)((E1)op(E2)),其中 T 是 E1 的类型,除非 E1 只被计算一次。
     */
    @Test
    public void puzzler9() {
        short x = 0;
        int i = 123456;
        // 包含了一个隐藏的转型!
        x += i;
        System.out.println(x);

        // not compile
        //x = x + i;
    }

    /**
     * 复合赋值操作符要求两个操作数都是原始类型的,例如 int,或包装了的原始类
     * 型,例如 Integer,但是有一个例外:如果在+=操作符左侧的操作数是 String
     * 类型的,那么它允许右侧的操作数是任意类型,在这种情况下,该操作符执行的
     * 是字符串连接操作。简单赋值操作符(=)允许其左侧的是对象引用类型,这就
     * 显得要宽松许多了:你可以使用它们来表示任何你想要表示的内容,只要表达式
     * 的右侧与左侧的变量是赋值兼容的即可
     * <p>
     * this puzzler works perfectly, no error as the book said.
     */
    @Test
    public void puzzler10() {
        Object x = "Buy";
        String i = "Effective Java!";
        String ii = "hello world!";

        x = x + i;
        System.out.println(x);

        x += i;
        System.out.println(x);

        ii += x;
        System.out.println(ii);
    }

    /**
     * 使用字符串连接操作符使用格外小心。+ 操作符当且仅当它的操作数中至
     * 少有一个是 String 类型时,才会执行字符串连接操作;否则,它执行的就是加
     * 法。如果要连接的没有一个数值是字符串类型的,那么你可以有几种选择:
     * 预置一个空字符串;
     * 将第一个数值用 String.valueOf 显式地转换成一个字符串;
     * 使用一个字符串缓冲区;
     * 或者如果你使用的 JDK 5.0,可以用 printf 方法。
     */
    @Test
    public void puzzler11() {
        //Ha
        System.out.println("H" + "a");

        //169
        System.out.println('H' + 'a');

        StringBuffer sb = new StringBuffer();
        sb.append('H');
        sb.append('a');
        System.out.println(sb);

        System.out.println("" + 'H' + 'a');

        System.out.println("2 + 2 = " + 2 + 2);

        System.out.printf("%c%c", 'H', 'a');
    }

    /**
     * 尽管 char 是一个整数类型,但是许多类库都对其进行了特殊处理,因为 char
     * 数值通常表示的是字符而不是整数。
     * 然而,字符串连接操作符在这些方法中没有被定义。该操作符被定义为先对它的
     * 两个操作数执行字符串转换,然后将产生的两个字符串连接到一起。对包括数组
     * 在内的对象引用的字符串转换定义如下[JLS 15.18.1.1]:
     * 如果引用为 null,它将被转换成字符串"null"。否则,该转换的执行就像是不
     * 用任何参数调用该引用对象的 toString 方法一样;但是如果调用 toString 方法
     * 的结果是 null,那么就用字符串"null"来代替。
     * <p>
     * char 数组不是字符串。要想将一个 char 数组转换成一个字符串,就要调
     * 用 String.valueOf(char[])方法。某些类库中的方法提供了对 char 数组的类似
     * 字符串的支持,通常是提供一个 Object 版本的重载方法和一个 char[]版本的重
     * 载方法,而之后后者才能产生我们想要的行为。
     */
    @Test
    public void puzzler12() {
        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        //ABC easy as [C@722c41f4
        System.out.println(letters + " easy as " + numbers);

        //ABC, easy as123
        System.out.println(letters + ", easy as" + String.valueOf(numbers));

        //ABC, easy as
        System.out.println(letters + ", easy as");
        //123
        System.out.println(numbers);

        Object numbers2 = new char[]{'1', '2', '3'};
        //ABC easy as [C@5b80350b
        System.out.print(letters + " easy as ");
        // use object, differ from char[]
        System.out.println(numbers2);
    }

    /**
     * String 类型的编译期常量是内存限定的。
     * 换句话说,任何两个 String
     * 类型的常量表达式,如果标明的是相同的字符序列,那么它们就用相同的对象引
     * 用来表示。如果用常量表达式来初始化 pig 和 dog,那么它们确实会指向相同的
     * 对象,但是 dog 并不是用常量表达式初始化的。
     */
    @Test
    public void puzzler13() {
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        // false
        System.out.println("Animals are equal: " + pig == dog);
        //+ 操作符,不论是用作加法还是字符串连接操作,它都比 == 操作符的优先级高。
        System.out.println(("Animals are equal: " + pig) == dog);

        //Animals are equal: false
        System.out.println("Animals are equal: " + (pig == dog));
    }

    /**
     * Java 对在字符串字面常量中的 Unicode 转义字
     * 符没有提供任何特殊处理。编译器在将程序解析成各种符号之前,先将 Unicode
     * 转义字符转换成为它们所表示的字符[JLS 3.2]。因此,程序中的第一个 Unicode
     * 转义字符将作为一个单字符字符串字面常量("a")的结束引号,而第二个
     * Unicode 转义字符将作为另一个单字符字符串字面常量("b")的开始引号。程
     * 序打印的是表达式"a".length()+"b".length(),即 2。
     */
    @Test
    public void puzzler14() {
        // \u0022 是双引号的 Unicode 转义字符
        System.out.println("a\u0022.length()+\u0022b".length());
    }

    /**
     * 该方法不能通过运行
     * Error:(361, 27) 错误: 非法的 Unicode 转义
     *
     * 问题在于注释的第三行,它包含了字符"\\units"。这些字符以反斜杠(\)以及紧
     跟着的字母 u 开头的,而它(\\u)表示的是一个 Unicode 转义字符的开始。遗憾
     的是,这些字符后面没有紧跟四个十六进制的数字,因此,这个 Unicode 转义字
     符是病构的,而编译器则被要求拒绝该程序。Unicode 转义字符必须是良构的,
     即使是出现在注释中也是如此。
     *
     * 代码（包括注释）不能出现单反斜杠+u，后面却不是4个16进制数。
     */
    /**
     * Generated by the IBM IDL-to-Java compiler, version 1.0
     * from F:\TestRoot\apps\a1\\units\include\PolicyHome.idl
     * Wednesday, June 17, 1998 6:44:40 o’clock AM GMT+00:00
     */
    @Test
    public void puzzler15() {
        System.out.println("hello world");
    }

    /**
     * 这个谜题的关键就是程序第三行的注释。与最好的注释一样,这条注释也是一种
     * 准确的表达,遗憾的是,它有一点准确得过头了。编译器不仅会在将程序解析成
     * 为符号之前把 Unicode 转义字符转换成它们所表示的字符(谜题 14),而且它
     * 是在丢弃注释和空格之前做这些事的[JLS 3.2]。这个程序包含了一个 Unicode 转移字符(\u000A),它位于程序唯一的注释行中。
     * 就像注释所陈述的,这个转义字符表示换行符,编译器将在丢弃注释之前适时地
     * 转换它。遗憾的是,这个换行符是表示注释开始的两个斜杠符之后的第一个行终
     * 结符(line terminator),因此它将终结该注释[JLS 3.4]。所以,该转义字符
     * 之后的字(is Unicode representation of linefeed (LF))就不是注释的一部
     * 分了,而它们在语法上也不是有效的。
     * <p>
     * Unicode 转义字符只有在你要向程序中插入用其他任何方式都
     * 无法表示的字符时才是必需的,除此之外的任何情况都不应该避免使用它们。
     * Unicode 转义字符降低了程序的清晰度,并且增加了产生 bug 的可能性。
     */
    @Test
    public void puzzler16() {
        // Note: \\u000A is Unicode representation of linefeed (LF)
        char c = 0x000A;
        System.out.println(c);
    }

    /**
     * 首先,byte 数组用从 0 到 255 每一个可能的 byte 数值进行了初始化,然后这些
     * byte 数值通过 String 构造器被转换成了 char 数值。最后,char 数值被转型为
     * int 数值并被打印。打印出来的数值肯定是非负整数,因为 char 数值是无符号
     * 的,因此,你可能期望该程序将按顺序打印出 0 到 255 的整数。
     * 如果你运行该程序,可能会看到这样的序列。但是在运行一次,可能看到的就不
     * 是这个序列了。我们在四台机器上运行它,会看到四个不同的序列,包括前面描
     * 述的那个序列。这个程序甚至都不能保证会正常终止,比打印其他任何特定字符
     * 串都要缺乏这种保证。它的行为完全是不确定的。
     * 这里的罪魁祸首就是 String(byte[])构造器。有关它的规范描述道:“在通过
     * 解码使用平台缺省字符集的指定 byte 数组来构造一个新的 String 时,该新
     * String 的长度是字符集的一个函数,因此,它可能不等于 byte 数组的长度。当
     * 给定的所有字节在缺省字符集中并非全部有效时,这个构造器的行为是不确定
     * 的”
     * <p>
     * 到底什么是字符集?从技术角度上讲,它是“被编码的字符集合和字符编码模式
     * 的结合物”[Java-API]。换句话说,字符集是一个包,包含了字符、表示字符的
     * 数字编码以及在字符编码序列和字节序列之间来回转换的方式。转换模式在字符
     * 集之间存在着很大的区别:某些是在字符和字节之间做一对一的映射,但是大多
     * 数都不是这样。ISO-8859-1 是唯一能够让该程序按顺序打印从 0 到 255 的整数
     * 的缺省字符集,它更为大家所熟知的名字是 Latin-1[ISO-8859-1]
     */
    @Test
    public void puzzler18() {
        byte[] bytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            bytes[i] = (byte) i;
        }

        String str = new String(bytes);
        for (int i = 0, n = str.length(); i < n; i++) {
            System.out.print((int) str.charAt(i) + " ");
        }

        String str2;
        try {
            str2 = new String(bytes, "ISO-8859-1");
            for (int i = 0, n = str2.length(); i < n; i++) {
                System.out.print((int) str.charAt(i) + " ");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用单行注释，块注释不能可靠地注释掉代码段
     */
    @Test
    public void puzzler19() {
        //code commented out with an if statement - doesn't always work!
        if (false) {
            /* Add the numbers from 1 to n */
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
        }
    }

    @Test
    public void puzzler20() {
        /*
         * Reports any calls to java.lang.String.replaceAll() with "." as the first argument.
         * Calling replaceAll(".", ...) replaces all of the characters in a string with its second argument,
         * which is rarely the desired functionality. More probably, replaceAll("\.", ...) was intended.

         ///////////////////.class
         */
        System.out.println(PuzzlerTest.class.getName().replaceAll(".", "/") + ".class");

        //com/test/shanemao/puzzler/PuzzlerTest.class
        /**
         * 要想只匹配句点符号，在正则表达式中的句点必须在其前面添加一个反斜杠（\）
         * 进行转义。 因为反斜杠字符在字面含义的字符串中具有特殊的含义——它标识转
         * 义字符序列的开始——因此反斜杠自身必须用另一个反斜杠来转义， 这样就可以
         * 产生一个转义字符序列，它可以在字面含义的字符串中生成一个反斜杠。
         *
         * 字符串中什么时候使用\\，什么时候使用\?
         * \b \t等，是有意义的转义字符时，使用一个\；
         * \"表示使用"的文本意义，不做字符串的开始和结束标记；
         * \\表示使用\的文本意义，不做转义字符的标识。
         * \标识转义，既可以和b、t等本身无特殊意义的字符连在一起，表示某一个特殊的含义，也可以将某一些特殊的含义，转变为其本身的文本含义。
         *
         */
        System.out.println(PuzzlerTest.class.getName().replaceAll("\\.", "/") + ".class");

        System.out.println("\\");
    }

    /**
     * 5.0 版本提供了新的静态方法
     * java.util.regex.Pattern.quote。它接受一个字符串作为参数，并可以添加必
     * 需的转义字符，它将返回一个正则表达式字符串，该字符串将精确匹配输入的字
     * 符串。
     */
    @Test
    public void puzzler20two() {
        // com/test/shanemao/puzzler/PuzzlerTest.class
        System.out.println(PuzzlerTest.class.getName().replaceAll(Pattern.quote("."), "/") + ".class");
    }

    @Test
    public void puzzler21() {
        /*
         * String.replaceAll 的第二个参数不是一个普通的字符串，而是一个
         替代字符串（replacement string），就像在 java.util.regex 规范中所定义的
         那样[Java-API]。在替代字符串中出现的反斜杠会把紧随其后的字符进行转义，
         从而导致其被按字面含义而处理了。
         当你在 Windows 上运行该程序时， 替代字符串是单独的一个反斜杠， 它是无效的。

         java.lang.IllegalArgumentException: character to be escaped is missing
         */
        System.out.println(PuzzlerTest.class.getName().replaceAll("\\.", File.separator) + ".class");
    }

    @Test
    public void puzzler21Two() {
        // com\test\shanemao\puzzler\PuzzlerTest.class
        // 第一个方法是 java.util.regex.Matcher.quoteReplacement，它将字符串转换成相应的替代字符串。
        System.out.println(PuzzlerTest.class.getName().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class");
        // com\test\shanemao\puzzler\PuzzlerTest.class
        /**
         * String.replace(CharSequence, CharSequence)，它做的事情和String.replaceAll 相同，
         * 但是它将模式和替代物都当作字面含义的字符串处理
         */
        System.out.println(PuzzlerTest.class.getName().replace(".", File.separator) + ".class");
    }


    /**
     * 在本谜题中所引用的“Java编程语言中很少被人了解的特性”
     * 实际上就是你可以在任何语句前面放置标号。
     * 这个程序标注了一个表达式语句，它是合法的，但是却没什么用处。
     * <p>
     * 执行结果iexplore::maximize
     */
    @Test
    public void puzzler22() {
        System.out.print("iexplore:");
        http:
//www.google.com
        System.out.println(":maximize");
    }

    /**
     * 栅栏柱错误（fencepost error）
     * 这个名字来源于对下面这个问题最常见的但却是错误的答案， 如果你要建造一个
     * 100 英尺长的栅栏，其栅栏柱间隔为 10 英尺，那么你需要多少根栅栏柱呢？11
     * 根或 9 根都是正确答案，这取决于是否要在栅栏的两端树立栅栏柱，但是 10 根
     * 却是错误的。要当心栅栏柱错误，每当你在处理长度、范围或模数的时候，都要
     * 仔细确定其端点是否应该被包括在内，并且要确保你的代码的行为要与其相对应
     * <p>
     * StringBuffer 有一个无参数的构造器，一个接受
     * 一个 String 作为字符串缓冲区初始内容的构造器，以及一个接受一个 int 作为
     * 缓冲区初始容量的构造器, 并没有char作为参数的构造器，因此，下方的代码的char会转化为int值
     * <p>
     * 始终打印ain
     */
    @Test
    public void puzzler23() {
        StringBuffer word = null;
        switch (rnd.nextInt(2)) {
            case 1:
                word = new StringBuffer('P');
                break;
            // 执行不到2， rnd.nextInt(2)的取值只有0,1
            case 2:
                word = new StringBuffer('G');
                break;
            default:
                word = new StringBuffer('M');
                break;
        }
        word.append('a');
        word.append('i');
        word.append('n');
        System.out.println(word);
    }

    @Test
    public void puzzler23two() {
        StringBuffer word = null;
        switch (rnd.nextInt(3)) {
            case 1:
                word = new StringBuffer("P");
                break;
            case 2:
                word = new StringBuffer("G");
                break;
            default:
                word = new StringBuffer("M");
                break;
        }
        word.append('a');
        word.append('i');
        word.append('n');
        System.out.println(word);
    }

    /**
     * 一个 char 不是一个 String，而是更像一个 int
     */
    @Test
    public void puzzler23three() {
        System.out.println("PGM".charAt(rnd.nextInt(3)) + "ain");
    }

    @Test
    public void puzzler23four() {
        String[] a = {"Main", "Pain", "Gain"};
        System.out.println(randomElement(a));
    }

    private String randomElement(String[] a) {
        return a[rnd.nextInt(a.length)];
    }

    /**
     * 简单地说,0x90 是一个 int 常量,它超出了 byte 数值的范围。这与直觉是相悖
     * 的,因为 0x90 是一个两位的十六进制字面常量,每一个十六进制位都占据 4 个
     * 比特的位置,所以整个数值也只占据 8 个比特,即 1 个 byte。问题在于 byte 是
     * 有符号类型。常量 0x90 是一个正的最高位被置位的 8 位 int 数值。合法的 byte
     * 数值是从-128 到+127,但是 int 常量 0x90 等于+144。
     * <p>
     * 请使用声明的常量替代“魔幻数字”。你已经了解了这确实是
     * 一个好主意:它说明了常量的含义,集中了常量的定义,并且根除了重复的定义。
     * 现在你知道它还可以强制你去为每一个常量赋予适合其用途的类型,从而消除了
     * 产生混合类型比较的一种根源。
     */
    @Test
    public void puzzler24() {
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90) {
                System.out.println("Joy!");
            }

            if (b == (byte) 0x90) {
                System.out.println("Joy!! b = " + b);
            }
            if ((b & 0xff) == 0x90) {
                System.out.println("Joy!!! b = " + Integer.toBinaryString(b));
            }
        }
    }

    @Test
    public void testHex() {
        int i = 0xA6;
        // 166
        System.out.println(i);
        // a6
        System.out.println(Integer.toHexString(i));
        // 10100110
        System.out.println(Integer.toBinaryString(i));
        // 246
        System.out.println(Integer.toOctalString(i));

        int ii = -90;
        // ffffffa6
        System.out.println(Integer.toHexString(ii));
        // 11111111111111111111111110100110
        System.out.println(Integer.toBinaryString(ii));
        // 37777777646
        System.out.println(Integer.toOctalString(ii));

        // -112
        System.out.println((byte) 0x90);
        // 11111111111111111111111110010000
        System.out.println(Integer.toBinaryString((byte) 0x90));
        // 10010000
        System.out.println(Integer.toBinaryString(0x90));
        // 144
        System.out.println(0x90);
    }

    @Test
    public void calSize() {
        //4
        System.out.println("Integer: " + Integer.SIZE / 8);
        //2
        System.out.println("Short: " + Short.SIZE / 8);
        // 8
        System.out.println("Long: " + Long.SIZE / 8);
        // 1
        System.out.println("Byte: " + Byte.SIZE / 8);
        // 2
        System.out.println("Character: " + Character.SIZE / 8);
        // 4
        System.out.println("Float: " + Float.SIZE / 8);
        // 8
        System.out.println("Double: " + Double.SIZE / 8);
        System.out.println("Boolean: " + Boolean.toString(false));

    }

    /**
     * 当++操作符被置于一个变量值之后时,其作用就是一个后缀增量操作符(postfix
     * increment operator)[JLS 15.14.2]:表达式 j++的值等于 j 在执行增量操作
     * 之前的初始值。因此,前面提到的赋值语句首先保存 j 的值,然后将 j 设置为其
     * 值加 1,最后将 j 复位到它的初始值。换句话说,这个赋值操作等价于下面的语
     * 句序列:
     * int tmp = j;
     * j = j + 1;
     * j = tmp;
     * 程序重复该过程 100 次,之后 j 的值还是等于它在循环开始之前的值,即 0。
     */
    @Test
    public void puzzler25() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        // 0
        System.out.println(j);

        for (int i = 0; i < 100; i++) {
            j = j--;
        }
        // 0
        System.out.println(j);

        for (int i = 0; i < 100; i++) {
            j = ++j;
        }
        // 100
        System.out.println(j);

        for (int i = 0; i < 50; i++) {
            j = --j;
        }
        // 50
        System.out.println(j);
    }

    /**
     * 数据溢出， Integer.MAX_VALUE + 1 == Integer.MIN_VALUE
     * <p>
     * 考虑使用long替代int。
     */
    @Test
    public void puzzler26() {
        System.out.println(Integer.MAX_VALUE + 1);

        // always true
        System.out.println(Integer.MAX_VALUE + 1 == Integer.MIN_VALUE);

        // always true
        System.out.println(Integer.MIN_VALUE - 1 == Integer.MAX_VALUE);
    }

    /**
     * infinite loop
     * <p>
     * 问题在于(-1 << 32)等于-1 而不是 0,因为移位操作符之使用其右操作数的低
     * 5 位作为移位长度。或者是低 6 位,如果其左操作数是一个 long 类数值[JLS
     * 15.19]。
     * 这条规则作用于全部的三个移位操作符:<<、>>和>>>。移位长度总是介于 0 到
     * 31 之间,如果左操作数是 long 类型的,则介于 0 到 63 之间。这个长度是对 32
     * 取余的,如果左操作数是 long 类型的,则对 64 取余。如果试图对一个 int 数值
     * 移位 32 位,或者是对一个 long 数值移位 64 位,都只能返回这个数值自身的值。
     * 没有任何移位长度可以让一个 int 数值丢弃其所有的 32 位,或者是让一个 long
     * 数值丢弃其所有的 64 位。
     */
    @Test
    public void puzzler27() {
        int i = 0;
        while (-1 << i != 0) {
            i++;
        }
        System.out.println(i);
    }

    /**
     * 右移操作符总是起到右移的作用,而左移操作符也总是起到左移的
     * 作用。负的移位长度通过只保留低 5 位而剔除其他位的方式被转换成了正的移位
     * 长度——如果左操作数是 long 类型的,则保留低 6 位。因此,如果要将一个 int
     * 数值左移,其移位长度为-1,那么移位的效果是它被左移了 31 位。
     */
    @Test
    public void puzzler27two() {
        int distance = 0;
        for (int val = -1; val != 0; val <<= 1) {
            distance++;
        }
        System.out.println(distance);
    }

    @Test
    public void puzzler28() {
        // infinite loop
        int start = Integer.MAX_VALUE;
        for (int i = start; i <= start + 1; i++) {

        }
        System.out.println("infinite loop, you can't see this statement");
    }

    /**
     * 事实上,你不必将 i 初始化为无穷大以确保循环永远执行。任何足够大的浮点数
     * 都可以实现这一目的,例如:
     * double i = 1.0e40;
     * 这样做之所以可以起作用,是因为一个浮点数值越大,它和其后继数值之间的间
     * 隔就越大。浮点数的这种分布是用固定数量的有效位来表示它们的必然结果。对
     * 一个足够大的浮点数加 1 不会改变它的值,因为 1 是不足以“填补它与其后继者
     * 之间的空隙”。
     * 浮点数操作返回的是最接近其精确的数学结果的浮点数值。一旦毗邻的浮点数值
     * 之间的距离大于 2,那么对其中的一个浮点数值加 1 将不会产生任何效果,因为
     * 其结果没有达到两个数值之间的一半。对于 float 类型,加 1 不会产生任何效果
     * 的最小级数是 2^25 ,即 33,554,432;而对于 double 类型,最小级数是 2^54,大约
     * 是 1.8 × 10^16 。
     * 毗邻的浮点数值之间的距离被称为一个 ulp,它是“最小单位(unit in the last
     * place)”的首字母缩写词。在 5.0 版中,引入了 Math.ulp 方法来计算 float
     * 或 double 数值的 ulp。
     */
    @Test
    public void puzzler28two() {
        double i = 1.0 / 0.0;
        while (i == i + 1) {
            System.out.println("hello world");
        }

        double j = Double.POSITIVE_INFINITY;
        while (j == j + 1) {
            System.out.println("hello world");
        }

        double k = 1.0e40;
        while (k == k + 1) {
            System.out.println("hello world");
        }
    }

    /**
     * 毗邻的浮点数值之间的距离被称为一个 ulp,它是“最小单位(unit in the last
     * place)”的首字母缩写词。在 5.0 版中,引入了 Math.ulp 方法来计算 float
     * 或 double 数值的 ulp。
     */
    @Test
    public void puzzler28three() {
        System.out.println(Math.ulp(Math.pow(2, 54)));
        System.out.println(Math.pow(2, 54));
    }

    /**
     * IEEE 754 浮点算术保留了一个特殊的值用来表示一个不是数字的数量
     * [IEEE 754]。这个值就是 NaN(“不是一个数字(Not a Number)”的缩写),
     * 对于所有没有良好的数字定义的浮点计算,例如 0.0/0.0,其值都是它。规范中
     * 描述道,NaN 不等于任何浮点数值,包括它自身在内[JLS 15.21.1]。因此,如
     * 果 i 在循环开始之前被初始化为 NaN,那么终止条件测试(i != i)的计算结果就
     * 是 true,循环就永远不会终止。很奇怪但却是事实。
     */
    @Test
    public void puzzler29() {
        // NaN 还有其他的惊人之处。任何浮点操作,只要它的一个或多个操作数为 NaN,那么其结果为 NaN。
        // 一旦一个计算产生了 NaN,它就被损坏了,没有任何更进一步的计算可以修复这样的损坏
        double i = 0.0 / 0.0;

        // false
        System.out.println(i == i);

        double k = Double.NaN;
        // true
        System.out.println(k != k);

        System.out.println(k - k + 100);

    }

    @Test
    public void puzzler30() {
        // 将i设置成String类型
        String i = "hello world";

        // i不使用浮点数
        while (i != i + 0) {
        }
    }

    /**
     * 解决本谜题的关键在于>>>=是一个复
     * 合赋值操作符。(复合赋值操作符包括*=、/=、%=、+=、-=、<<=、>>=、>>>=、
     * &=、^=和|=。)有关混合操作符的一个不幸的事实是,它们可能会自动地执行窄
     * 化原始类型转换[JLS 15.26.2],这种转换把一种数字类型转换成了另一种更缺
     * 乏表示能力的类型。窄化原始类型转换可能会丢失级数的信息,或者是数值的精度
     * <p>
     * 让我们更具体一些,假设你在循环的前面放置了下面的声明:
     * short i = -1;
     * 因为 i 的初始值((short)0xffff)是非 0 的,所以循环体会被执行。在执行移
     * 位操作时,第一步是将 i 提升为 int 类型。所有算数操作都会对 short、byte
     * 和 char 类型的操作数执行这样的提升。这种提升是一个拓宽原始类型转换,因
     * 此没有任何信息会丢失。这种提升执行的是符号扩展,因此所产生的 int 数值是
     * 0xffffffff。然后,这个数值右移 1 位,但不使用符号扩展,因此产生了 int
     * 数值 0x7fffffff。最后,这个数值被存回到 i 中。为了将 int 数值存入 short
     * 变量,Java 执行的是可怕的窄化原始类型转换,它直接将高 16 位截掉。这样就
     * 只剩下(short)oxffff 了,我们又回到了开始处。循环的第二次以及后续的迭代
     * 行为都是一样的,因此循环将永远不会终止。
     * 如果你将 i 声明为一个 short 或 byte 变量,并且初始化为任何负数,那么这种
     * 行为也会发生。如果你声明 i 为一个 char,那么你将无法得到无限循环,因为
     * char 是无符号的,所以发生在移位之前的拓宽原始类型转换不会执行符号扩展。
     */
    @Test
    public void puzzler31() {
//        long i = -1;
        // infinite loop
        short i = -1;
        while (i != 0) {
            i >>>= 1;
            System.out.println(i);
        }
    }

    /**
     * infinite loop
     */
    @Test
    public void puzzler32() {
        //请提供一个对 i 的声明,将下面的循环转变为一个无限循环:
        Integer i = new Integer(0);
        Integer j = new Integer(0);
        //当两个操作数都是被包装的数字类型时,数值比较操作符和判等操作符的
        //行为存在着根本的差异:数值比较操作符执行的是值比较,而判等操作符执行的
        //是引用标识的比较。
        while (i <= j && j <= i && i != j) {

        }

        //true
        System.out.println(new Integer(0) == 0);
    }

    /**
     * Java 使用 2 的补码的算术运算,它是非对称的。对于每一种有符号的整
     * 数类型(int、long、byte 和 short),负的数值总是比正的数值多一个,这个
     * 多出来的值总是这种类型所能表示的最小数值。对 Integer.MIN_VALUE 取负值得
     * 到的还是它没有改变过的值,Long.MIN_VALUE 也是如此。对 Short.MIN_VALUE
     * 取负值并将所产生的 int 数值转型回 short,返回的同样是最初的值
     * (Short.MIN_VALUE)。对 Byte.MIN_VALUE 来说,也会产生相似的结果。更一般
     * 地讲,千万要当心溢出:就像狼人一样,它是个杀手。
     */
    @Test
    public void puzzler33() {
        int i = Integer.MIN_VALUE;
        while (i != 0 && i == -i) {

        }

        long ii = Long.MIN_VALUE;
        while (ii != 0 && ii == -ii) {

        }
    }

    /**
     * 循环变量是 float 类型的,而非 int 类型的。
     * 回想一下谜题 28,很明显,增量操作(f++)不能正常工作。F 的初始值接近于
     * Integer.MAX_VALUE,因此它需要用 31 位来精确表示,而 float 类型只能提供
     * 24 位的精度。对如此巨大的一个 float 数值进行增量操作将不会改变其值。因
     * 此,这个程序看起来应该无限地循环下去,因为 f 永远也不可能解决其终止值。
     * 但是,如果你运行该程序,就会发现它并没有无限循环下去,事实上,它立即就
     * 终止了,并打印出 0。怎么回事呢?
     * 问题在于终止条件测试失败了,其方式与增量操作失败的方式非常相似。这个循
     * 环只有在循环索引 f 比(float)(START + 50)小的情况下才运行。在将一个 int
     * 与一个 float 进行比较时,会自动执行从 int 到 float 的提升[JLS 15.20.1]。
     * 遗憾的是,这种提升是会导致精度丢失的三种拓宽原始类型转换的一种[JLS
     * 5.1.2]。(另外两个是从 long 到 float 和从 long 到 double。)
     * f 的初始值太大了,以至于在对其加上 50,然后将结果转型为 float 时,所产生
     * 的数值等于直接将 f 转换成 float 的数值。换句话说,(float)2000000000 ==
     * 2000000050,因此表达式 f < START + 50 即使是在循环体第一次执行之前就是
     * false,所以,循环体也就永远的不到机会去运行。
     */
    @Test
    public void puzzler34() {
        final int START = 2000000000;
        int count = 0;
        for (float f = START; f < START + 50; f++) {
            count++;
        }
        // 0
        System.out.println(count);
    }


    /**
     * omit
     * 取余和乘法操作符具有相同的优先级[JLS 15.17]
     */
    @Test
    public void puzzler35() {
    }

    @Test
    public void puzzler36() {
        // false
        System.out.println(decision());
        // true
        System.out.println(decision1());
    }

    /**
     * 在一个 try-finally 语句中,finally 语句块总是在控制权离开 try 语
     * 句块时执行的[JLS 14.20.2]。无论 try 语句块是正常结束的,还是意外结束的,
     * 情况都是如此。一条语句或一个语句块在它抛出了一个异常,或者对某个封闭型
     * 语句执行了一个 break 或 continue,或是象这个程序一样在方法中执行了一个
     * return 时,将发生意外结束。它们之所以被称为意外结束,是因为它们阻止程
     * 序去按顺序执行下面的语句。
     * <p>
     * 当 try 语句块和 finally 语句块都意外结束时,在 try 语句块中引发意外结束的
     * 原因将被丢弃,而整个 try-finally 语句意外结束的原因将于 finally 语句块意
     * 外结束的原因相同。在这个程序中,在 try 语句块中的 return 语句所引发的意
     * 外结束将被丢弃,而 try-finally 语句意外结束是由 finally 语句块中的 return
     * 造成的。简单地讲,程序尝试着(try)返回(return)true,但是它最终(finally)
     * 返回(return)的是 false。
     * <p>
     * 总之,每一个 finally 语句块都应该正常结束,除非抛出的是不受检查的异常。
     * 千万不要用一个 return、break、continue 或 throw 来退出一个 finally 语句块,
     * 并且千万不要允许将一个受检查的异常传播到一个 finally 语句块之外去。
     *
     * @return
     */
    private boolean decision() {
        try {
            return true;
        } finally {
            return false;
        }
    }

    private boolean decision1() {
        try {
            return true;
        } finally {
            // better not use break or continue in finally
            for (int i = 0; i < 5; i++) {
                if (i == 1) {
                    break;
                }
            }
        }
    }

    /**
     * 这个程序不能编译,因为 println 方法没有声明会抛出任何被检查异常,而
     * IOException 却正是一个被检查异常。语言规范中描述道:如果一个 catch 子句
     * 要捕获一个类型为 E 的被检查异常,而其相对应的 try 子句不能抛出 E 的某种子
     * 类型的异常,那么这就是一个编译期错误[JLS 11.2.3]
     */
    @Test
    public void puzzler37() {
//        try {
//            System.out.println("Hello world");
//        } catch (IOException e) {
//            System.out.println("I've never seen println fail!");
//        }
    }

    /**
     * 它之所以可以编译,是因为它唯一的 catch 子句检查了 Exception。尽
     * 管 JLS 在这一点上十分含混不清,但是捕获 Exception 或 Throwble 的 catch 子
     * 句是合法的,不管与其相对应的 try 子句的内容为何。尽管 Arcane2 是一个合法
     * 的程序,但是 catch 子句的内容永远的不会被执行,这个程序什么都不会打印。
     */
    @Test
    public void puzzler37two() {
        try {
            // If you have nothing nice to say, say nothing
        } catch (Exception e) {
            System.out.println("This can't happen");
        }
    }

    interface Type1 {
        void f() throws CloneNotSupportedException;
    }

    interface Type2 {
        void f() throws InterruptedException;
    }

    interface Type3 extends Type1, Type2 {
    }

    public class Arcane3 implements Type3 {
        @Override
        public void f() {
            System.out.println("Hello world");
        }
    }

    /**
     * 每一个接口都限制了方
     * 法 f 可以抛出的被检查异常集合。一个方法可以抛出的被检查异常集合是它所适
     * 用的所有类型声明要抛出的被检查异常集合的交集,而不是合集。因此,静态类
     * 型为 Type3 的对象上的 f 方法根本就不能抛出任何被检查异常。因此,Arcane3
     * 可以毫无错误地通过编译,并且打印 Hello world。
     */
    @Test
    public void puzzler37three() {
        Type3 t3 = new Arcane3();
        t3.f();
    }

    /**
     * 一个空 final 域只有在它是明确未赋过值的地方才可
     * 以被赋值。规范长篇大论,对此术语提供了一个准确的但保守的定义[JLS 16]。
     * 因为它是保守的,所以编译器必须拒绝某些可以证明是安全的程序。
     *
     * @see UnwelcomeGuest
     */
    @Test
    public void puzzler38() {

    }

    /**
     * 不论 try 语句块的执行是正常地还是意外地结束,
     * finally 语句块确实都会执行。
     * 然而在这个程序中,try 语句块根本就没有结束其执行过程。System.exit 方法
     * 将停止当前线程和所有其他当场死亡的线程。finally 子句的出现并不能给予线
     * 程继续去执行的特殊权限。
     */
    @Test
    public void puzzler39() {
        try {
            System.out.println("Hello world");
            System.exit(0);
        } finally {
            // not executed
            System.out.println("Goodbye world");
        }
    }

    /**
     * 当 System.exit 被调用时,虚拟机在关闭前要执行两项清理工作。首先,它执行
     * 所有的关闭挂钩操作,这些挂钩已经注册到了 Runtime.addShutdownHook 上。这
     * 对于释放 VM 之外的资源将很有帮助。务必要为那些必须在 VM 退出之前发生的行
     * 为关闭挂钩。
     * <p>
     * VM 执行在 System.exit 被调用时执行的第二个清理任务与终结器有关。如果
     * System.runFinalizerOnExit 或它的魔鬼双胞胎 Runtime.runFinalizersOnExit
     * 被调用了,那么 VM 将在所有还未终结的对象上面调用终结器。这些方法很久以
     * 前就已经过时了,而且其原因也很合理。无论什么原因,永远不要调用
     * System.runFinalizersOnExit 和 Runtime.runFinalizersOnExit:
     * 它们属于 Java
     * 类库中最危险的方法之一[ThreadStop]。调用这些方法导致的结果是,终结器会
     * 在那些其他线程正在并发操作的对象上面运行,从而导致不确定的行为或导致死
     * 锁。
     * 总之,System.exit 将立即停止所有的程序线程,它并不会使 finally 语句块得
     * 到调用,但是它在停止 VM 之前会执行关闭挂钩操作。当 VM 被关闭时,请使用关
     * 闭挂钩来终止外部资源。通过调用 System.halt 可以在不执行关闭挂钩的情况下
     * 停止 VM,但是这个方法很少使用。
     */
    @Test
    public void puzzler39two() {
        System.out.println("Hello world");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Goodbye world")));
        System.exit(0);
    }

    /**
     * @see Reluctant
     */
    @Test
    public void puzzler40() {
    }

    /**
     * 问题在 finally 语句块自身中。close 方法也可能会抛出 IOException 异常。如
     * 果这正好发生在 in.close 被调用之时，那么这个异常就会阻止 out.close 被调
     * 用，从而使输出流仍保持在开放状态。
     * <p>
     * 请注意，该程序违反了谜题 36 的建议：对 close 的调用可能会导致 finally 语
     * 句块意外结束。遗憾的是，编译器并不能帮助你发现此问题，因为 close 方法抛
     * 出的异常与 read 和 write 抛出的异常类型相同，而其外围方法（copy）声明将
     * 传播该异常。
     */
    @Test
    public void puzzler41() throws IOException {
        String src = "D:\\hello";
        String dest = "D:\\world";
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            int n;
            byte[] buf = new byte[1024];
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
        } finally {
            if (in != null) {
                in.close();
            }

            if (out != null) {
                out.close();
            }
        }
    }

    @Test
    public void puzzler41two() throws IOException {
        String src = "D:\\hello";
        String dest = "D:\\world";
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            int n;
            byte[] buf = new byte[1024];
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
        } finally {
            // type one
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // There is nothing we can do if close fails.
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // There is nothing we can do if close fails.
                }
            }
            // type two
            closeIgnoringException(in);
            closeIgnoringException(out);
        }
    }

    /**
     * 总之，当你在 finally 语句块中调用 close 方法时，要用一个嵌套的 try-catch
     * 语句来保护它，以防止 IOException 的传播。更一般地讲，对于任何在 finally
     * 语句块中可能会抛出的被检查异常都要进行处理，而不是任其传播。这是谜题
     * 36 中的教训的一种特例，而对语言设计着的教训情况也相同。
     */
    private void closeIgnoringException(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // There is nothing we can do if close fails.
            }
        }
    }

    /**
     * & (或|)操作符有其他的含义。除了常见的被当作整型操作数的位 AND 操作
     * 符之外，当被用于布尔操作数时，它的功能被重载为逻辑 AND 操作符[JLS
     * 15.22.2]。这个操作符与更经常被使用的条件 AND 操作符有所不同，& 操作符总
     * 是要计算它的两个操作数，而 && 操作符在其左边的操作数被计算为 false 时，
     * 就不再计算右边的操作数了[JLS 15.23]。
     */
    @Test
    public void puzzler42() {
        // 注意 & 和 &&， | 和 ||的区别
    }

    /**
     * @see Thrower
     * @see TigerThrower
     */
    @Test
    public void puzzler43() {
        sneakyThrow(new NullPointerException());
    }

    /**
     * 这个讨厌的小方法所做的事情正是 throw 语句要做的事情,但是它绕过了编译器
     * 的所有异常检查操作。你可以(卑鄙地)在你的代码的任意一点上抛出任何受检
     * 查的或不受检查的异常,而编译器对此连眉头都不会皱一下。
     * <p>
     * 不要这样使用！
     */
    public static void sneakyThrow(Throwable t) {
        Thread.currentThread().stop(t);
    }

    /**
     * @see Strange
     * not the same as the book written.
     */
    @Test
    public void puzzler44() {

    }


    /**
     * 一个非常有意思的二叉树式的调用
     */
    @Test
    public void puzzler45() {
        workHard();
        System.out.println("It's a nap time");
    }

    /**
     * Java 虚拟机对栈的深度限制到了某个预设的水平。当超过这个水平时，VM 就抛
     * 出 StackOverflowError。为了让我们能够更方便地考虑程序的行为，我们假设
     * 栈的深度为 3，这比它实际的深度要小得多。现在让我们来跟踪其执行过程。
     * main 方法调用 workHard，而它又从其 try 语句块中递归地调用了自己，然后它
     * 再一次从其 try 语句块中调用了自己。在此时，栈的深度是 3。当 workHard 方
     * 法试图从其 try 语句块中再次调用自己时，该调用立即就会以
     * StackOverflowError 而失败。这个错误是在最内部的 finally 语句块中被捕获
     * 的，在此处栈的深度已经达到了 3。在那里，workHard 方法试图递归地调用它自
     * 己，但是该调用却以 StackOverflowError 而失败。这个错误将在上一级的
     * finally 语句块中被捕获，在此处站的深度是 2。该 finally 中的调用将与相对
     * 应的 try 语句块具有相同的行为：最终都会产生一个 StackOverflowError。这
     * 似乎形成了一种模式，而事实也确实如此。
     * <p>
     * 从技术角度讲，调用图是一棵完全二叉树，它的深度就是 VM 的栈深度的上限。
     * WorkOut 程序的执行过程等于是在先序遍历这棵树。在先序遍历中，程序先访问
     * 一个节点，然后递归地访问它的左子树和右子树。对于树中的每一条边，都会产
     * 生一个调用，而对于树中的每一个节点，都会抛出一个异常。
     */
    private void workHard() {
        try {
            workHard();
        } finally {
            workHard();
        }
    }

    /**
     * @see Confusing
     */
    @Test
    public void puzzler46() {

    }

    /**
     * 在设计一个类的时候， 如果该类构建于另一个类的行为之上， 那么你有两种选择：
     * 一种是继承，即一个类扩展另一个类；另一种是组合，即在一个类中包含另一个
     * 类的一个实例。选择的依据是，一个类的每一个实例都是另一个类的一个实例，
     * 还是都有另一个类的一个实例。在第一种情况应该使用继承，而第二种情况应该
     * 使用组合。当你拿不准时，优选组合而不是继承
     * <p>
     * 静态域由声明它的类及其所有子类所共享。如果你需要让每一个子类都具
     * 有某个域的单独拷贝，那么你必须在每一个子类中声明一个单独的静态域。如果
     * 每一个实例都需要一个单独的拷贝，那么你可以在基类中声明一个非静态域。还
     * 有就是，要优选组合而不是继承，除非导出类真的需要被当作是某一种基类来看
     * 待。
     */
    @Test
    public void puzzler47() {

    }

    private static class Dog {
        public static void bark() {
            System.out.print("woof ");
        }
    }

    private static class Basenji extends Dog {
        public static void bark() {
        }
    }

    /**
     * 对静态方法的调用不存在任何动态的分派机
     * 制[JLS 15.12.4.4]。当一个程序调用了一个静态方法时，要被调用的方法都是
     * 在编译时刻被选定的，而这种选定是基于修饰符的编译期类型而做出的，修饰符
     * 的编译期类型就是我们给出的方法调用表达式中圆点左边部分的名字。
     * <p>
     * 静态方法是不能被覆写的；它们只能被隐藏
     */
    @Test
    public void puzzler48() {
        Dog woofer = new Dog();
        Dog nipper = new Basenji();
        woofer.bark();
        nipper.bark();
    }

    /**
     * @see Elvis
     */
    @Test
    public void puzzler49() {
    }

    @Test
    public void puzzler50() {
        String s = null;
        //尽管 null 对于每一个引用类型来说都是其子类型， 但是 instanceof 操作符被定
        //义为在其左操作数为 null 时返回 false。
        System.out.println(s instanceof String);

        // not compile
        // 该程序编译失败是因为 instanceof 操作符有这样的要求：如果两个操作数的类
        //型都是类，其中一个必须是另一个的子类型
        /*System.out.println(new PuzzlerTest() instanceof String);*/

        /*
         * 转型操作符的行为。与 instanceof 操作相同，如果在一个转型操作中的两
         种类型都是类，那么其中一个必须是另一个的子类型。尽管对我们来说，这个转
         型很显然会失败，但是类型系统还没有强大到能够洞悉表达式 new Object()的
         运行期类型不可能是 Type3 的一个子类型。因此，该程序将在运行期抛出
         ClassCastException 异常。
         要是写个new String() newLinkedList() 会在编译期就会发现
         */
        PuzzlerTest t = (PuzzlerTest) new Object();
    }

    /**
     * @see ColorPoint
     */
    @Test
    public void puzzler51() {

    }

    /**
     * @see Client
     */
    @Test
    public void puzzler52() {

    }

    /**
     * @see MyThing
     */
    @Test
    public void puzzler53() {

    }

    /**
     * 不仅表达式的值所引用的对象的运行期类型在确定哪一个方法将被
     * 调用时并不起任何作用，but also the identity of the object, if any, plays no role。在
     * 本例中，没有任何对象，但是这并不会造成任何区别。静态方法调用的限定表达
     * 式是可以计算的，但是它的值将被忽略。没有任何要求其值为非空的限制。
     */
    @Test
    public void puzzler54() {
        ((PuzzlerTest) null).greet();
    }

    public static void greet() {
        System.out.println("Hello world!");
    }

    /**
     * @see Creator
     */
    @Test
    public void puzzler55() {
    }

    /**
     * BigInteger 实例是不可变的。String、
     * BigDecimal 以及包装器类型： Integer、 Long、 Short、 Byte、 Character、 Boolean、
     * Float 和 Double 也是如此，你不能修改它们的值。我们不能修改现有实例的值，
     * 对这些类型的操作将返回新的实例。
     */
    @Test
    public void puzzler56() {
        BigInteger fiveThousand = new BigInteger("5000");
        BigInteger fiftyThousand = new BigInteger("50000");
        BigInteger fiveHundredThousand = new BigInteger("500000");
        BigInteger total = BigInteger.ZERO;
        total.add(fiveThousand);
        total.add(fiftyThousand);
        total.add(fiveHundredThousand);
        // 0
        System.out.println(total);
    }

    @Test
    public void puzzler56two() {
        BigInteger fiveThousand = new BigInteger("5000");
        BigInteger fiftyThousand = new BigInteger("50000");
        BigInteger fiveHundredThousand = new BigInteger("500000");
        BigInteger total = BigInteger.ZERO;
        total = total.add(fiveThousand);
        total = total.add(fiftyThousand);
        total = total.add(fiveHundredThousand);
        System.out.println(total);
    }

    /**
     * @see Name
     * 当你覆写 equals 方法时，一定要记着覆写 hashCode 方法。更一般地讲，
     * 当你在覆写一个方法时，如果它具有一个通用的约定，那么你一定要遵守它。对
     * 于大多数在Object中声明的非final的方法， 都需要注意这一点[EJ Chapter 3]。
     * 不采用这项建议就会导致任意的、不确定的行为。
     */
    @Test
    public void puzzler57() {
    }

    /**
     * @see Name
     */
    @Test
    public void puzzler58() {
    }

    /**
     * 千万不要在一个整型字面常量的前面加上一个 0；这会使
     * 它变成一个八进制字面常量。
     */
    @Test
    public void puzzler59() {
        System.out.println(012);
    }

    /**
     * A．编写一个方法，它接受一个包含元素的 List，并返回一个新的 List，
     * 它以相同的顺序包含相同的元素， 只不过它把第二次以及后续出现的重复
     * 元素都剔除了。例如，如果你传递了一个包
     * 含”spam”,”sausage”,”spam”,”spam”,”bacon”,”spam”,”tomato”和”spam”的列表，那么你将得到一个包
     * 含”spam”,”sausage”,”bacon”,”tomato”的新列表。
     */
    @Test
    public void puzzler60() {
        List<String> list = new ArrayList<>();
        list.add("spam");
        list.add("sausage");
        list.add("spam");
        list.add("spam");
        list.add("bacon");
        list.add("spam");
        list.add("tomato");
        list.add("spam");
        System.out.println(list);

        /*
         * 有一种 Set 的实现可以维护其元素被插入的顺序，它提供
         的导入性能接近 HashMap。它就是 LinkedHashSet，它是在 1.4 版本的 JDK 中被
         添加到 Java 平台中的。在内部，它是用一个链接列表来处理的，从而被实现为
         一个散列表。
         */
        List<String> newList = new LinkedList<>(new LinkedHashSet<>(list));
        System.out.println(newList);

    }

    /**
     * 编写一个方法，它接受一个由 0 个或多个由逗号分隔的标志所组成的
     * 字符串，并返回一个表示这些标志的字符串数组，数组中的元素的顺序与
     * 这些标志在输入字符串中出现的顺序相同。 每一个逗号后面都可能会跟随
     * 0 个或多个空格字符，这个方法忽略它们。例如，如果你传递的字符串
     * 是”fear, surprise, ruthless efficiency, an almost fanatical
     * devotion to the Pope, nice red uniforms”，那么你得到的将是一个
     * 包含 5 个元素的字符串数组，这些元素
     * 是”fear”，”surprise”，”ruthless efficiency”，”an almost
     * fanatical devotion to the Pope” 和 “nice red uniform”。
     */
    @Test
    public void puzzler60Two() {
        String string = "fear, surprise, ruthless efficiency," +
                " an almost fanatical devotion to the Pope, nice red uniforms";
        String[] split = string.split(",\\s*");
        for (String s : split) {
            System.out.println(s);
        }
    }

    /**
     * 假设你有一个多维数组，出于调试的目的，你想打印它。你不知道这
     * 个数组有多少级，以及在数组的每一级中所存储的对象的类型。编写一个
     * 方法，它可以向你显示出在每一级上的所有元素。
     * <p>
     * 这是一个讲究技巧的问题。你甚至不必去编写一个方法。这个方法在 5.0 或
     * 之后的版本中已经提供了，它就是 Arrays.deepToString。如果你传递给它一个
     * 对象引用的数组，它将返回一个精密的字符串表示。它可以处理嵌套数组，甚至
     * 可以处理循环引用，即一个数组元素直接或间接地引用了其嵌套外层的数组。事
     * 实上，5.0 版本中的 Arrays 类提供了一整套的 toString、equals 和 hashCode
     * 方法，使你能够打印、比较或散列任何原始类型数组或对象引用数组的内容。
     */
    @Test
    public void puzzler60Three() {
        String string = "fear, surprise,          ruthless efficiency," +
                "    an almost fanatical devotion to the Pope,     nice red uniforms";
        String[] split = string.split(",\\s*");
        System.out.println(Arrays.deepToString(split));
    }

    /**
     * 编写一个方法，它接受两个 int 数值，并在第一个数值与第二个数值
     * 以二进制补码形式进行比较，具有更多的位被置位时，返回 true
     * <p>
     * 整数类型的包装器类（Integer、Long、Short、Byte 和
     * Char）现在支持通用的位处理操作，包括 highestOneBit、lowestOneBit、
     * numberOfLeadingZeros、numberOfTrailingZeros、bitCount、rotateLeft、
     * rotateRight、reverse、signum 和 reverseBytes。
     */
    @Test
    public void puzzler60Four() {
        int i = 234;
        int j = 152;
        // 11101010
        System.out.println(Integer.toBinaryString(i));
        // 5
        System.out.println(Integer.bitCount(i));
        // 10011000
        System.out.println(Integer.toBinaryString(j));
        // 3
        System.out.println(Integer.bitCount(j));

        // 取 i 这个数的二进制形式最左边的最高1位且高位后面全部补零，最后返回int型的结果
        // 128
        System.out.println(Integer.highestOneBit(i));
        // 取 i 这个数的二进制形式最右边的最低1位且高位后面全部补零，最后返回int型的结果
        // 2
        System.out.println(Integer.lowestOneBit(i));
        // 给定一个int类型数据，返回这个数据的二进制串中从最左边算起连续的“0”的总数量。
        // 因为int类型的数据长度为32所以高位不足的地方会以“0”填充
        // 24
        System.out.println(Integer.numberOfLeadingZeros(i));
        // 给定一个int类型数据，返回这个数据的二进制串中从最右边算起连续的“0”的总数量。
        // 因为int类型的数据长度为32所以高位不足的地方会以“0”填充。
        // 1
        System.out.println(Integer.numberOfTrailingZeros(i));
        // 循环左移
        // 936
        System.out.println(Integer.rotateLeft(i, 2));
        // 循环右移
        // -2147483590
        System.out.println(Integer.rotateRight(i, 2));
        // 返回数据的正负符号：1表示整数，0表示0,-1表示负数
        // 1
        System.out.println(Integer.signum(i));
        // 方法返回通过反转指定int值的二进制补码表示的字节的顺序而获得的值。
        // -369098752
        System.out.println(Integer.reverseBytes(i));
        // 11101010000000000000000000000000
        System.out.println(Integer.toBinaryString(Integer.reverseBytes(i)));
    }

    /**
     * Date问题很多，已经过时，不要使用
     * 使用常量
     * Java 8使用了新的日期类
     */
    @Test
    public void puzzler61() {
        Calendar cal = Calendar.getInstance();
        // Year, Month, Day
        cal.set(1999, 12, 31);
        System.out.print(cal.get(Calendar.YEAR) + " ");
        Date d = cal.getTime();
        // Deprecated, getDay很傻的
        System.out.println(d.getDay());
    }

    /**
     * 这个类用一个散列表实现了 Map 接口，它在比较键时，使用的是引用等价性而不是值等价性
     */
    @Test
    public void puzzler62() {
        Map<String, String> m =
                new IdentityHashMap<>();
        // 语言规范保证了字符串是内存限定的
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
        // 1
        System.out.println(m.size());
    }

    /**
     * boring, not interested
     */
    @Test
    public void puzzler63() {
    }

    /**
     * Math.abs 不能保证一定会返回非负的结果。如果它的参数是
     * Integer.MIN_VALUE，或者对于 long 版本的实现传递的是 Long.MIN_VALUE，那
     * 么它将返回它的参数。
     */
    @Test
    public void puzzler64() {
        // -2147483648
        System.out.println(Math.abs(Integer.MIN_VALUE));
        // -9223372036854775808
        System.out.println(Math.abs(Long.MIN_VALUE));
        // 4.9E-324
        System.out.println(Math.abs(Double.MIN_VALUE));
        // 1.4E-45
        System.out.println(Math.abs(Float.MIN_VALUE));
        // 0
        System.out.println(Math.abs(Character.MIN_VALUE));
        // 128
        System.out.println(Math.abs(Byte.MIN_VALUE));
        // 32768
        System.out.println(Math.abs(Short.MIN_VALUE));
    }

    /**
     * @see SuspiciousSort
     */
    @Test
    public void puzzler65() {
    }

    /**
     * @see PrivateMatter
     */
    @Test
    public void puzzler66() {
    }

    /**
     * 避免重用类名，尤其是 Java 平台类的类名
     * <p>
     * 著名的例子有 java.sql.Date，它与 java.util.Date 相冲突。
     */
    @Test
    public void puzzler67() {
    }

    /**
     * @see ShadesOfGray
     */
    @Test
    public void puzzler68() {
    }

    /**
     * @see ShadesOfGray
     */
    @Test
    public void puzzler69() {
    }

    /**
     * 一个包访问权限的方法不能被位于另一个包中的某个方法直接覆写
     */
    @Test
    public void puzzler70() {
    }

    /**
     * 导入的
     * toString 方法被 ImportDuty 从 Object 那里继承而来的具有相同名字的方法所
     * 遮蔽（shade）了[JLS 6.3.1]。遮蔽与遮掩（谜题 68）非常相像，二者的关键
     * 区别是一个声明只能遮蔽类型相同的另一个声明： 一个类型声明可以遮蔽另一个
     * 类型声明，一个变量声明可以遮蔽另一个变量声明，一个方法声明可以遮蔽另一
     * 个方法声明。与其形成对照的是，变量声明可以遮掩类型和包声明，而类型声明
     * 也可以遮掩包声明。
     * 当一个声明遮蔽了另一个声明时， 简单名将引用到遮蔽声明中的实体。 在本例中，
     * toString 引用的是从 Object 继承而来的 toString 方法。简单地说，本身就属
     * 于某个范围的成员在该范围内与静态导入相比具有优先权。 这导致的后果之一就
     * 是与 Object 的方法具有相同名字的静态方法不能通过静态导入工具而得到使
     * 用。
     * <p>
     * 再次说明一下，本谜题所要说明的仍然是你在
     * 覆写之外的情况中使用名字重用通常都会产生混乱。我们通过重载、隐藏和遮掩
     * 看清楚了这一点，现在我们又通过遮蔽看到了同样的问题。
     */
    @Test
    public void puzzler71() {
        Object[] args = new Object[5];
        /*
        本身就属于某个范围的成员在该范围内与静态导入相比具有优先权。 这导致的后果之一就
        是与 Object 的方法具有相同名字的静态方法不能通过静态导入工具而得到使用。
         */
        //toString(args);

        Arrays.toString(args);
    }

    /**
     * @see DoubleJeopardy
     */
    @Test
    public void puzzler72() {
    }

    /**
     * 通过重用名字，可以修改Api，使得Api可以编译通过，但是客户端编译失败。
     *
     * @see Api
     */
    @Test
    public void puzzler73() {

    }

    /**
     * @see Conundrum
     */
    @Test
    public void puzzler74() {
    }

    /**
     * boring , ignored
     */
    @Test
    public void puzzler75() {

    }

    /**
     * boring, 启动线程使用start()，run()方法并不能启动一个线程
     */
    @Test
    public void puzzler76() {
    }

    /**
     * @see Worker
     * <p>
     * 正如库类对锁的使用会干扰应用程序一样，应用程序中对锁的使用也会干扰库
     * 类。例如，在迄今为止发布的所有版本的 JDK（包括 5.0 版本）中，为了创建一
     * 个新的 Thread 实例，系统都会去获取 Thread 类上的锁。而执行下面的代码就可
     * 以阻止任何新线程的创建：
     */
    @Test
    public void puzzler77() {
        synchronized (Thread.class) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see Reflector
     * <p>
     * 访问其他包中的非公共类型的成员是不合法的，即使这个成员同时也被声
     * 明为某个公共类型的公共成员也是如此。不论这个成员是否是通过反射被访问
     * 的，上述规则都是成立的。
     */
    @Test
    public void puzzler78() {
    }

    /**
     * omit
     * 在Thread类内部会继承一些方法如sleep，谨防一些无意识的方法遮蔽
     */
    @Test
    public void puzzler79() {
    }

    /**
     * 更深层的反射
     *
     * @see Outer
     */
    @Test
    public void puzzler80() {
    }

    /**
     * print : Hello World
     * not the same as the book written.
     */
    @Test
    public void puzzler81() {
        String greeting = "Hello World";
        // Hello World, no need flash
        for (int i = 0; i < greeting.length(); i++) {
            System.out.write(greeting.charAt(i));
        }
    }

    /**
     * omit, what the fox thing!
     */
    @Test
    public void puzzler82() {

    }

    /**
     * @see SpecialDog
     * 这个谜题的主要教训就是一个实现了 Serializable 的单例类,必须有一个
     * readResolve 方法,用以返回它的唯一的实例。一个次要的教训就是,有可能由
     * 于对一个实现了 Serializable 的类进行了扩展,或者由于实现了一个扩展自
     * Serializable 的接口,使得我们在无意中实现了 Serializable。
     */
    @Test
    public void puzzler83() {
    }

    /**
     * 当一个方法捕捉
     * 到了一个 InterruptedException 异常,而且没有做好处理这个异常的准备时,
     * 那么这个方法通常会将该异常重新抛出(rethrow)。但是由于这是一个“被检
     * 查的异常”,所以只有在方法声明允许的情况下该方法才能够将异常重新抛出。
     * 如果不能重新抛出,该方法可以通过中断当前线程对异常“再构建”(reraise)。
     * <p>
     * Thread.interrupted 方法第一次被调用的时候返回了
     * true,并且清除了线程的中断状态,所以在 if-then-else 语句的分支中第 2 次
     * 调用该方法的时候,返回的就是 false。调用 Thread.interrupted 方法总是会
     * 清除当前线程的中断状态。
     */
    @Test
    public void puzzler84() {
        Thread.currentThread().interrupt();
        if (Thread.interrupted()) {
            // Interrupted: false
            System.out.println("Interrupted: " + Thread.interrupted());
        } else {
            System.out.println("Not interrupted: " +
                    Thread.interrupted());
        }
    }

    /**
     * 如果你只是想查询中断状态,请使用 isInterrupted 方法。
     */
    @Test
    public void puzzler84Two() {
        Thread.currentThread().interrupt();
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Interrupted : " + Thread.currentThread().isInterrupted());
        } else {
            System.out.println("Not Interrupted : " + Thread.currentThread().isInterrupted());
        }
    }

    /**
     * @see Lazy
     * deadlock initialization
     * 最好不要在初始化的时候，启动线程。
     */
    @Test
    public void puzzler85() {
    }

    /**
     * 你能否举出这样一个合法的 Java 表达式,只要对它的某个子表达式加上括号就
     * 可以使其成为不合法的表达式,而添加的括号只是为了注解未加括号时赋值的顺
     * 序?
     * <p>
     * 符号-2147483648 构成了一个合
     * 法的 Java 表达式,它由一元负操作符加上一个 int 型字面常量 2147483648 组成。
     * 通过添加一对括号来注解(很不重要的)赋值顺序,即写成-(2147483648),
     * 就会破坏这条规则。
     */
    @Test
    public void puzzler86() {
//        int i = -(2147483648);
//        long j = -(9223372036854775808L);
    }

    /**
     * == 具有对称性，不具备传递性
     */
    @Test
    public void puzzler87() {
        long x = Long.MAX_VALUE;
        double y = (double) Long.MAX_VALUE;
        long z = Long.MAX_VALUE - 1;
        // Imprecise! true
        System.out.println((x == y) + "");
        // Imprecise! true
        System.out.println((y == z) + "");
        // Precise! false
        System.out.println(x == z);
    }

    /**
     * raw type
     * <p>
     * List<?>是一种特殊的参数化类型,
     * 被称为通配符类型(wildcard type)。像原生类型 List 一样,编译器也不会知
     * 道它接受哪种类型的元素,但是因为 List<?>是一个参数化类型,从语言上来说
     * 需要更强的类型检查。为了避免出现 ClassCastException 异常,编译器不允许
     * 你添加除 null 以外的任何元素到一个类型为 List<?>的 list 中。
     *
     * @see Pair
     */
    @Test
    public void puzzler88() {
        List<?> list = new ArrayList<>();
        list.add(null);
    }

    /**
     * @see MyLinkedList
     * 注意泛型的遮蔽:内部类的泛型会遮蔽外部类的泛型
     */
    @Test
    public void puzzler89() {
    }

    /**
     * @see Outer
     * not the same as the book written
     */
    @Test
    public void puzzler90() {
    }

    /**
     * @see SerialKiller
     * hard to know
     */
    @Test
    public void puzzler91() {
    }

    /**
     * @see Twisted
     */
    @Test
    public void puzzler92() {

    }

    /**
     * @see PrintWords
     * @see com.test.shane.puzzler.another.Words
     * @see com.test.shane.puzzler.another.Words2
     * @see com.test.shane.puzzler.another.Words3
     * @see com.test.shane.puzzler.another.Words4
     * <p>
     * 先编译并运行PrintWords，然后再将Words改成Words2里的内容（类名不变），观察结果。
     * 然后再将Words3改成Words4里的内容（类名不变），观察结果。
     * 分别打印：
     * the null set
     * physics chemistry biology
     * <p>
     * <p>
     * 一个常量变量的定义是:一个在编译期被常量表达式初始化的 final 的原始类型或 String 类型的变量。
     * <p>
     * null 不是一个编译期常量表达式;
     * 枚举常量并不是一个常量变量，不会在编译期就被写入到class文件中。
     * 你可以在枚举类型中加入枚举常量,对它们重新排序,甚
     * 至可以移除没有用的枚举常量,而且并不需要重新编译客户端。
     */
    @Test
    public void puzzler93() {
    }

    /**
     * @see Shuffle
     * 公平算法和非公平算法
     */
    @Test
    public void puzzler94() {
    }

    @Test
    public void puzzler95() {
        Integer[] array = {3, 1, 4, 1, 5, 9};
        Arrays.sort(array, (i1, i2) -> (i1 < i2) ? 1 : (i2 > i1 ? -1 : 0));
        // 不排序, 从技术上说,程序的输出是未被定义的。它的比较器(comparator)承受
        // 着“是头我赢,是尾你输”的综合症。
        System.out.println(Arrays.toString(array));

        Arrays.sort(array, (i1, i2) ->
                (i1 < i2) ? -1 : (i2 > i1 ? 1 : 0)
        );
        // 排序
        System.out.println(Arrays.toString(array));
    }

}
