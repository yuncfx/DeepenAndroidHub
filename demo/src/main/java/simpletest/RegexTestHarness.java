package simpletest;

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 由于当前版本的 Java Tutorial 是基于 JDK 6.0 编写的，上述的测试用具由于使用到 JDK 6.0
 * 中新增的类库（java.io.Console），所以该用具只能在 JDK 6.0 的环境中编译运行，由于 Console
 * 访问操作系统平台上的控制台，因此这个测试用具只能在操作系统的字符控制台中运行，不能运行在 IDE 的控制台中。
 * 
 * @author maoxl
 *
 */
public class RegexTestHarness {

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        while (true) {
            Pattern pattern = Pattern.compile(console.readLine("%nEnter your regex: "));
            Matcher matcher = pattern.matcher(console.readLine("Enter input string to search: "));
            boolean found = false;
            while (matcher.find()) {
                console.format("I found the text \"%s\" starting at index %d " + "and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found) {
                console.format("No match found.%n");
            }
        }
    }
}