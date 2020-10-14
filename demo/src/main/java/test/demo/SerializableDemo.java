package test.demo;

import org.junit.Test;

import java.io.*;

public class SerializableDemo {

    static class SerializableBean implements Serializable {
        private String name;
        private transient String address;

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        private void generateAddress() {
            // Generate thumbnail.
            this.address = "hahhaha";
        }

        // 可以实现readObject
        private void readObject(ObjectInputStream inputStream)
                throws IOException, ClassNotFoundException {
            // 如果不写defaultReadObject(), 则不会序列化属性
            inputStream.defaultReadObject();
            generateAddress();
        }

        // 可以实现writeObject
        private void writeObject(ObjectOutputStream outputStream) throws IOException {
            // 如果实现了writeObject, 必须添加defaultWriteObject
            outputStream.defaultWriteObject();
        }

        @Override
        public String toString() {
            return "SerializableBean{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    static class ExternalizableBean implements Externalizable {

        transient String name;
        transient String address;

        public ExternalizableBean() {
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(name);
            out.writeObject(address);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.name = (String) in.readObject();
            this.address = (String) in.readObject();
        }

        @Override
        public String toString() {
            return "ExternalizableBean{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ExternalizableBean bean = new ExternalizableBean();
        bean.setName("hello");
        bean.setAddress("changping");
        System.out.println(bean);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("c:\\obj.txt"));
        outputStream.writeObject(bean);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("c:\\obj.txt"));
        ExternalizableBean bean2 = (ExternalizableBean) inputStream.readObject();

        System.out.println(bean2);

        SerializableBean bean3 = new SerializableBean();
        bean3.setName("serializable_bean");
        bean3.setAddress("serializable_address");
        outputStream = new ObjectOutputStream(new FileOutputStream("c:\\obj.txt"));
        outputStream.writeObject(bean3);

        inputStream = new ObjectInputStream(new FileInputStream("c:\\obj.txt"));
        SerializableBean bean4 = (SerializableBean) inputStream.readObject();
        System.out.println(bean4);

    }
}
