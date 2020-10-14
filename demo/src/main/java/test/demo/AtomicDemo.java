package test.demo;

import org.junit.Test;

import java.util.concurrent.atomic.*;

public class AtomicDemo {
    final class AtomicDemoBean {
        volatile String name;
        volatile int age;
        volatile long id;

        AtomicDemoBean(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "AtomicDemoBean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private AtomicReference<AtomicDemoBean> mReference = new AtomicReference<>();
    private static final AtomicReferenceFieldUpdater<AtomicDemoBean, String> NEW_UPDATER = AtomicReferenceFieldUpdater.newUpdater(AtomicDemoBean.class, String.class, "name");
    private static final AtomicIntegerFieldUpdater<AtomicDemoBean> integerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicDemoBean.class, "age");
    private static final AtomicLongFieldUpdater<AtomicDemoBean> longFieldUpdater = AtomicLongFieldUpdater.newUpdater(AtomicDemoBean.class, "id");
    private AtomicDemoBean stampedBean = new AtomicDemoBean("stamped");
    private AtomicDemoBean markableBean = new AtomicDemoBean("markable");
    private AtomicMarkableReference<AtomicDemoBean> markableReference = new AtomicMarkableReference<>(markableBean, false);
    private AtomicStampedReference<AtomicDemoBean> stampedReference = new AtomicStampedReference<>(stampedBean, 111);

    @Test
    public void test() {
        AtomicDemoBean bean = new AtomicDemoBean("hello0");
        boolean b = NEW_UPDATER.compareAndSet(bean, "hello1", "hello2");
        System.out.println("newUpdater b:" + b);
        System.out.println("bean:" + bean);

        mReference.set(bean);
        boolean helloReference = mReference.compareAndSet(bean, new AtomicDemoBean("helloReference"));
        System.out.println("mReference helloReference:" + helloReference);
        System.out.println("mReference.get:" + mReference.get());

        boolean b1 = integerFieldUpdater.compareAndSet(bean, 0, 11);
        System.out.println("integerFieldUpdater b1:" + b1);

        boolean b2 = longFieldUpdater.compareAndSet(bean, 0, 361234L);
        System.out.println("longFieldUpdater b2:" + b2);

        boolean b3 = stampedReference.attemptStamp(stampedBean, 222);
        int stamp = stampedReference.getStamp();
        System.out.println("stampedReference b3:" + b3 + ", stamp:" + stamp);

        boolean b4 = markableReference.attemptMark(markableBean, true);
        boolean marked = markableReference.isMarked();
        System.out.println("markableReference b4:" + b4 + ", marked:" + marked);
    }
}


