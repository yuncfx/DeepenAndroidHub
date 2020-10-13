package simpletest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarnessV4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
        while (true) {
            System.out.print("\nEnter your regex: ");
            Pattern pattern = Pattern.compile(br.readLine());
            System.out.print("Enter input string to search: ");
            Matcher matcher = pattern.matcher(br.readLine());
            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text \"" + matcher.group() + "\" starting at index " + matcher.start()
                        + " and ending at index " + matcher.end() + ".");
                found = true;
            }
            if (!found) {
                System.out.println("No match found.");
            }
        }
    }
}