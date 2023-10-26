package com.adp.wordcount.processors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class MsWordFileProcessor extends FileProcessor {
    
    public MsWordFileProcessor(String filename) {
        super(filename);
    }

    @Override
    public void readFileContent() throws IOException {

        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(this.filename)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String content = xwpfWordExtractor.getText();
           
            this.content = content;
            xwpfWordExtractor.close();
        }

    }
}
