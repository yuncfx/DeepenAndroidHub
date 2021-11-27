package zx.base.s2;
interface ResponseResut{
    void resule(String msg,int code);
}
public class ktbase29 {
    public static void main(String[] args) {
        loginApi("derry","dgdg", new ResponseResut() {
            @Override
            public void resule(String msg, int code) {
                System.out.println(msg+code);
            }
        });
    }

    private static void loginApi(String name, String password, ResponseResut responseResut) {
    if (name.equals("derry")&& password.equals("123456")){
        responseResut.resule("登录成功。",200);
    }else{
        responseResut.resule("登录失败。",444);
    }
    }


}
