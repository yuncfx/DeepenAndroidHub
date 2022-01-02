package design.pattern.lg;

class Chair {
}

class Sofa {
}

class Table {
}

class ChinaChair extends Chair {
}

class ChinaSofa extends Sofa {
}

class ChinaTable extends Table {
}

class USAChair extends Chair {
}

class USASofa extends Sofa {
}

class USATable extends Table {
}

class Client {
    private Chair myChair;
    private Sofa mySofa;
    private Table myTable;


    //通过抽象工厂来生产家具
    public Client(AbstractFactory af) {
        myChair = af.createChair();
        mySofa = af.createSofa();
        myTable = af.createTable();
    }
}

//抽象的家具工厂
abstract class AbstractFactory {
    abstract Chair createChair();

    abstract Sofa createSofa();

    abstract Table createTable();
}

//中国的家具工厂

class ChinaFactory extends AbstractFactory {

    @Override
    Chair createChair() {
        return new ChinaChair();
    }

    @Override
    Sofa createSofa() {
        return new ChinaSofa();
    }

    @Override
    Table createTable() {
        return new ChinaTable();
    }
}

//美国的家具工厂

class USAFactory extends AbstractFactory {

    @Override
    Chair createChair() {
        return new USAChair();
    }

    @Override
    Sofa createSofa() {
        return new USASofa();
    }

    @Override
    Table createTable() {
        return new USATable();
    }
}

