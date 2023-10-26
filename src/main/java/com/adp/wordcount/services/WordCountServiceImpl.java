package com.adp.wordcount.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.wordcount.config.ApplicationConfig;
import com.adp.wordcount.models.WordCountResult;
import com.adp.wordcount.processors.FileProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WordCountServiceImpl implements WordCountService {

    @Autowired
    private ApplicationConfig appConfig;

    public void processWordCount(FileProcessor fileProcessor, String filename) throws IOException {

        fileProcessor.processWordCount();
        WordCountResult result = fileProcessor.getResult();
        log.info("Result size - " + result.getWordCounts().size());

        /*
         * result.getWordCounts().forEach((key, count) -> {
         * log.info(String.format("Word is %s - %s", key, count));
         * });
         */

        generateResultFiles(result);
    }

    private void generateResultFiles(WordCountResult result) throws FileNotFoundException {

        String tmpdir = System.getProperty("java.io.tmpdir");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String descFilename = tmpdir + "\\wordcount-desc-" + timestamp.getTime() + ".txt";
        String ascFilename = tmpdir + "\\wordcount-asc-" + timestamp.getTime() + ".txt";

        Map<String, Integer> sortedAscResult = result.getWordCounts().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        generateWordCountFile(ascFilename, sortedAscResult);

        Map<String, Integer> sortedDescResult = result.getWordCounts().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        generateWordCountFile(descFilename, sortedDescResult);

    }

    private void generateWordCountFile(String filename, Map<String, Integer> result) throws FileNotFoundException {

        StringBuilder builder = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.forEach((key, count) -> {

            builder.append(key)
                    .append(": ")
                    .append(count).append(newLine);
        });

        try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
            out.print(builder.toString());
            out.flush();
            out.close();
        }
    }

}
