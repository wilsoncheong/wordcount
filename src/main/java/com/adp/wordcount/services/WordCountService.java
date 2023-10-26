package com.adp.wordcount.services;

import java.io.IOException;

import com.adp.wordcount.processors.FileProcessor;

public interface WordCountService {
    
    void processWordCount(FileProcessor fileProcessor, String filename) throws IOException;
}
