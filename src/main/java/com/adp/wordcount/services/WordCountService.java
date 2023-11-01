package com.adp.wordcount.services;

import java.io.IOException;
import java.util.Map;

import com.adp.wordcount.models.WordCountResult;
import com.adp.wordcount.processors.FileProcessor;

public interface WordCountService {
    
    void processWordCount(FileProcessor fileProcessor, String filename) throws IOException;

    WordCountResult countWordsInContent(String content) throws IOException;

    Map<String, Integer> sortByWordCountAscending(WordCountResult result);

    Map<String, Integer> sortByWordCountDescending(WordCountResult result);
}
