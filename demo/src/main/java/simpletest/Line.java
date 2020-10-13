package simpletest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class Line {

    private static final String _50 = "50";
    private static final String _25 = "25";
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();

    public static String Tickets(int[] peopleInLine) {
        if (null == peopleInLine || peopleInLine.length == 0) {
            return "YES";
        }

        int count25 = 0, count50 = 0;

        for (int i = 0; i < peopleInLine.length; i++) {

            switch (peopleInLine[i]) {
            case 25:
                map.put(_25, ++count25);
                break;
            case 50:
                map.put(_50, ++count50);
                map.put(_25, --count25);
                break;
            case 100:
                if (count50 > 0 && count25 > 0) {
                    map.put(_50, --count50);
                    map.put(_25, --count25);
                } else {
                    map.put(_25, count25 - 3);
                    count25 -= 3;
                }
                break;
            }
        }
        
        if (map.get(_25) < 0) {
            return "NO";
        }

        return "YES";
    }

    @Test
    public void test1() {
        assertEquals("YES", Line.Tickets(new int[] { 25, 25, 50 }));
    }

    @Test
    public void test2() {
        assertEquals("NO", Line.Tickets(new int[] { 25, 100 }));
    }
}
