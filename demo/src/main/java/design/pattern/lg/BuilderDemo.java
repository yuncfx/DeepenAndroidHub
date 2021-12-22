package design.pattern.lg;

class Product {

    private int partA;
    private String partB;
    private int partC;

    public Product(int partA, String partB, int partC) {
        this.partA = partA;
        this.partB = partB;
        this.partC = partC;
    }

    @Override
    public String toString() {
        return "Product{" +
                "partA=" + partA +
                ", partB='" + partB + '\'' +
                ", partC=" + partC +
                '}';
    }
}

interface Builder {

    void buildPartA(int partA);

    void buildPartB(String partB);

    void buildPartC(int partC);

    Product getResult();
}

class ConcreteBuilder implements Builder {

    private int partA;
    private String partB;
    private int partC;

    @Override
    public void buildPartA(int partA) {
        this.partA = partA;
    }

    @Override
    public void buildPartB(String partB) {
        this.partB = partB;
    }

    @Override
    public void buildPartC(int partC) {
        this.partC = partC;
    }

    public Product getResult() {
        return new Product(partA, partB, partC);
    }

}

class Director {
    public void construct(Builder builder) {
        builder.buildPartA(1);
        builder.buildPartB("test-test");
        builder.buildPartC(2);
    }

    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilder();
        director.construct(builder);
        System.out.println(builder.getResult());
    }
}

/**
 * 将自身类作为建造者的方法来实现。
 */
class MigrantWorker {

    //所有属性
    private String name;
    private int age;
    private String phone;
    private String gender;

    private MigrantWorker() {
    }

    public static MigrantWorker builder() {
        return new MigrantWorker();
    }

    //将属性作为步骤

    public MigrantWorker name(String name) {
        this.name = name;
        return this;
    }

    //将属性作为步骤

    public MigrantWorker age(int age) {
        this.age = age;
        return this;
    }

    //将属性作为步骤

    public MigrantWorker phone(String phone) {
        this.phone = phone;
        return this;
    }

    //将属性作为步骤
    public MigrantWorker gender(String gender) {
        this.gender = gender;
        return this;
    }

    //执行创建操作

    public MigrantWorker build() {
        validateObject(this);
        return this;
    }

    private void validateObject(MigrantWorker migrantWorker) {
        //可以做基础预校验，或自定义校验
    }

    @Override
    public String toString() {
        return "MigrantWorker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

