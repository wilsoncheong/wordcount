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

    /**
     * Constructor for File Processor
     * @param filename Filename to process
     */
    public FileProcessor(String filename) {
        this.filename = filename;
    }
 
    /**
     * This is to read file content
     * @throws IOException
     */
    public String readFileContent() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String content = stringBuilder.toString();
        reader.close();

        return content;
    }

}
