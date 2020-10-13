package collections;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

public class TestEnumeration {

    @Test
    public void test1() throws IOException {
        Vector v = new Vector(3);
        v.add(new FileInputStream("/a/b"));
        v.add(new FileInputStream("yourfile.bar"));
        v.add(new FileInputStream("/yourfile.txt"));

        Enumeration e = v.elements();
        SequenceInputStream sis = new SequenceInputStream(e);
        InputStreamReader isr = new InputStreamReader(sis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
