package simpletest;

public class TestGenerics {
    public static void main(String[] args) {
        GenericClass<Integer> obj = new GenericClass<>(88);
        obj.showType();

        int v = obj.getOb();
        System.out.println("value : " + v);

        GenericClass<String> strObj = new GenericClass<>("Generics Test");
        strObj.showType();

        String str = strObj.getOb();
        System.out.println("value : " + str);
    }
}

class GenericClass<T> {
    T ob;

    public GenericClass(T o) {
        ob = o;
    }

    public T getOb() {
        return ob;
    }

    public void showType() {
        System.out.println("Type of T is " + ob.getClass().getName());
    }
}
