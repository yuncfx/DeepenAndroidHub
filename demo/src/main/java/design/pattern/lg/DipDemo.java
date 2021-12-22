package design.pattern.lg;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

class StringProcessor { //具体类
    private final StringReader stringReader; //具体类
    private final StringWriter stringWriter; //具体类

    //通过构造函数来注入依赖组件
    public StringProcessor(StringReader stringReader, StringWriter stringWriter) {
        this.stringReader = stringReader;
        this.stringWriter = stringWriter;
    }

    public void readAndWrite() throws IOException {
        stringWriter.write(stringReader.read());
    }

    //测试用例

    public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader("1111111");
        sr.read();
        StringWriter sw = new StringWriter();
        StringProcessor sp = new StringProcessor(sr, sw);
        sp.readAndWrite();
    }
}

interface IStringProcessor {
    void readAndWrite(StringReader stringReader, StringWriter stringWriter) throws IOException;
}

class StringProcessorImpl implements IStringProcessor {

    //通过接口传参来注入依赖组件
    @Override
    public void readAndWrite(StringReader stringReader, StringWriter stringWriter) throws IOException {
        stringWriter.write(stringReader.read());
    }

    public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader("222222");
        sr.read();
        StringWriter sw = new StringWriter();
        IStringProcessor sp = new StringProcessorImpl();
        sp.readAndWrite(sr, sw);
    }
}

interface IStringReader {
    void read(String path);

    String getValue();
}

interface IStringWriter {
    void write(String value);
}

interface IStringProcessor2 {
    void readAndWrite(IStringReader stringReader, IStringWriter stringWriter) throws IOException;
}

class StringReaderImpl implements IStringReader {
    @Override
    public void read(String path) {
    }

    @Override
    public String getValue() {
        return null;
    }
}

class StringWriterImpl implements IStringWriter {
    @Override
    public void write(String value) {
    }
}

class StringProcessorImpl2 implements IStringProcessor2 {
    @Override
    public void readAndWrite(IStringReader stringReader, IStringWriter stringWriter) {
        stringWriter.write(stringReader.getValue());
    }

    public static void main(String[] args) throws IOException {
        IStringReader sr = new StringReaderImpl();
        sr.read("333333");
        IStringWriter sw = new StringWriterImpl();
        IStringProcessor2 sp = new StringProcessorImpl2();
        sp.readAndWrite(sr, sw);
    }
}

class SPTest {
    //@Resource
    private StringProcessor sp;

    //@Resource
    private StringReader sr;

    //@Resource
    private StringWriter sw;

    public void main(String[] args) {
        // sr.read("444444");
        // sp.readAndWrite(sr, sw);
    }
}



