package simpletest;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexDemo {

    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoob";
    private static String REPLACE = "-";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获得匹配器对象
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, REPLACE);
            System.out.println(sb + " start =" + m.start() + " end = " + m.end() + " group = " + m.group());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}