package com.adp.wordcount.factory;

import com.adp.wordcount.processors.FileProcessor;
import com.adp.wordcount.processors.MsWordFileProcessor;
import com.adp.wordcount.processors.TextFileProcessor;

public class FileProcessorFactory {
    
    public static FileProcessor getFileProcessor(String fileFormat, String filename){

        FileProcessor fileProcessor = null;

        switch(fileFormat) {
            case "txt":
                fileProcessor = new TextFileProcessor(filename);
            break;

            case "docx":
                fileProcessor = new MsWordFileProcessor(filename);
            break;

            default:
                throw new UnsupportedOperationException("Not implement yet!");
   
        }
		
		return fileProcessor;
	}
}
