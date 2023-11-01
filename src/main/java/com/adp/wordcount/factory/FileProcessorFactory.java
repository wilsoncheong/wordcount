package com.adp.wordcount.factory;

import com.adp.wordcount.processors.FileProcessor;
import com.adp.wordcount.processors.MsWordFileProcessor;
import com.adp.wordcount.processors.TextFileProcessor;

/**
* WordCount File Processor Factory
* 
* @author  Wilson Cheong
* @version 1.0
* @since   2023-11-01
*/
public class FileProcessorFactory {
    
    /**
     * This is to create File Processor based on fileFormat.
     * 
     * @param fileFormat File extension
     * @param filename Filename to process
     * 
     * @return
     */
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
