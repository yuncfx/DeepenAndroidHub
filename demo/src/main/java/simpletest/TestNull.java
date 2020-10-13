package simpletest;

public class TestNull extends Father {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        String str = null;

        String str2 = (String) str;

        System.out.println(str2);
        
        TestNull.testF();
    }
    
    public static void testF() {
        System.out.println("Son testF");
    }
}

//class Father {
//    public static void testF() {
//        System.out.println("Father testF");
//    }
//}