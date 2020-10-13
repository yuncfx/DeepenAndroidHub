package com.design.pattern.visitor_pattern;

public interface Visitor {
    void visit(PdfFile pdffile);
    void visit(PPTFile file);
    void visit(WordFile pdfFile);
}
