package simpletest;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Kata {

    private static final String YOU = "you";
    private static final String U = "u";
    private static final String GOOD_STR = "your sister";

    public static String autocorrect(String input) {

//        if (input == null || input.trim().length() == 0) {
//            return "";
//        }
//
//        String reg = "\\b[yY]{1}[oO]{1}[uU]+\\b";
//        Pattern p = Pattern.compile(reg);
//        Matcher m = p.matcher(input);
//
//        while (m.find()) {
//            input = input.replaceAll(reg, GOOD_STR);
//        }
//
//        String[] strs = input.split(" ");
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < strs.length; i++) {
//            if (strs[i].equalsIgnoreCase(U) || strs[i].equalsIgnoreCase(YOU)) {
//                strs[i] = GOOD_STR;
//            }
//
//            sb.append(strs[i]);
//
//            if (i != strs.length - 1) {
//                sb.append(" ");
//            }
//        }
//
//        return sb.toString().trim(); // "corrected" input
        input = input.replaceAll("(?i)\\b(u|you+)\\b", GOOD_STR);
        
                System.out.println(input);
        return input;
        
    }

    @Test
    public void test() {
        // autocorrect("haha YouuUuu youuuuu jjjll");

        autocorrect("I want to film the bayou with you and put it on youtube");
    }

}
