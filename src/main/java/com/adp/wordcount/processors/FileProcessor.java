package com.adp.wordcount.processors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.adp.wordcount.models.WordCountResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
* File Processor
* This is baseline for File Processor
*
* @author  Wilson Cheong
* @version 1.0
* @since   2023-11-01
*/
public abstract class FileProcessor {

    protected String filename;
    protected String content;
    protected WordCountResult result;

    /**
     * Constructor for File Processor
     * @param filename Filename to process
     */
    public FileProcessor(String filename) {
        this.filename = filename;
    }

    /**
     * This is to return the WordCount result
     * @return
     */
    public WordCountResult getResult() {
        return this.result;
    }

    /**
     * This is to read content and push wordcount to map
     * @throws IOException
     */
    public void processWordCount() throws IOException {
        readFileContent();
        countWordsInContent();
    }

    /**
     * This is to read file content
     * @throws IOException
     */
    protected void readFileContent() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String content = stringBuilder.toString();
        //log.info("content - " + content);
        this.content = content;

        reader.close();

    }

    /**
     * This is to count word
     * @throws IOException
     */
    protected void countWordsInContent() throws IOException {

        Map<String, Integer> wordCounts = new HashMap<>();

        // Split string by space
        Arrays.stream(this.content.split("\\s+")).forEach((x) -> {

            // Remove special character
            var str = x.replaceAll("\\W", "");
            
            if (wordCounts.keySet().contains(str)) {
                wordCounts.put(str, wordCounts.get(str) + 1);
            } else {
                wordCounts.put(str, 1);
            }
        });

        this.result = new WordCountResult(wordCounts);
    }

}
