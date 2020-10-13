package simpletest;

/*
    网页爬虫(蜘蛛)
*/
import java.io.*;
import java.util.regex.*;
import java.net.*;

class RegexTest2 {
    public static void main(String[] args) throws Exception {
        // getMails();
        getMails_1();
    }

    public static void getMails_1() throws Exception {
        // URL url = new URL("http://192.168.1.104:8080/myweb/mail.html");
        URL url = new URL("http://tieba.baidu.com/p/1582858352?pid=19615718830&cid=0#19615718830");
        URLConnection conn = url.openConnection();
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line = null;
        String mailreg = "\\w+@\\w+(\\.\\w+)+";
        Pattern p = Pattern.compile(mailreg);
        while (null != (line = bufIn.readLine())) {
            // System.out.println(line);
            Matcher m = p.matcher(line);
            while (m.find()) {
                System.out.println(m.group());
            }
        }
    }

    /*
     * 获取指定文档中的邮件地址. 使用获取功能, Pattern Matcher
     */
    public static void getMails() throws Exception {

        BufferedReader bufr = new BufferedReader(new FileReader("mail.txt"));

        String line = null;
        String mailreg = "\\w+@\\w+(\\.\\w+)+";
        Pattern p = Pattern.compile(mailreg);
        while (null != (line = bufr.readLine())) {
            // System.out.println(line);
            Matcher m = p.matcher(line);
            while (m.find()) {
                System.out.println(m.group());
            }
        }
        
        bufr.close();
    }
}