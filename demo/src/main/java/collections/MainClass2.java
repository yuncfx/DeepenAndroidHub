package collections;

import java.util.Arrays;

public class MainClass2 {

    public static void main(String[] args) {
/*        long[][][] beans = new long[5][10][30];

        for (int i = 0; i < beans.length; i++) {
            for (int j = 0; j < beans[i].length; j++) {
                beans[i][j] = new long[(int) (1.0 + 6.0 * Math.random())];
            }
        }*/
        
        String[] letters = { "A", "I", "U", "E", "O" };

        String[] results = new String[3];

        System.arraycopy(letters, 2, results, 0, 3);

        for (int i = 0; i < results.length; i++) {
          System.out.println("result = " + results[i]);
        }
        
        
        System.out.printf("Before (original)\t%s%n", Arrays.toString(args));
        String copy[] = Arrays.copyOf(args, 4);
        System.out.printf("Before (copy)\t\t%s%n", Arrays.toString(copy));
        copy[0] = "A";
        copy[1] = "B";
        copy[2] = "C";
        copy[3] = "D";
        System.out.printf("After (original)\t%s%n", Arrays.toString(args));
        System.out.printf("After (copy)\t\t%s%n", Arrays.toString(copy));
        
        
    }

}