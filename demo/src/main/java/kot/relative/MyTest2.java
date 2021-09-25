package kot.relative;

import java.util.ArrayList;
import java.util.List;

// PECS: Producer Extends, Consumer Super.
public class MyTest2 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<Object> list2 = list;
//        list2.add(3);
//        String str = list2.get(0);

        List<Cat> cats = new ArrayList<>();
        List<? extends Animal> animals = cats;

        // compile error, 协变：只允许读取，不允许写入
//        animals.add(new Cat())

        List<Animal> animals1 = new ArrayList<>();
        List<? super Animal> contravariantAnimals = animals1;
//        List<? super Animal> contravariantAnimals = new ArrayList<>();

        contravariantAnimals.add(new Cat());
        contravariantAnimals.add(new Dog());
// TODO 为什么不能添加Object
//        contravariantAnimals.add(new Object());


        Object object = contravariantAnimals.get(0);
        System.out.println(object);

        // 数组天然支持协变，但是有缺陷
        Object[] objs = new String[]{"hello", "world"};
        // ArrayStoreException
        objs[0] = new Object();

    }
}

class Animal {
}

class Cat extends Animal {
}

class Dog extends Animal {
}
