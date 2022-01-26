@file:Official

package com.ssy.chapter7

import com.ssy.Official
import java.io.IOException
import java.io.Writer

/*
   TODO 设置JvmDefault
   @JvmDefault注解就是为非抽象的接口成员生成默认的方法。

   在没有添加 @JvmDefault注解，Kotlin会自动生成一个叫做 DefaultImpl静态内部类，用于保存静态方法的默认实现，
   并使用自身接收器类型来模拟属于对象的方法。然后，对于扩展该接口的每种类型，如果类型没有实现方法本身，
   则在编译时，Kotlin将通过调用将方法连接到默认实现。
 */
//interface ITeaching {
//    @JvmDefault//注意: 可能一开始使用该注解会报错，需要在gradle中配置jvm参数:-jvm-target=1.8 -Xjvm-default=enable
//    fun speak() = println("open the book")
//}


/*
    @JvmField 它会自动将该字段的setter、getter访问器消除掉，并且把这个字段修改为public

    to Java code:

    public final class Person {
       @JvmField
       public int age = 18;//消除了setter、getter访问器，并且age字段为public公开
    }

    //在Java中调用
    public static void main(String[] args) {
        System.out.println(Person.MAX_AGE);//可以直接调用，因为它已经变成了public了
    }
 */
class Person {
    @JvmField
    var age = 18

    companion object {
        @JvmField
        val maxAge = 120
    }
}

/*
    @file:JvmName("IOUtils")
    @file:JvmMultifileClass, 可以将不同文件中的函数，属性等，合并到一个类中。

    //存在于IOUtilA文件中
    @file:JvmName("IOUtils")
    @file:JvmMultifileClass

    package com.mikyou.annotation

    import java.io.IOException
    import java.io.Reader


    fun closeReaderQuietly(input: Reader?) {
        try {
            input?.close()
        } catch (ioe: IOException) {
            // ignore
        }
    }

    //存在于IOUtilB文件中
    @file:JvmName("IOUtils")
    @file:JvmMultifileClass

    package com.mikyou.annotation

    import java.io.IOException
    import java.io.InputStream


    fun closeStreamQuietly(input: InputStream?) {
        try {
            input?.close()
        } catch (ioe: IOException) {
            // ignore
        }
    }
    //在Java中使用
    public class Test {
        public static void main(String[] args) {
            //即使存在于不同文件中，但是对于外部Java调用仍然是同一个类IOUtils
            IOUtils.closeReaderQuietly(null);
            IOUtils.closeStreamQuietly(null);
        }
    }
 */

/*
    @JvmName
    将改变由Kotlin默认生成的Java方法、字段或类名
 */
class Student {
    @get:JvmName(name = "getStudentName")//修改属性的getter函数名称
    @set:JvmName(name = "setStudentName")//修改属性的setter函数名称
    var name: String = "Tim"

    @JvmName("getStudentScore")//修改函数名称
    fun getScore(): Double {
        return 110.5
    }
}

/*
    @JvmOverloads 指导Kotlin编译器为带默认参数值的函数(包括构造函数)生成多个重载函数。
    class ScrollerView @JvmOverloads constructor(
        context: Context,
        attr: AttributeSet? = null,
        defStyle: Int = 0
    ) : View(context, attr, defStyle) {
        //...
    }
 */

/*
    @JvmPackageName
    更改从使用该注解标注的文件生成的.class文件的JVM包的完全限定名称
    @file:kotlin.jvm.JvmPackageName("kotlin.collections.jdk8")
    使用限制，文件中不能包含类定义， 忽略吧，这个是jvm的internal中定义的。
 */

/*
    @JvmStatic
    能被用在对象声明或者Companion object伴生对象的方法上，把它们暴露成一个Java的静态方法

    //在java中调用,可以直接这样Data.getDefaultDataName()调用
    public class Test {
        public static void main(String[] args) {
            System.out.println(Data.getDefaultDataName());
        }
    }
 */
class Data {
    companion object {
        @JvmStatic
        fun getDefaultDataName(): String {
            return "default"
        }
    }
}

/*
    @JvmSuppressWildcards和@JvmWildcard
    用于指示编译器生成或省略类型参数的通配符，JvmSuppressWildcards用于参数的泛型是否生成或省略通配符，
    而JvmWildcard用于返回值的类型是否生成或省略通配符

    class CovertImpl implements ICovert {
    @Override
    public void covertData(List<? extends String> datas) {//参数类型生成通配符

    }
    @Override
    public List<? extends String> getData() {//返回值类型生成通配符
        return null;
    }
}

 */
interface ICovert {
    fun covertData(datas: List<@JvmSuppressWildcards(suppress = false) String>)//@JvmSuppressWildcards用于参数类型

    fun getData(): List<@JvmWildcard String>//@JvmWildcard用于返回值类型
}

/*
    @JvmSynthetic
    1、作用
        它在生成的类文件中将适当的元素标记为合成,并且编译器标记为合成的任何元素都将无法从Java语言中访问。
    2、什么是合成属性(Synthetic属性)?
        JVM字节码标识的ACC_SYNTHETIC属性用于标识该元素实际上不存在于原始源代码中，而是由编译器生成。
    3、合成属性能做什么?
        它一般用于支持代码生成，允许编译器生成不应向其他开发人员公开但需要支持实际公开接口所需的字段和方法。我们可以将其视为超越private或protected级别。
 */
class Synthetic {
    @JvmSynthetic
    val name: String = "Tim"
    var age: Int
        @JvmSynthetic
        set(value) {
        }
        @JvmSynthetic
        get() {
            return 18
        }
}

/*
    @Throws
    用于Kotlin中的函数,属性的setter或getter函数，构造器函数抛出异常
 */
@Throws(IOException::class)
fun closeQuietly(output: Writer?) {
    output?.close()
}

/*
    @Transient
    该注解充当了Java中的transient关键字

    @Synchronized
    该注解充当了Java中的synchronized关键字

    @Volatile
    该注解充当了Java中的volatile关键字
 */

/*
    @Strictfp
    该注解充当了Java中的strictfp关键字
    strictfp关键词的作用就是规范Java中的浮点类型的计算让计算结果更加精确，因为Java的底层默认计算方式不是很精确，
    在不同的平台会得到不同的结果，结果就会有所差异，但是默认的这种方式是为了运算速度而言，运算速度会更快。
    该关键字可以确保在任何平台中的计算结果都相同都精确精确，只不过是速度会稍微慢一点儿。

    对精确率类型较高且跨平台的计算结果要求比较严格的清醒的话，建议使用该strictfp关键词。
    该关键字可以修饰在接口、类或者是方法上面，下面是strictfp的用法与代码案例：
    修饰接口的代码书写格式：
    strictfp   interface  Interface {} // 作用于整个类
    public  strictfp  class  Demo {} // 作用于接口中的所有方法
    strictfp  void  compute () {} // 作用于该方法
 */