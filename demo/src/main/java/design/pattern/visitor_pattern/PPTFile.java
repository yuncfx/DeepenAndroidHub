package design.pattern.visitor_pattern;

public class PPTFile extends ResourceFile {
    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
