package simpletest;

public class TestCode {
    public static void main(String[] args) {
        String s = "sfdaf[[dfadsfdsaf[[[sdfdaf";
        
        String[] strs = s.split("\\[\\[");
        
        String[] strs2 = s.split("\\[+");
        
        System.out.println(strs);
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        
        System.out.println(strs2);
        for (int i = 0; i < strs2.length; i++) {
            System.out.println(strs2[i]);
        }
    }

}
