package simpletest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestPattern {
    
    public static final String EXAMPLE_TEST = "This is my small example "
            + "string which I'm going to " + "use for pattern matching.";
    
    public static void main(String[] args) {
        String regex = "^\\d+(\\.\\d+)?";// "this\\s+is\\s+text";//;"^\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("5.15");
        boolean find = matcher.matches();
        System.out.println(find);
    }

    @Test
    public void test1() {
        String regex = "^1[a-z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("1b");
        boolean find = matcher.find();

        System.out.println(find);
    }

    @Test
    public void test2() {
        String regex = "[a^e]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("^");
        boolean find = matcher.matches();

        System.out.println(find);
    }

    @Test
    public void test3() {
        String regex = "[^\\w]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(" ");
        boolean find = matcher.matches();

        System.out.println(find);
    }
    
    @Test
    public void test4() {
        String regex = "\\d{1,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("3");
        boolean find = matcher.matches();

        System.out.println(find);
    }
    
    @Test
    public void test5() {
        String regex = "a(?!b)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("abacad");
/*        boolean find = matcher.find();
        boolean find1 = false;*/
        while(matcher.find()) {
            System.out.println(matcher.group());
        }
                
    }
    
    /**
     * from vogella
     */
    @Test
    public void test6() {
        System.out.println(EXAMPLE_TEST.matches("\\w.*"));
        String[] splitString = (EXAMPLE_TEST.split("\\s+"));
        System.out.println(splitString.length);// should be 14
        for (String string : splitString) {
                System.out.println(string);
        }
        // replace all whitespace with tabs
        System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));
    }
    
   
    
    @Test
    public void test7() {
        Pattern pattern = Pattern.compile("\\w+");
        // in case you would like to ignore case sensitivity,
        // you could use this statement:
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(EXAMPLE_TEST);
        // check all occurrence
        while (matcher.find()) {
                System.out.print("Start index: " + matcher.start());
                System.out.print(" End index: " + matcher.end() + " ");
                System.out.println(matcher.group());
        }
        // now create a new pattern and matcher to replace whitespace with tabs
        Pattern replace = Pattern.compile("\\s+");
        Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
        System.out.println(matcher2.replaceAll("\t"));
    }
    
    @Test
    public void test8() {
        String pattern = "\\d\\d\\d([,\\s])?\\d\\d\\d\\d";
        String s= "1233323322";
        assertFalse(s.matches(pattern));
        s = "1233323";
        assertTrue(s.matches(pattern));
        s = "123 3323";
        assertTrue(s.matches(pattern));
        
        s = "123,3323";
        System.out.println(s.matches(pattern));
    }
    
    @Test
    public void test9() {
        LinkGetter linkGetter = new LinkGetter();
        List<String> list = linkGetter.getLinks("http://www.vogella.com/tutorials/JavaRegularExpressions/article.html");
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Test
    public void getDemo() {
        String str = "ming tian jiu yao fang jia le ,da jia.";
        String reg = "\\b[a-zA-Z]{3}\\b"; // '\b' 单词边界
        // 将规则封装成对象
        Pattern p = Pattern.compile(reg);
        // 让正则对象和要作用的字符串相关联, 获取匹配器对象.
        Matcher m = p.matcher(str);
        /*
         * 其实String类中的matches方法,用的就是Pattern和Matcher对象来完成的,
         * 只不过String的对象封装后,用起来较为简单, 但是功能却单一.
         */
        // m.matches();
        /*
         * 尝试查找与该模式想匹配的下一个子序列 将规则作用到字符串上,并进行符合规则的子串查找
         */
        // m.find();
        // System.out.println(m.group()); //用于获取匹配后的结果.
        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.start() + "..." + m.end());
        }
    }

}
