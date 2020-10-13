package simpletest;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class TestReflections {
    public static void main(String[] args) {

    }

    @Test
    public void showClass() {
        System.out.println(TestReflections.class.getName());
        System.out.println(int[].class.getName());
        System.out.println(TestReflections[].class.getName());
        System.out.println(TestReflections.class.getPackage());
        System.out.println(TestReflections.class.getCanonicalName());
    }

    @Test
    public void newInstance() {
        try {
            String pc = String.class.newInstance();
            System.out.println(pc);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMethods() {
        Method[] methods = String.class.getMethods();
        for (int idx = 0; idx < methods.length; idx++) {
            System.out.println(methods[idx] + " delcared by " + methods[idx].getDeclaringClass());
        }

    }

    public void testType() {
        final Set<Class> classes = new HashSet<>();
        classes.add(Class.class);
        classes.add(Comparable.class);
        classes.add(Serializable.class);
        classes.add(Integer.class);
        classes.add(int.class);
        classes.add(Float[].class);
        classes.add(String.class);
        classes.add(double[].class);
        classes.add(boolean.class);
        Iterator<Class> it = classes.iterator();
        while (it.hasNext()) {
            Class clazz = it.next();
            if (clazz.isArray()) {
                System.out.println(clazz + " is array");
            }

            if (clazz.isPrimitive()) {
                System.out.println(clazz + " is primitive");
            }

            if (clazz.isInterface()) {
                System.out.println(clazz + " is interface");
            }

            if (clazz.isAnnotation()) {
                System.out.println(clazz + " is annotation");
            }

            if (clazz.isAnonymousClass()) {
                System.out.println(clazz + " is anonymous class");
            }

            if (clazz.isEnum()) {
                System.out.println(clazz + "is enum");
            }

            if (clazz.isLocalClass()) {

            }

            if (clazz.isMemberClass()) {

            }

            if (clazz.isSynthetic()) {

            }

        }

    }

}
