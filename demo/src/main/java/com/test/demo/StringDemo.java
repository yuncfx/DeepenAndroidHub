package com.test.demo;

import org.junit.Test;

public class StringDemo {

    @Test
    public void test() {
        String content = "&amp;";
        long l = System.currentTimeMillis();
//        content = HtmlManipulator.replaceHtmlEntities(content);
        System.out.println("duration: " + (System.currentTimeMillis() - l));
        System.out.println(content);
    }
}
