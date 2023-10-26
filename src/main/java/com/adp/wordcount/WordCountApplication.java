package com.adp.wordcount;

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

        wordCountFacade.processWordCount(filename);
        log.info("Application End !!");
    }

}
