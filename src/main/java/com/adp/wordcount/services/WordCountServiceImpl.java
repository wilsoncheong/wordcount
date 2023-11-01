package com.adp.wordcount.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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

        String content = fileProcessor.readFileContent();
        WordCountResult result = countWordsInContent(content);
        log.info("Result size - " + result.getWordCounts().size());

        generateResultFiles(result);
    }

    /**
     * This is to prepare ASC and Descending result
     * @param result
     * @throws FileNotFoundException
     */
    private void generateResultFiles(WordCountResult result) throws FileNotFoundException {

        String tmpdir = System.getProperty("java.io.tmpdir");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String ascFilename = tmpdir + "\\wordcount-asc-" + timestamp.getTime() + ".txt";
        String descFilename = tmpdir + "\\wordcount-desc-" + timestamp.getTime() + ".txt";

        generateWordCountFile(ascFilename, sortByWordCountAscending(result));
        generateWordCountFile(descFilename, sortByWordCountDescending(result));

    }

    /**
     * This is to sort result by wordcount ascending
     * @param result return WordCountResult
     */
    public Map<String, Integer> sortByWordCountAscending(WordCountResult result) {
        return result.getWordCounts().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * This is to sort result by wordcount descending
     * @param result return WordCountResult
     */
    public Map<String, Integer> sortByWordCountDescending(WordCountResult result) {
        return result.getWordCounts().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * This is to generate WordCount output file
     * @param filename Filename to output
     * @param result Wordcount result in map
     * @throws FileNotFoundException
     */
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
    
    /**
     * This is to calculate word in content
     * @param content file content
     * @return WordCountResult
     * @throws IOException
     */
    public WordCountResult countWordsInContent(String content) throws IOException {

        Map<String, Integer> wordCounts = new HashMap<>();
        
        // Split string by space
        Arrays.stream(content.split("\\s+")).forEach((x) -> {

            // Remove special character
            var str = x.replaceAll("\\W", "");
            
            if (wordCounts.keySet().contains(str)) {
                wordCounts.put(str, wordCounts.get(str) + 1);
            } else {
                wordCounts.put(str, 1);
            }
        });

        return new WordCountResult(wordCounts);
    }


}
