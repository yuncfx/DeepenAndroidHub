package design.pattern.lg

import java.io.IOException
import java.io.StringReader
import java.io.StringWriter

internal class StringProcessor     //通过构造函数来注入依赖组件
    (//具体类
    private val stringReader //具体类
    : StringReader, //具体类
    private val stringWriter: StringWriter
) {
    @Throws(IOException::class)
    fun readAndWrite() {
        stringWriter.write(stringReader.read())
    }

    companion object {
        //测试用例
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val sr = StringReader("1111111")
            sr.read()
            val sw = StringWriter()
            val sp = StringProcessor(sr, sw)
            sp.readAndWrite()
        }
    }
}

internal interface IStringProcessor {
    @Throws(IOException::class)
    fun readAndWrite(stringReader: StringReader, stringWriter: StringWriter)
}

internal class StringProcessorImpl : IStringProcessor {
    //通过接口传参来注入依赖组件
    @Throws(IOException::class)
    override fun readAndWrite(stringReader: StringReader, stringWriter: StringWriter) {
        stringWriter.write(stringReader.read())
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val sr = StringReader("222222")
            sr.read()
            val sw = StringWriter()
            val sp: IStringProcessor = StringProcessorImpl()
            sp.readAndWrite(sr, sw)
        }
    }
}

internal interface IStringReader {
    fun read(path: String?)
    val value: String?
}

internal interface IStringWriter {
    fun write(value: String?)
}

internal interface IStringProcessor2 {
    @Throws(IOException::class)
    fun readAndWrite(stringReader: IStringReader, stringWriter: IStringWriter)
}

internal class StringReaderImpl : IStringReader {
    override fun read(path: String?) {}
    override val value: String?
        get() = null
}

internal class StringWriterImpl : IStringWriter {
    override fun write(value: String?) {}
}

internal class StringProcessorImpl2 : IStringProcessor2 {
    override fun readAndWrite(stringReader: IStringReader, stringWriter: IStringWriter) {
        stringWriter.write(stringReader.value)
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val sr: IStringReader = StringReaderImpl()
            sr.read("333333")
            val sw: IStringWriter = StringWriterImpl()
            val sp: IStringProcessor2 = StringProcessorImpl2()
            sp.readAndWrite(sr, sw)
        }
    }
}

internal class SPTest {
    //@Resource
    private val sp: StringProcessor? = null

    //@Resource
    private val sr: StringReader? = null

    //@Resource
    private val sw: StringWriter? = null
    fun main(args: Array<String?>?) {
        // sr.read("444444");
        // sp.readAndWrite(sr, sw);
    }
}