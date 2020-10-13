package bean;

public class SingletonBean {
    private static SingletonBean ourInstance = new SingletonBean();

    public static SingletonBean getInstance() {
        return ourInstance;
    }

    private SingletonBean() {
    }
}
