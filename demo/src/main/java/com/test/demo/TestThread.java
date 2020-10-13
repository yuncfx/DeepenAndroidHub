package com.test.demo;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class TestThread {

    PipedReader reader;
    PipedWriter writer;

    public class DemoThread extends Thread {
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                char[] chars = new char[1024];
                try {
                    int length = reader.read(chars);
                    System.out.println("read:" + new String(chars));

                    if (length == -1) {
                        break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test() {
        reader = new PipedReader();
        writer = new PipedWriter();
        try {
            writer.connect(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DemoThread demoThread = new DemoThread();
        demoThread.start();
        int count = 0;
        while (count < 1000) {
            try {
                writer.write("hello world");
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        demoThread.interrupt();
    }
}


