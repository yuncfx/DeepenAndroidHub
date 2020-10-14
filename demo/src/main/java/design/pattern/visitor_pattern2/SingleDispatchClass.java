package design.pattern.visitor_pattern2;

public class SingleDispatchClass {
    public void polymorphismFunction(ParentClass p) {
        p.f();
    }

    public void overloadFunction(ParentClass p) {
        System.out.println("I am overloadFunction(ParentClass p).");
    }

    public void overloadFunction(ChildClass c) {
        System.out.println("I am overloadFunction(ChildClass c).");
    }

    public void overloadFunction2(ParentClass p) {
        p.f();
    }

    public void overloadFunction2(ChildClass c) {
        c.f();
    }
}