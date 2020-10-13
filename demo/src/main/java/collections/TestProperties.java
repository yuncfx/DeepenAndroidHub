package collections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

public class TestProperties {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        // properties.list(System.out);

        //// FileInputStream in = null;
        //
        //// try {
        //// in = new FileInputStream("colon.txt");
        //// } catch (FileNotFoundException e) {
        //// e.printStackTrace();
        //// }
        //// try {
        //// properties.load(in);
        //// } catch (IOException e) {
        //// e.printStackTrace();
        //// }

        properties.list(System.out);
        System.out.println("\nValue of local.animal.defined is " + Boolean.getBoolean("local.animal.defined"));
        System.out.println("\nValue of local.animal.legcount is " + Integer.getInteger("local.animal.legcount"));
    }

    /**
     *  foo:bar
        one
        two
        three=four
        five  six seven eight
        nine ten
     */
    @Test
    public void test1() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("colon.txt"));
            p.list(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String property = p.getProperty("foo");
        System.out.println(property);

    }

    @Test
    public void test2() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("colon.txt"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Enumeration e = p.propertyNames();

        for (; e.hasMoreElements();) {
            System.out.println(e.nextElement());

        }
    }

    /**
     * t.txt: #no comments #Mon Sep 05 16:18:17 CST 2016 five=six seven eight
     * two= one= nine=ten three=four foo=bar
     */
    @Test
    public void testStore() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("colon.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            p.store(new FileOutputStream("t.txt"), "no comments");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {

        Properties props = System.getProperties();
        Enumeration e = props.propertyNames();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            System.out.println(key + " -- " + props.getProperty(key));
        }
    }

    @Test
    public void testPut() {
        Properties capitals = new Properties();

        capitals.put("Illinois", "Springfield");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Washington", "Olympia");
        capitals.put("California", "Sacramento");
        capitals.put("Indiana", "Indianapolis");

        Set states = capitals.keySet();

        for (Object name : states)
            System.out.println(name + " / " + capitals.getProperty((String) name));

        String str = capitals.getProperty("Florida", "Not Found");
        System.out.println("The capital of Florida is " + str + ".");
    }

    /**
     * Sort Properties when saving.
     */
    @Test
    public void testSortedProperties() {
        SortedProperties sp = new SortedProperties();
        sp.put("B", "value B");
        sp.put("C", "value C");
        sp.put("A", "value A");
        sp.put("D", "value D");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("sp.props");
            sp.store(fos, "sorted props");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        Properties props = new Properties();
        URL url = ClassLoader.getSystemResource("sp.props");
//        URL url = ClassLoader.getSystemResource("/com/java2s/config/system.props");
        try {
            if (url != null)
                props.load(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(props);
    }

    /**
     *  <?xml version="1.0" encoding="UTF-8" standalone="no"?>
        <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
            <properties>
            <comment>updated</comment>
            <entry key="user">A</entry>
            <entry key="today">Mon Sep 05 16:50:30 CST 2016</entry>
        </properties>

     */
    @Test
    public void testLoadFromXml() {
        Properties p = new Properties();

        p.put("today", new Date().toString());
        p.put("user", "A");

        try {
            FileOutputStream out = new FileOutputStream("user.props");
            p.storeToXML(out, "updated");

            FileInputStream in = new FileInputStream("user.props");

            p.loadFromXML(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.list(System.out);

    }
    
    @Test
    public void testSaveToXml() {
        Properties properties = new Properties();
        properties.setProperty("database.type", "mysql");
        properties.setProperty("database.url", "jdbc:mysql://localhost/mydb");
        properties.setProperty("database.username", "root");
        properties.setProperty("database.password", "root");

        try {
            FileOutputStream fos = new FileOutputStream("database-configuration.xml");
            properties.storeToXML(fos, "Database Configuration", "UTF-8");
        } catch(IOException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSetProperty() {
        Properties properties = new Properties();
        try {
          properties.load(new FileInputStream("filename.properties"));
        } catch (IOException e) {
        }
        String string = properties.getProperty("a.b");
        properties.setProperty("a.b", "new value");
    }
    
    @Test
    public void propertiesToHashMap() {
        Properties prop = new Properties();

        prop.setProperty("A", "t@h.com");
        prop.setProperty("B", "k@h.com");
        prop.setProperty("C", "R@h.com");
        prop.setProperty("D", "S@h.com");

        HashMap<String, String> propMap = new HashMap<String, String>((Map) prop);

        Set<Map.Entry<String, String>> propSet;
        propSet = propMap.entrySet();

        System.out.println("Contents of map: ");
        for (Map.Entry<String, String> me : propSet) {
          System.out.print(me.getKey() + ": ");
          System.out.println(me.getValue());
        }
    }

    class SortedProperties extends Properties {
        public Enumeration keys() {
            Enumeration keysEnum = super.keys();
            Vector<String> keyList = new Vector<String>();
            while (keysEnum.hasMoreElements()) {
                keyList.add((String) keysEnum.nextElement());
            }
            Collections.sort(keyList);
            return keyList.elements();
        }

    }
}
