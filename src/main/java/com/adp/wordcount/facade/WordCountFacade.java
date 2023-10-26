package com.adp.wordcount.facade;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adp.wordcount.factory.FileProcessorFactory;
import com.adp.wordcount.processors.FileProcessor;
import com.adp.wordcount.services.WordCountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WordCountFacade {

    @Autowired
    private WordCountService wordCountService;
 
    public void processWordCount(String filename) throws IOException {
        log.info("Start Process Count - " + filename);
        String fileFormat = FilenameUtils.getExtension(filename);
        log.info("fileFormat - " + fileFormat);
        
        FileProcessor fileProcessor = FileProcessorFactory.getFileProcessor(fileFormat, filename);
        wordCountService.processWordCount(fileProcessor, filename);
    }
}
