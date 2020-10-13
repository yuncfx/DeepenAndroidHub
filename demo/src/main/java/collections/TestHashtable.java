package collections;

import java.util.Enumeration;
import java.util.Hashtable;

import org.junit.Test;

public class TestHashtable {

    @Test
    public void testHashtable() {
        Hashtable<String, Double> balance = new Hashtable<String, Double>();

        Enumeration<String> names;
        String str;
        double bal;

        balance.put("A", 3434.34);
        balance.put("B", 123.22);
        balance.put("C", 1378.00);
        balance.put("D", 99.22);
        balance.put("E", -19.08);

        names = balance.keys();
        while (names.hasMoreElements()) {
          str = names.nextElement();
          System.out.println(str + ": " + balance.get(str));
        }
        bal = balance.get("A");
        balance.put("A", bal + 1000);
        System.out.println("A's new balance: " + balance.get("A"));
    }
}
