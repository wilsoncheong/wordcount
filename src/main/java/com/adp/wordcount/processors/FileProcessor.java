package com.adp.wordcount.processors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.adp.wordcount.models.WordCountResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class FileProcessor {

    protected String filename;
    protected String content;
    protected WordCountResult result;

    public FileProcessor(String filename) {
        this.filename = filename;
    }

    public WordCountResult getResult() {
        return this.result;
    }

    public void processWordCount() throws IOException {
        readFileContent();
        countWordsInContent();
    }

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

    protected void countWordsInContent() throws IOException {

        Map<String, Integer> wordCounts = new HashMap<>();

        Arrays.stream(this.content.split("\\s+")).forEach((x) -> {
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
