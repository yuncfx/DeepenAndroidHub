package test.puzzler;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author shane
 *         <p>
 *         这里的问题并不在于该方法的访问级别（access level），而在于该方法所
 *         在的类型的访问级别。 这个类型所扮演的角色和一个普通方法调用中的限定类型
 *         （qualifying type）是相同的[JLS 13.1]。在这个程序中，该方法是从某个类
 *         中选择出来的，而这个类型是由从 it.getClass 方法返回的 Class 对象表示的。
 *         这是迭代器的动态类型 （dynamic type） ， 它恰好是私有的嵌套类(nested class)
 *         java.util.HashMap.KeyIterator。出现 IllegalAccessException 异常的原因
 *         就是这个类不是公共的，它来自另外一个包：访问位于其他包中的非公共类型的
 *         成员是不合法的[JLS 6.6.1]。无论是一般的访问还是通过反射的访问，下述的
 *         禁律都是有效的。
 */

public class Reflector {
    /**
     * Exception in thread "main" java.lang.IllegalAccessException:
     * Class Reflector can not access a member of class HashMap$HashIterator
     * with modifiers "public"
     */
    public static void main(String[] args) throws NoSuchMethodException
            , InvocationTargetException, IllegalAccessException {
        Set<String> s = new HashSet<>();
        s.add("foo");
        Iterator iterator = s.iterator();
        Method m = iterator.getClass().getMethod("hasNext");
        System.out.println(m.invoke(iterator));

/*        在使用反射访问某个类型时，请使用表示某种可访问类型的 Class 对象。回到我
        们前面的那个程序，hasNext 方法是声明在一个公共类型 java.util.Iterator
        中的，所以它的类对象应该被用来进行反射访问。经过这样的修改后，这个
        Reflector 程序就会打印出 true：
        */
        Method m2 = Iterator.class.getMethod("hasNext");

        // cannot access hashCode in java.lang.Object
        /*
         * 这个错误与前面那个由含有反射的程序所产生的运行期错误具有相同的意义。
         Object 类型和 hashCode 方法都是公共的。问题在于 hashCode 方法是通过一个
         限定类型调用的，但用户访问不到这个类型。该方法调用的限定类型是
         library.Api.PackagePrivate，这是一个位于其他包的非公共类型
         */
//        System.out.println(ApiAnother.member.hashCode());

        System.out.println(((Object) ApiAnother.member).hashCode());


    }
}
