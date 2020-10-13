package simpletest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author shane
 */
public class TestEncode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("@", "utf-8");
        System.out.println(encode);

    }
}
