package com.adp.wordcount.processors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
* Ms Word File Processor
* This is File Processor for Ms Word file
*
* @author  Wilson Cheong
* @version 1.0
* @since   2023-11-01
*/
public class MsWordFileProcessor extends FileProcessor {
    
    /**
     * Constructor for MsWordFileProcessor
     * @param filename Filename to process
     */
    public MsWordFileProcessor(String filename) {
        super(filename);
    }

    @Override
    /**
     * This is to read doc file content
     */
    public String readFileContent() throws IOException {

        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(this.filename)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String content = xwpfWordExtractor.getText();
           
            xwpfWordExtractor.close();
            return content;
        }

    }
}
