package com.adp.wordcount;

import java.lang.annotation.Documented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.adp.wordcount.facade.WordCountFacade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.adp.wordcount")

/**
* Wordcount Application
* This is to calculate the number of occurrences of word in the file.
*
* @author  Wilson Cheong
* @version 1.0
* @since   2023-11-01
*/
public class WordCountApplication implements CommandLineRunner {

    @Autowired
    private WordCountFacade wordCountFacade;

    public static void main(String[] args) {
        SpringApplication.run(WordCountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application Started !!");
        
        String filename = System.getProperty("wordcount.filename");
        log.info("Start Process " + filename);

        if (filename != null) {
            wordCountFacade.processWordCount(filename);
        }
        
        log.info("Application End !!");
    }

}
