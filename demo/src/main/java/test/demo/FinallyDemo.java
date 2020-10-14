package test.demo;

import org.junit.Test;

public class FinallyDemo {
    @Test
    public void test() {
        try{
            System.out.println(1/0);
        }catch(Exception e){
            System.out.println("除数为0了");
            System.exit(0);     //退出JVM虚拟机
            return ;
        }finally{
            System.out.println("看看我执行了吗");
        }
    }
}
