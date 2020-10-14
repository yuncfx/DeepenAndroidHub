package design.pattern.visitor_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 允许一个或者多个操作应用到一组对象上，解耦操作和对象本身。
 * 定义比较简单，结合前面的例子不难理解，我就不过多解释了。对于访问者模式的代码实
 * 现，实际上，在上面例子中，经过层层重构之后的最终代码，就是标准的访问者模式的实现代码
 * <p>
 * 一般来说，访问者模式针对的是一组类型不同的对象（PdfFile、PPTFile、WordFile）。不
 * 过，尽管这组对象的类型是不同的，但是，它们继承相同的父类（ResourceFile）或者实
 * 现相同的接口。在不同的应用场景下，我们需要对这组对象进行一系列不相关的业务操作
 * （抽取文本、压缩等），但为了避免不断添加功能导致类（PdfFile、PPTFile、WordFile）
 * 不断膨胀，职责越来越不单一，以及避免频繁地添加功能导致的频繁代码修改，我们使用访
 * 问者模式，将对象与操作解耦，将这些业务操作抽离出来，定义在独立细分的访问者类
 * （Extractor、Compressor）中。
 *
 */
public class ToolApplication {
    public static void main(String[] args) {
        Extrator extrator = new Extrator();
        List<ResourceFile> resourceFileList = listAllResourceFiles(args[0]);
        for (ResourceFile file : resourceFileList) {
            file.accept(extrator);
        }

        Compressor compressor = new Compressor();
        for (ResourceFile file : resourceFileList) {
            file.accept(compressor);
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirecto) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
//...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PPTFile("c.ppt"));
        return resourceFiles;
    }
}
