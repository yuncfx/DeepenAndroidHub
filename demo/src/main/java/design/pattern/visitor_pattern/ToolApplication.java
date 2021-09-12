package design.pattern.visitor_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * ����һ�����߶������Ӧ�õ�һ������ϣ���������Ͷ�����
 * ����Ƚϼ򵥣����ǰ������Ӳ�����⣬�ҾͲ���������ˡ����ڷ�����ģʽ�Ĵ���ʵ
 * �֣�ʵ���ϣ������������У���������ع�֮������մ��룬���Ǳ�׼�ķ�����ģʽ��ʵ�ִ���
 * <p>
 * һ����˵��������ģʽ��Ե���һ�����Ͳ�ͬ�Ķ���PdfFile��PPTFile��WordFile������
 * ���������������������ǲ�ͬ�ģ����ǣ����Ǽ̳���ͬ�ĸ��ࣨResourceFile������ʵ
 * ����ͬ�Ľӿڡ��ڲ�ͬ��Ӧ�ó����£�������Ҫ������������һϵ�в���ص�ҵ�����
 * ����ȡ�ı���ѹ���ȣ�����Ϊ�˱��ⲻ����ӹ��ܵ����ࣨPdfFile��PPTFile��WordFile��
 * �������ͣ�ְ��Խ��Խ����һ���Լ�����Ƶ������ӹ��ܵ��µ�Ƶ�������޸ģ�����ʹ�÷�
 * ����ģʽ��������������������Щҵ�������������������ڶ���ϸ�ֵķ�������
 * ��Extractor��Compressor���С�
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
//...���ݺ�׺(pdf/ppt/word)�ɹ�������������ͬ�������(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PPTFile("c.ppt"));
        return resourceFiles;
    }
}
