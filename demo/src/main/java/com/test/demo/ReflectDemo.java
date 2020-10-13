package com.test.demo;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;

public class ReflectDemo {
    @Test
    public void test() throws Exception {
        Class<ReflectBean> clazz = (Class<ReflectBean>) Class.forName("com.test.demo.ReflectBean");
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        System.out.println(declaredConstructors.length);
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor + ", " + constructor.isAccessible());
            constructor.setAccessible(true);
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            System.out.println("parameterTypes.length:" + parameterTypes.length);
            for (Class cc : parameterTypes) {
                System.out.println("cc.getName():" + cc.getName());
            }
//            Object o = constructor.newInstance();
//            System.out.println(o);
        }

        Constructor<ReflectBean> declaredConstructor = clazz.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        ReflectBean bean = declaredConstructor.newInstance(null);
        System.out.println(bean);
    }

    @Test
    public void testClass() throws Exception {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;

        class1 = Class.forName("com.test.demo.ReflectBean");
//        class2 = new ReflectBean().getClass();
        class3 = ReflectBean.class;

        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class3);
    }

    @Test
    public void testSuperClass() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        Class<?> superclass = clazz.getSuperclass();
        System.out.println(superclass.getName());

        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> c : interfaces) {
            System.out.println(c.getName());
        }
    }

    @Test
    public void testConstructors() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor + ", " + constructor.isAccessible());
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> para : parameterTypes) {
                System.out.print(para.getName() + ", ");
            }
            System.out.println();
            constructor.setAccessible(true);
//            ReflectBean bean = (ReflectBean) constructor.newInstance();
//            System.out.println(bean);
        }
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        Constructor<?> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        ReflectBean bean = (ReflectBean) constructor.newInstance();
        System.out.println(bean);
    }

    @Test
    public void testFields() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            int modifiers = f.getModifiers();
            String modifyStr = Modifier.toString(modifiers);
            Class<?> type = f.getType();
            System.out.println(modifyStr + " " + type.getName() + " " + f.getName() + ";");
        }
        System.out.println("**************");

        Field[] superFields = clazz.getFields();
        System.out.println(superFields.length);
        for (Field f : superFields) {
            int modifiers = f.getModifiers();
            String modifyStr = Modifier.toString(modifiers);
            AnnotatedType annotatedType = f.getAnnotatedType();
            System.out.println(annotatedType);
            Annotation[] annotations = f.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
            Class<?> type = f.getType();
            System.out.println(modifyStr + " " + type.getName() + " " + f.getName() + ";");
        }
    }

    @Test
    public void testMethods() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            Class<?>[] parameterTypes = method.getParameterTypes();
            int modifiers = method.getModifiers();
            String s = Modifier.toString(modifiers);
            System.out.print(s + " ");
            System.out.print(returnType.getName() + "  ");
            System.out.print(method.getName() + " ");

            System.out.print("(");
            for (int j = 0; j < parameterTypes.length; ++j) {
                System.out.print(parameterTypes[j].getName() + " " + "arg" + j);
                if (j < parameterTypes.length - 1) {
                    System.out.print(",");
                }
            }
            Class<?>[] exce = method.getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(") throws ");
                for (int k = 0; k < exce.length; ++k) {
                    System.out.print(exce[k].getName() + " ");
                    if (k < exce.length - 1) {
                        System.out.print(",");
                    }
                }
            } else {
                System.out.print(")");
            }
            System.out.println();
        }
    }

    @Test
    public void testMethodInvoke() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        Method print = clazz.getMethod("print");
        print.invoke(clazz.newInstance());

        Method reflect2 = clazz.getMethod("reflect2", int.class, String.class);
        reflect2.invoke(clazz.newInstance(), 20, "zhangsan");

        Class<?> aClass = Class.forName("bean.SingletonBean");
        Method getInstance = aClass.getDeclaredMethod("getInstance", null);
        Object invoke = getInstance.invoke(null, null);

        System.out.println(invoke);
    }

    @Test
    public void testFieldUpdate() throws Exception {
        Class<?> clazz = Class.forName("com.test.demo.ReflectBean");
        ReflectBean bean = (ReflectBean) clazz.newInstance();
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(bean, "Java reflect");
        System.out.println(name);
        System.out.println(bean);
        System.out.println(name.get(bean));
    }

    @Test
    public void testTricky1() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "Java reflection");
        System.out.println(list.get(0));
    }

    /**
     * 通过反射取得并修改数组的信息
     */
    @Test
    public void testTricky2() throws Exception {
        int[] temp = {1, 2, 3, 4, 5};
        Class<?> componentType = temp.getClass().getComponentType();
        System.out.println(componentType.getName());
        System.out.println(Array.getLength(temp));
        System.out.println(Array.get(temp, 0));
        Array.set(temp, 0, 100);
        System.out.println(Array.get(temp, 0));
    }

    @Test
    public void testTricky3() throws Exception {
        int[] temp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] newTemp = (int[]) arrayInc(temp, 15);
        print(newTemp);
        String[] atr = {"a", "b", "c"};
        String[] str1 = (String[]) arrayInc(atr, 8);
        print(str1);
    }

    // 修改数组大小
    public static Object arrayInc(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();
        Object newArr = Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }

    // 打印
    public static void print(Object obj) {
        Class<?> c = obj.getClass();
        if (!c.isArray()) {
            return;
        }
        System.out.println("数组长度为： " + Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + " ");
        }
        System.out.println();
    }

    static class FatherBean {
        public String country;
    }


}
