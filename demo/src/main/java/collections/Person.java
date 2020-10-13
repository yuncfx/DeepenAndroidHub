package collections;

import java.util.Calendar;

public class Person<T> implements Cloneable {
    String name;
    int age;
    Calendar birth;
    public String firstName;
    String lastName;
    T t;

    public Person(T t) {
        this.t = t;
    }

    public Person(String name, int age, Calendar birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
    }
    


    Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return name + ":" + age + ":" + birth.get(Calendar.YEAR) 
                + ":" + birth.get(Calendar.MONTH) + ":" + birth.get(Calendar.DAY_OF_MONTH);
    }

}
