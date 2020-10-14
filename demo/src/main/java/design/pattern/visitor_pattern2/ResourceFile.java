package design.pattern.visitor_pattern2;

public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract ResourceFileType getType();
}