package strange;

public class TestFinally {
    public static void main(String[] args) {
        System.out.println("hahaha begin");
        try {
            if (false) {

            }
        } catch (Exception e) {

        } finally {
            System.out.println("hahaha finally");
        }
        System.out.println("hahaha end");

    }
}
